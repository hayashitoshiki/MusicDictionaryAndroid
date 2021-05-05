package com.example.musicdictionaryandroid.ui.home

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.ItemResultArtistBinding
import com.example.musicdictionaryandroid.databinding.ItemResultArtistHeaderBinding
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.model.value.Gender
import com.example.musicdictionaryandroid.ui.util.MessageUtilImp
import com.example.musicdictionaryandroid.ui.util.transition.ResizeAnimation
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.squareup.picasso.Picasso

/**
 * アーティスト検索結果画面のリサイクルビュー
 */
class ResultAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: ResultAdapterViewModel,
    private val context: Context,
    private val artistList: List<ArtistSearchContents<*>>
) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_CONTENTS = 2
    }

    // 表示するViewのタイプ
    override fun getItemViewType(position: Int): Int {
        return when (artistList[position]) {
            is ArtistSearchContents.Conditions -> VIEW_TYPE_HEADER
            is ArtistSearchContents.Item -> VIEW_TYPE_CONTENTS
        }
    }

    // 表示するレイアウト
    private fun getLayoutRes(viewType: Int) =
        when (viewType) {
            VIEW_TYPE_HEADER -> R.layout.item_result_artist_header
            VIEW_TYPE_CONTENTS -> R.layout.item_result_artist
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }

    private lateinit var listener: View.OnClickListener
    private val originalHeight = convertDp2Px(352f, context).toInt()
    private val anim1 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_150_anim)
    private val anim2 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_300_anim)

    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding = DataBindingUtil.bind(v)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(getLayoutRes(viewType), parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = artistList[position]) {
            is ArtistSearchContents.Item -> {
                (holder.binding as ItemResultArtistBinding).also { binding ->
                    // 初期化
                    val state = ResultAdapterBodyState(MessageUtilImp)
                    binding.item = item.value
                    binding.viewModel = viewModel
                    binding.lifecycleOwner = viewLifecycleOwner
                    binding.state = state
                    state.setBookmarkFlg(item.value.bookmarkFlg)

                    // サムネイル
                    if (!item.value.thumb.isNullOrEmpty()) {
                        Picasso.with(context)
                            .load(item.value.thumb)
                            .fit()
                            .centerCrop()
                            .into(binding.imageView)
                    }
                    // 年齢毎の比率表示
                    binding.generationPieChart.also {
                        val data = setPieDataSet(item.value, PiaChartKey.GENERATION)
                        it.centerText = context.resources.getString(R.string.pie_title_gender)
                        it.setEntryLabelTextSize(10f)
                        it.setCenterTextSize(15f)
                        it.legend.isEnabled = false
                        it.description.isEnabled = false
                        it.data = PieData(data)
                        it.invalidate()
                    }
                    // 男女比率表示
                    binding.genderPieChart.also {
                        val data = setPieDataSet(item.value, PiaChartKey.GENDER)
                        it.centerText = context.resources.getString(R.string.pie_title_gender)
                        it.setCenterTextSize(15f)
                        it.legend.isEnabled = false
                        it.description.isEnabled = false
                        it.data = PieData(data)
                        it.invalidate()
                    }
                    // お気に入りボタン
                    binding.bookmark.setOnClickListener {
                        item.value.bookmarkFlg = !item.value.bookmarkFlg
                        state.setBookmarkFlg(item.value.bookmarkFlg)
                        viewModel.setBookMark(item.value)
                    }
                    // 再生ボタン
                    binding.play.setOnClickListener {
                        viewModel.onClickPlayBack(state, item.value)
                    }
                    // 詳細ボタン
                    val collapseAnimation = ResizeAnimation(binding.detailsProfile, -originalHeight, originalHeight)
                    val expandAnimation = ResizeAnimation(binding.detailsProfile, originalHeight, 0)
                    collapseAnimation.duration = 300
                    expandAnimation.duration = 300
                    binding.detailButton.setOnClickListener {
                        binding.detailsProfile.clearAnimation()
                        when (state.isDetailsProfile.value) {
                            true -> {
                                binding.detailsProfile.startAnimation(collapseAnimation)
                                Handler().postDelayed({
                                    state.setIsDetailsProfile(false)
                                    state.setIsGenerationPieChart(false)
                                    state.setIsGenderPieChart(false)
                                }, 300)
                            }
                            false -> {
                                state.setIsDetailsProfile(true)
                                binding.detailsProfile.startAnimation(expandAnimation)
                                Handler().postDelayed({
                                    state.setIsGenerationPieChart(true)
                                    binding.generationPieChart.startAnimation(anim1)
                                }, 150)
                                Handler().postDelayed({
                                    state.setIsGenderPieChart(true)
                                    binding.genderPieChart.startAnimation(anim2)
                                }, 300)
                            }
                        }
                    }
                }
            }
            is ArtistSearchContents.Conditions -> {
                (holder.binding as ItemResultArtistHeaderBinding).also { binding ->
                    binding.item = item.value
                    binding.state = ResultAdapterHeaderState(MessageUtilImp)
                    // 検索ボタン
                    binding.searchButton.setOnClickListener { view ->
                        listener.onClick(view)
                    }
                }
            }
        }
    }

    // 円グラフの種類
    enum class PiaChartKey {
        GENDER,
        GENERATION
    }

    // 世代の種類
    enum class Generation {
        OVER_10,
        OVER_20,
        OVER_30,
        OVER_40,
        OVER_50,
        OVER_60
    }

    // 円グラフデータ作成
    private fun setPieDataSet(item: ArtistContents, key: PiaChartKey): PieDataSet {
        var totalValue = 0f
        val entryList = mutableListOf<PieEntry>()
        val colorList = mutableListOf<Int>()
        var title = ""

        when (key) {
            PiaChartKey.GENDER -> {
                title = context.resources.getString(R.string.pie_title_gender)
                for (enum in Gender.values()) {
                    val (value, label, color) = when (enum) {
                        Gender.MAN -> Triple(
                            item.user_man,
                            context.resources.getString(R.string.pie_value_gender_man),
                            context.getColor(R.color.light_blue_500)
                        )
                        Gender.WOMAN -> Triple(
                            item.user_woman,
                            context.resources.getString(R.string.pie_value_gender_woman),
                            context.getColor(R.color.pink_500)
                        )
                    }
                    if (value == 0) continue

                    entryList.add(PieEntry(value.toFloat(), label))
                    colorList.add(color)
                    totalValue += value.toFloat()
                }
            }
            PiaChartKey.GENERATION -> {
                title = context.resources.getString(R.string.pie_title_generation)
                for (enum in Generation.values()) {
                    val (value, label, color) = when (enum) {
                        Generation.OVER_10 -> Triple(
                            item.generation1,
                            context.resources.getString(R.string.pie_value_generation_over10),
                            context.getColor(R.color.red_500)
                        )
                        Generation.OVER_20 -> Triple(
                            item.generation2,
                            context.resources.getString(R.string.pie_value_generation_over20),
                            context.getColor(R.color.blue_500)
                        )
                        Generation.OVER_30 -> Triple(
                            item.generation3,
                            context.resources.getString(R.string.pie_value_generation_over30),
                            context.getColor(R.color.yellow_500)
                        )
                        Generation.OVER_40 -> Triple(
                            item.generation4,
                            context.resources.getString(R.string.pie_value_generation_over40),
                            context.getColor(R.color.green_500)
                        )
                        Generation.OVER_50 -> Triple(
                            item.generation5,
                            context.resources.getString(R.string.pie_value_generation_over50),
                            context.getColor(R.color.purple_500)
                        )
                        Generation.OVER_60 -> Triple(
                            item.generation6,
                            context.resources.getString(R.string.pie_value_generation_over60),
                            context.getColor(R.color.brown_500)
                        )
                    }
                    if (value == 0) continue

                    entryList.add(PieEntry(value.toFloat(), label))
                    colorList.add(color)
                    totalValue += value.toFloat()
                }
            }
        }
        return PieDataSet(entryList, title).also { pieDataSet ->
            pieDataSet.colors = colorList
            pieDataSet.valueTextSize = 15f
            pieDataSet.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "%.0f".format(value / totalValue * 100) + "%"
                }
            }
        }
    }

    // 変換
    private fun convertDp2Px(dp: Float, context: Context): Float {
        val metrics = context.resources.displayMetrics
        return dp * metrics.density
    }

    // クリックリスナー
    fun setOnItemClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }
}
