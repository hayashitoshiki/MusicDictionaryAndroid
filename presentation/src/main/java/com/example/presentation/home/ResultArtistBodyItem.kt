package com.example.presentation.home

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.LifecycleOwner
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.Gender
import com.example.presentation.R
import com.example.presentation.databinding.ItemResultArtistBinding
import com.example.presentation.util.MessageUtilImp
import com.example.presentation.util.transition.ResizeAnimation
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.squareup.picasso.Picasso
import com.xwray.groupie.databinding.BindableItem

/**
 * アーティスト検索結果（コンテンツ部分）アイテム
 */
class ResultArtistBodyItem  (private val item: ArtistContents,
                             private val context: Context,
                             private val viewLifecycleOwner: LifecycleOwner,
                             private val viewModel: ResultAdapterViewModel
                                ) : BindableItem<ItemResultArtistBinding>() {

    private val originalHeight = convertDp2Px(352f, context).toInt()
    private val anim1 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_150_anim)
    private val anim2 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_300_anim)

    override fun getLayout(): Int = R.layout.item_result_artist

    override fun bind(binding: ItemResultArtistBinding, position: Int) {
        // 初期化
        val state = ResultArtistBodyState(MessageUtilImp(context))
        binding.item = item
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.state = state
        state.setBookmarkFlg(item.bookmarkFlg)

        // サムネイル
        if (!item.thumb.isNullOrEmpty()) {
            Picasso.with(context)
                .load(item.thumb)
                .fit()
                .centerCrop()
                .into(binding.imageView)
        }
        // 年齢毎の比率表示
        binding.generationPieChart.also {
            val data = setPieDataSet(item, PiaChartKey.GENERATION)
            it.centerText = context.resources.getString(R.string.pie_title_generation)
            it.setEntryLabelTextSize(10f)
            it.setCenterTextSize(15f)
            it.legend.isEnabled = false
            it.description.isEnabled = false
            it.data = PieData(data)
            it.invalidate()
        }
        // 男女比率表示
        binding.genderPieChart.also {
            val data = setPieDataSet(item, PiaChartKey.GENDER)
            it.centerText = context.resources.getString(R.string.pie_title_gender)
            it.setCenterTextSize(15f)
            it.legend.isEnabled = false
            it.description.isEnabled = false
            it.data = PieData(data)
            it.invalidate()
        }
        // お気に入りボタン
        binding.bookmark.setOnClickListener {
            item.bookmarkFlg = !item.bookmarkFlg
            state.setBookmarkFlg(item.bookmarkFlg)
            viewModel.setBookMark(item)
        }
        // 再生ボタン
        binding.play.setOnClickListener {
            viewModel.onClickPlayBack(state, item)
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
                    Handler().postDelayed(
                        {
                            state.setIsDetailsProfile(false)
                            state.setIsGenerationPieChart(false)
                            state.setIsGenderPieChart(false)
                        },
                        300
                    )
                }
                false -> {
                    state.setIsDetailsProfile(true)
                    binding.detailsProfile.startAnimation(expandAnimation)
                    Handler().postDelayed(
                        {
                            state.setIsGenerationPieChart(true)
                            binding.generationPieChart.startAnimation(anim1)
                        },
                        150
                    )
                    Handler().postDelayed(
                        {
                            state.setIsGenderPieChart(true)
                            binding.genderPieChart.startAnimation(anim2)
                        },
                        300
                    )
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
}
