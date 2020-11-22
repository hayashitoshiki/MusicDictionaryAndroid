package com.example.musicdictionaryandroid.ui.adapter

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
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

    // 参照するviewの定義
    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nameTextView: TextView = v.arthist
        val genderTextView: TextView = v.gender
        val voiceRatingBar: RatingBar = v.voice_ratingBar
        val lengthRatingBar: RatingBar = v.lenght_ratingBar
        val lyricsRatingBar: RatingBar = v.lyric_ratingBar
        val imageView: ImageView = v.imageView
        val playButton: ImageButton = v.play
        val webView: WebView = v.webview
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
        // アーティスト名
        holder.nameTextView.text = artist.name
        // 声の高さ
        holder.voiceRatingBar.rating = artist.voice.toFloat()
        // 曲の平均の長さ
        holder.lengthRatingBar.rating = artist.length.toFloat()
        // 歌詞の言語
        holder.lyricsRatingBar.rating = artist.lyrics.toFloat()

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

        // 性別
        if (artist.gender.toString() == "1") {
            holder.genderTextView.text = "男"
            holder.genderTextView.setTextColor(Color.BLUE)
        } else {
            holder.genderTextView.text = "女"
            holder.genderTextView.setTextColor(Color.RED)
        }

        // サムネイル
        var thumb = ""
        artist.thumb?.let{ thumb = it }
        if (thumb == "") {
            holder.imageView.visibility = View.GONE
        } else {
            holder.imageView.visibility = View.VISIBLE
            Picasso.with(context)
                .load(thumb)
                .fit()
                .centerCrop()
                .into(holder.imageView)
        }

    }
}
