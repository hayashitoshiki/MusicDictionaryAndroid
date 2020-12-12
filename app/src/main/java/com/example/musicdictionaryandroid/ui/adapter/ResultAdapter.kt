package com.example.musicdictionaryandroid.ui.adapter

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
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.example.musicdictionaryandroid.ui.transition.ResizeAnimation
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_result_artist.view.*


/**
 * アーティスト検索結果画面のリサイクルビュー
 *
 * @property context コンテキスト
 * @property artistList 取得したアーティスト情報
 */
class ResultAdapter(private val context: Context, private val artistList: ArrayList<ArtistsForm>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var holdButton: ImageButton? = null
    private lateinit var listener: View.OnClickListener
    private val originalHeight = convertDp2Px(352f, context).toInt()
    private val anim1 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_150_anim)
    private val anim2 = AnimationUtils.loadAnimation(context, R.anim.fade_in_offset_300_anim)

    // 参照するviewの定義
    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val mainLayout: ConstraintLayout = v.main_content
        val nameTextView: TextView = v.arthist
        val genderTextView: TextView = v.gender
        val voiceRatingBar: RatingBar = v.voice_ratingBar
        val lengthRatingBar: RatingBar = v.lenght_ratingBar
        val lyricsRatingBar: RatingBar = v.lyric_ratingBar
        val genre1TextView: TextView = v.genre1
        val genre2TextView: TextView = v.genre2
        val imageView: ImageView = v.imageView
        val playButton: ImageButton = v.play
        val webView: WebView = v.webview
        val titleLayout: ConstraintLayout = v.title_context
        val titleTextView: TextView = v.title
        val searchNameTextView: TextView = v.title_name
        val searchGenderTextView: TextView = v.title_gender
        val searchGenre1TextView: TextView = v.title_genre1
        val searchGenre2TextView: TextView = v.title_genre2
        val searchLyricTextView: TextView = v.title_lyric
        val searchLengthTextView: TextView = v.title_length
        val searchVoiceTextView: TextView = v.title_voice
        val searchButton: Button = v.search_buttn
        val detailButton: Button = v.detail_button
        val detailLayout: LinearLayout = v.details_profile
        val pieChart: PieChart = v.pieChartExample
        val genderChart: PieChart = v.gender_pie_chart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_result_artist, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist =  artistList[position]
        if (position == 0) {
            // タイトル設定
            holder.mainLayout.visibility = View.GONE
            holder.titleLayout.visibility = View.VISIBLE
            when (artist.name) {
                "急上昇", "おすすめ" -> {
                    holder.titleTextView.text = artist.name
                    holder.searchNameTextView.visibility = View.GONE
                    holder.searchButton.visibility = View.GONE
                }
                else -> {
                    if (artist.name != "") {
                        holder.searchNameTextView.text = "アーティスト名：" + artist.name
                    } else {
                        holder.searchNameTextView.visibility = View.GONE
                    }
                }
            }
            if (artist.gender != 0) {
                holder.searchGenderTextView.text = UserInfoChangeListUtil.changeGender(artist.gender)
            } else {
                holder.searchGenderTextView.visibility = View.GONE
            }
            if (artist.genre1 != 0) {
                holder.searchGenre1TextView.text = UserInfoChangeListUtil.changeGenre1(artist.genre1)
            } else {
                holder.searchGenre1TextView.visibility = View.GONE
            }
            if (artist.genre2 != 0) {
                holder.searchGenre2TextView.text = UserInfoChangeListUtil.changeGenre2(artist.genre1, artist.genre2)
            } else {
                holder.searchGenre2TextView.visibility = View.GONE
            }
            if (artist.length != 0) {
                holder.searchLengthTextView.text = UserInfoChangeListUtil.changeLength(artist.length)
            } else {
                holder.searchLengthTextView.visibility = View.GONE
            }
            if (artist.voice != 0) {
                holder.searchVoiceTextView.text = UserInfoChangeListUtil.changeVoice(artist.voice)
            } else {
                holder.searchVoiceTextView.visibility = View.GONE
            }
            if (artist.lyrics != 0) {
                holder.searchLyricTextView.text = UserInfoChangeListUtil.changeLyrics(artist.lyrics)
            } else {
                holder.searchLyricTextView.visibility = View.GONE
            }
            holder.searchButton.setOnClickListener { view ->
                listener.onClick(view)
            }
        } else {
            // アーティスト情報設定
            holder.mainLayout.visibility = View.VISIBLE
            holder.titleLayout.visibility = View.GONE

            // アーティスト名
            holder.nameTextView.text = artist.name
            // 性別
            holder.genderTextView.text = UserInfoChangeListUtil.changeGender(artist.gender)
            if (artist.gender == 1) {
                holder.genderTextView.setTextColor(Color.BLUE)
            } else {
                holder.genderTextView.setTextColor(Color.RED)
            }
            // 声の高さ
            holder.voiceRatingBar.rating = artist.voice.toFloat()
            // 曲の平均の長さ
            holder.lengthRatingBar.rating = artist.length.toFloat()
            // 歌詞の言語
            holder.lyricsRatingBar.rating = artist.lyrics.toFloat()
            // ジャンル１
            holder.genre1TextView.text = UserInfoChangeListUtil.changeGenre1(artist.genre1)
            // ジャンル２
            holder.genre2TextView.text =
                UserInfoChangeListUtil.changeGenre2(artist.genre1, artist.genre2)
            // 詳細ボタン
            val collapseAnimation =
                ResizeAnimation(holder.detailLayout, -originalHeight, originalHeight)
            val expandAnimation = ResizeAnimation(holder.detailLayout, originalHeight, 0)
            collapseAnimation.duration = 300
            expandAnimation.duration = 300
            holder.pieChart.visibility = View.GONE
            holder.genderChart.visibility = View.GONE
            holder.detailLayout.visibility = View.GONE
            holder.detailButton.text = context.getString(R.string.result_open)
            holder.detailButton.setOnClickListener {
                holder.detailLayout.clearAnimation()
                if (holder.detailLayout.visibility == View.VISIBLE) {
                    holder.detailLayout.startAnimation(collapseAnimation)
                    Handler().postDelayed(Runnable {
                        holder.pieChart.visibility = View.GONE
                        holder.genderChart.visibility = View.GONE
                        holder.detailLayout.visibility = View.GONE
                    }, 300)
                    holder.detailButton.text = context.getString(R.string.result_open)
                } else {
                    // 年齢毎の比率表示
                    holder.pieChart.also {
                        var totalValue = 0f
                        val entryList = mutableListOf<PieEntry>()
                        if (artist.generation1 != 0) {
                            entryList.add(PieEntry(artist.generation1.toFloat(), "10代"))
                            totalValue += artist.generation1.toFloat()
                        }
                        if (artist.generation2 != 0) {
                            entryList.add(PieEntry(artist.generation2.toFloat(), "20代"))
                            totalValue += artist.generation2.toFloat()
                        }
                        if (artist.generation3 != 0) {
                            entryList.add(PieEntry(artist.generation3.toFloat(), "30代"))
                            totalValue += artist.generation3.toFloat()
                        }
                        if (artist.generation4 != 0) {
                            entryList.add(PieEntry(artist.generation4.toFloat(), "40代"))
                            totalValue += artist.generation4.toFloat()
                        }
                        if (artist.generation5 != 0) {
                            entryList.add(PieEntry(artist.generation5.toFloat(), "50代"))
                            totalValue += artist.generation5.toFloat()
                        }
                        if (artist.generation6 != 0) {
                            entryList.add(PieEntry(artist.generation6.toFloat(), "60代"))
                            totalValue += artist.generation6.toFloat()
                        }
                        val data = PieDataSet(entryList, "年齢層").also { pieDataSet ->
                            val colorList = mutableListOf<Int>()
                            entryList.forEach { pieEntry ->
                                when (pieEntry.label) {
                                    "10代" -> colorList.add(context.getColor(R.color.red))
                                    "20代" -> colorList.add(context.getColor(R.color.blue))
                                    "30代" -> colorList.add(context.getColor(R.color.yellow))
                                    "40代" -> colorList.add(context.getColor(R.color.green))
                                    "50代" -> colorList.add(context.getColor(R.color.purple))
                                    "60代" -> colorList.add(context.getColor(R.color.brown))
                                }
                            }
                            pieDataSet.colors = colorList
                            pieDataSet.valueTextSize = 15f
                            pieDataSet.valueFormatter =  object: ValueFormatter() {
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
                        if (artist.user_man != 0) {
                            entryList.add(PieEntry(artist.user_man.toFloat(), "男性"))
                            totalValue += artist.user_man.toFloat()
                        }
                        if (artist.user_woman != 0) {
                            entryList.add(PieEntry(artist.user_woman.toFloat(), "女性"))
                            totalValue += artist.user_woman.toFloat()
                        }
                        val data = PieDataSet(entryList, "男女比率").also { pieDataSet ->
                            val colorList = mutableListOf<Int>()
                            entryList.forEach { pieEntry ->
                                when (pieEntry.label) {
                                    "男性" -> colorList.add(context.getColor(R.color.light_blue))
                                    "女性" -> colorList.add(context.getColor(R.color.pink))
                                }
                            }
                            pieDataSet.colors = colorList
                            pieDataSet.valueTextSize = 15f
                            pieDataSet.valueFormatter =  object: ValueFormatter() {
                                override fun getFormattedValue(value: Float): String{
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
                    Handler().postDelayed(Runnable {
                        holder.pieChart.startAnimation(anim1)
                        holder.pieChart.visibility = View.VISIBLE
                    }, 150)
                    Handler().postDelayed(Runnable {
                        holder.genderChart.startAnimation(anim2)
                        holder.genderChart.visibility = View.VISIBLE
                    }, 300)
                    holder.detailButton.text = context.getString(R.string.result_close)
                }
            }
            // 再生ボタン
            artist.preview?.let {
                if (artist.preview != "") {
                    holder.playButton.visibility = View.VISIBLE
                    holder.playButton.setOnClickListener {
                        if (holdButton != holder.playButton) {
                            holdButton?.setImageResource(R.mipmap.ic_button_music_play_32)
                            holder.playButton.setImageResource(R.mipmap.ic_button_music_pause_32)
                            holder.webView.settings.javaScriptEnabled = true
                            holder.webView.settings.domStorageEnabled = true
                            holder.webView.loadUrl(artist.preview)
                            holdButton = holder.playButton
                            Handler().postDelayed(Runnable {
                                if (holdButton == holder.playButton) {
                                    holdButton?.setImageResource(R.mipmap.ic_button_music_play_32)
                                }
                            }, 30500)
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
            if (artist.thumb != null && artist.thumb != "") {
                holder.imageView.visibility = View.VISIBLE
                Picasso.with(context)
                    .load(artist.thumb)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView)
            } else {
                holder.imageView.visibility = View.GONE
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
