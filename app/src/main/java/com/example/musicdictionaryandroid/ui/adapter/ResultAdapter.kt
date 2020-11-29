package com.example.musicdictionaryandroid.ui.adapter

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
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

    var holdButton: ImageButton? = null
    private lateinit var  listener: View.OnClickListener

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
        val searchNameTextView: TextView = v.title_name
        val searchGenderTextView: TextView = v.title_gender
        val searchGenre1TextView: TextView = v.title_genre1
        val searchGenre2TextView: TextView = v.title_genre2
        val searchLyricTextView: TextView = v.title_lyric
        val searchLengthTextView: TextView = v.title_length
        val searchVoiceTextView: TextView = v.title_voice
        val searchButton: Button = v.search_buttn
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
            if (artist.name != "") {
                holder.searchNameTextView.text = "アーティスト名：" + artist.name
            } else {
                holder.searchNameTextView.visibility = View.GONE
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
            holder.genre2TextView.text = UserInfoChangeListUtil.changeGenre2(artist.genre1, artist.genre2)
            // 再生ボタン
            artist.preview?.let{ if (artist.preview != "") {
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
            }}?: run {
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

    fun setOnItemClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }
}
