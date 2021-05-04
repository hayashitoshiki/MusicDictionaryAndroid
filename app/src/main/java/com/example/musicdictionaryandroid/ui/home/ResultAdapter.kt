package com.example.musicdictionaryandroid.ui.home

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.ItemResultArtistBinding
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.ui.util.MessageUtilImp
import com.example.musicdictionaryandroid.ui.util.transition.ResizeAnimation
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.squareup.picasso.Picasso

/**
 * アーティスト検索結果画面のリサイクルビュー
 */
class ResultAdapter(
    private val viewModel: ResultAdapterViewModel,
    private val context: Context,
    private val artistList: List<ArtistSearchContents<*>>
) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var holdButton: ImageButton? = null
    private lateinit var listener: View.OnClickListener
    private val originalHeight = convertDp2Px(352f, context).toInt()
    private val anim1 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_150_anim)
    private val anim2 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_300_anim)

    // 参照するviewの定義
    open class ViewHolder(v: ItemResultArtistBinding) : RecyclerView.ViewHolder(v.root) {
        val mainLayout: ConstraintLayout = v.mainContent
        val nameTextView: TextView = v.arthist
        val genderTextView: TextView = v.gender
        val voiceRatingBar: RatingBar = v.voiceRatingBar
        val lengthRatingBar: RatingBar = v.lenghtRatingBar
        val lyricsRatingBar: RatingBar = v.lyricRatingBar
        val genre1TextView: TextView = v.genre1
        val genre2TextView: TextView = v.genre2
        val imageView: ImageView = v.imageView
        val bookmark: ImageView = v.bookmark
        val playButton: ImageButton = v.play
        val webView: WebView = v.webview
        val titleLayout: ConstraintLayout = v.titleContext
        val titleTextView: TextView = v.title
        val searchNameTextView: TextView = v.titleName
        val searchGenderTextView: TextView = v.titleGender
        val searchGenre1TextView: TextView = v.titleGenre1
        val searchGenre2TextView: TextView = v.titleGenre2
        val searchLyricTextView: TextView = v.titleLyric
        val searchLengthTextView: TextView = v.titleLength
        val searchVoiceTextView: TextView = v.titleVoice
        val searchButton: Button = v.searchButtn
        val detailButton: Button = v.detailButton
        val detailLayout: LinearLayout = v.detailsProfile
        val pieChart: PieChart = v.pieChartExample
        val genderChart: PieChart = v.genderPieChart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = ItemResultArtistBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val contents = artistList[position]) {
            is ArtistSearchContents.Conditions -> {
                val conditions = contents.value
                // タイトル設定
                holder.mainLayout.visibility = View.GONE
                holder.titleLayout.visibility = View.VISIBLE
                when (conditions.name) {
                    "急上昇", "おすすめ" -> {
                        holder.titleTextView.text = conditions.name
                        holder.searchNameTextView.visibility = View.GONE
                        holder.searchButton.visibility = View.GONE
                    }
                    else -> {
                        if (!conditions.name.isNullOrEmpty()) {
                            holder.searchNameTextView.text = "アーティスト名：" + conditions.name
                        } else {
                            holder.searchNameTextView.visibility = View.GONE
                        }
                    }
                }
                if (conditions.gender != null && conditions.gender.value != 0) {
                    holder.searchGenderTextView.text = MessageUtilImp.getGender(conditions.gender.value)
                } else {
                    holder.searchGenderTextView.visibility = View.GONE
                }
                if (conditions.genre1 != null && conditions.genre1.value != 0) {
                    holder.searchGenre1TextView.text = MessageUtilImp.getGenre1(conditions.genre1.value)
                } else {
                    holder.searchGenre1TextView.visibility = View.GONE
                }
                if (conditions.genre1 != null && conditions.genre1.value != 0 && conditions.genre2 != null && conditions.genre2.value != 0) {
                    holder.searchGenre2TextView.text =
                        MessageUtilImp.getGenre2(conditions.genre1.value, conditions.genre2.value)
                } else {
                    holder.searchGenre2TextView.visibility = View.GONE
                }
                if (conditions.length != null && conditions.length.value != 0) {
                    holder.searchLengthTextView.text = MessageUtilImp.getLength(conditions.length.value)
                } else {
                    holder.searchLengthTextView.visibility = View.GONE
                }
                if (conditions.voice != null && conditions.voice.value != 0) {
                    holder.searchVoiceTextView.text = MessageUtilImp.getVoice(conditions.voice.value)
                } else {
                    holder.searchVoiceTextView.visibility = View.GONE
                }
                if (conditions.lyrics != null && conditions.lyrics.value != 0) {
                    holder.searchLyricTextView.text = MessageUtilImp.getLyrics(conditions.lyrics.value)
                } else {
                    holder.searchLyricTextView.visibility = View.GONE
                }
                holder.searchButton.setOnClickListener { view ->
                    listener.onClick(view)
                }
            }
            is ArtistSearchContents.Item -> {
                val item = contents.value
                // アーティスト情報設定
                holder.mainLayout.visibility = View.VISIBLE
                holder.titleLayout.visibility = View.GONE

                // アーティスト名
                holder.nameTextView.text = item.artist.name
                // お気に入りボタン
                holder.bookmark.also { bookmark ->
                    when (item.bookmarkFlg) {
                        true -> bookmark.setImageResource(R.drawable.ic_star_yellow_32)
                        false -> bookmark.setImageResource(R.drawable.ic_star_gray_32)
                    }
                    bookmark.setOnClickListener {
                        when (item.bookmarkFlg) {
                            true -> {
                                item.bookmarkFlg = false
                                bookmark.setImageResource(R.drawable.ic_star_gray_32)
                                viewModel.setUnBookMark(item)
                            }
                            false -> {
                                item.bookmarkFlg = true
                                bookmark.setImageResource(R.drawable.ic_star_yellow_32)
                                viewModel.setBookMark(item)
                            }
                        }
                    }
                }
                // 性別
                holder.genderTextView.text = MessageUtilImp.getGender(item.artist.gender.value)
                if (item.artist.gender.value == 1) {
                    holder.genderTextView.setTextColor(Color.BLUE)
                } else {
                    holder.genderTextView.setTextColor(Color.RED)
                }
                // 声の高さ
                holder.voiceRatingBar.rating = item.artist.voice.value.toFloat()
                // 曲の平均の長さ
                holder.lengthRatingBar.rating = item.artist.length.value.toFloat()
                // 歌詞の言語
                holder.lyricsRatingBar.rating = item.artist.lyrics.value.toFloat()
                // ジャンル１
                holder.genre1TextView.text = MessageUtilImp.getGenre1(item.artist.genre1.value)
                // ジャンル２
                holder.genre2TextView.text =
                    MessageUtilImp.getGenre2(item.artist.genre1.value, item.artist.genre2.value)
                // 詳細ボタン
                val collapseAnimation = ResizeAnimation(holder.detailLayout, -originalHeight, originalHeight)
                val expandAnimation = ResizeAnimation(holder.detailLayout, originalHeight, 0)
                collapseAnimation.duration = 300
                expandAnimation.duration = 300
                holder.pieChart.visibility = View.GONE
                holder.genderChart.visibility = View.GONE
                holder.detailLayout.visibility = View.GONE
                holder.detailButton.text = context.getString(R.string.page_open)
                holder.detailButton.setOnClickListener {
                    holder.detailLayout.clearAnimation()
                    if (holder.detailLayout.visibility == View.VISIBLE) {
                        holder.detailLayout.startAnimation(collapseAnimation)
                        Handler().postDelayed(
                            {
                                holder.pieChart.visibility = View.GONE
                                holder.genderChart.visibility = View.GONE
                                holder.detailLayout.visibility = View.GONE
                            },
                            300
                        )
                        holder.detailButton.text = context.getString(R.string.page_open)
                    } else {
                        // 年齢毎の比率表示
                        holder.pieChart.also {
                            var totalValue = 0f
                            val entryList = mutableListOf<PieEntry>()
                            if (item.generation1 != 0) {
                                entryList.add(PieEntry(item.generation1.toFloat(), "10代"))
                                totalValue += item.generation1.toFloat()
                            }
                            if (item.generation2 != 0) {
                                entryList.add(PieEntry(item.generation2.toFloat(), "20代"))
                                totalValue += item.generation2.toFloat()
                            }
                            if (item.generation3 != 0) {
                                entryList.add(PieEntry(item.generation3.toFloat(), "30代"))
                                totalValue += item.generation3.toFloat()
                            }
                            if (item.generation4 != 0) {
                                entryList.add(PieEntry(item.generation4.toFloat(), "40代"))
                                totalValue += item.generation4.toFloat()
                            }
                            if (item.generation5 != 0) {
                                entryList.add(PieEntry(item.generation5.toFloat(), "50代"))
                                totalValue += item.generation5.toFloat()
                            }
                            if (item.generation6 != 0) {
                                entryList.add(PieEntry(item.generation6.toFloat(), "60代"))
                                totalValue += item.generation6.toFloat()
                            }
                            val data = PieDataSet(entryList, "年齢層").also { pieDataSet ->
                                val colorList = mutableListOf<Int>()
                                entryList.forEach { pieEntry ->
                                    when (pieEntry.label) {
                                        "10代" -> colorList.add(context.getColor(R.color.red_500))
                                        "20代" -> colorList.add(context.getColor(R.color.blue_500))
                                        "30代" -> colorList.add(context.getColor(R.color.yellow_500))
                                        "40代" -> colorList.add(context.getColor(R.color.green_500))
                                        "50代" -> colorList.add(context.getColor(R.color.purple_500))
                                        "60代" -> colorList.add(context.getColor(R.color.brown_500))
                                    }
                                }
                                pieDataSet.colors = colorList
                                pieDataSet.valueTextSize = 15f
                                pieDataSet.valueFormatter = object : ValueFormatter() {
                                    override fun getFormattedValue(value: Float): String {
                                        return "%.0f".format(value / totalValue * 100) + "%"
                                    }
                                }
                            }

                            it.centerText = "年齢層"
                            it.setCenterTextSize(15f)
                            it.setEntryLabelTextSize(10f)
                            it.data = PieData(data)
                            it.legend.isEnabled = false
                            it.description.isEnabled = false
                            it.invalidate()
                        }

                        // 男女比率表示
                        holder.genderChart.also {
                            var totalValue = 0f
                            val entryList = mutableListOf<PieEntry>()
                            if (item.user_man != 0) {
                                entryList.add(PieEntry(item.user_man.toFloat(), "男性"))
                                totalValue += item.user_man.toFloat()
                            }
                            if (item.user_woman != 0) {
                                entryList.add(PieEntry(item.user_woman.toFloat(), "女性"))
                                totalValue += item.user_woman.toFloat()
                            }
                            val data = PieDataSet(entryList, "男女比率").also { pieDataSet ->
                                val colorList = mutableListOf<Int>()
                                entryList.forEach { pieEntry ->
                                    when (pieEntry.label) {
                                        "男性" -> colorList.add(context.getColor(R.color.light_blue_500))
                                        "女性" -> colorList.add(context.getColor(R.color.pink_500))
                                    }
                                }
                                pieDataSet.colors = colorList
                                pieDataSet.valueTextSize = 15f
                                pieDataSet.valueFormatter = object : ValueFormatter() {
                                    override fun getFormattedValue(value: Float): String {
                                        return "%.0f".format(value / totalValue * 100) + "%"
                                    }
                                }
                            }

                            it.centerText = "男女比率"
                            it.setCenterTextSize(15f)
                            it.legend.isEnabled = false
                            it.description.isEnabled = false
                            it.data = PieData(data)
                            it.invalidate()
                        }
                        holder.detailLayout.visibility = View.VISIBLE
                        holder.detailLayout.startAnimation(expandAnimation)
                        Handler().postDelayed(
                            {
                                holder.pieChart.startAnimation(anim1)
                                holder.pieChart.visibility = View.VISIBLE
                            },
                            150
                        )
                        Handler().postDelayed(
                            {
                                holder.genderChart.startAnimation(anim2)
                                holder.genderChart.visibility = View.VISIBLE
                            },
                            300
                        )
                        holder.detailButton.text = context.getString(R.string.page_close)
                    }
                }
                // 再生ボタン
                item.preview?.let {
                    if (item.preview != "") {
                        holder.playButton.visibility = View.VISIBLE
                        holder.playButton.setOnClickListener {
                            if (holdButton != holder.playButton) {
                                holdButton?.setImageResource(R.mipmap.ic_button_music_play_32)
                                holder.playButton.setImageResource(R.mipmap.ic_button_music_pause_32)
                                holder.webView.settings.domStorageEnabled = true
                                holder.webView.loadUrl(item.preview)
                                holdButton = holder.playButton
                                Handler().postDelayed(
                                    {
                                        if (holdButton == holder.playButton) {
                                            holdButton?.setImageResource(R.mipmap.ic_button_music_play_32)
                                        }
                                    },
                                    30500
                                )
                            } else {
                                holder.webView.loadUrl("about:blank")
                                holdButton?.setImageResource(R.mipmap.ic_button_music_play_32)
                                holdButton = null
                            }
                        }
                    } else {
                        holder.playButton.visibility = View.GONE
                    }
                } ?: run {
                    holder.playButton.visibility = View.GONE
                }
                // サムネイル
                if (item.thumb != null && item.thumb != "") {
                    holder.imageView.visibility = View.VISIBLE
                    Picasso.with(context)
                        .load(item.thumb)
                        .fit()
                        .centerCrop()
                        .into(holder.imageView)
                } else {
                    holder.imageView.visibility = View.GONE
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
