package com.example.musicdictionaryandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_result_artist.view.*
import kotlinx.android.synthetic.main.item_result_artist.view.arthist

/**
 * アーティスト検索結果画面のリサイクルビュー
 *
 * @property context コンテキスト
 * @property artistList 取得したアーティスト情報
 */
class ResultAdapter(private val context: Context, private val artistList: ArrayList<ArtistsForm>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    // 参照するviewの定義
    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nameTextView: TextView = v.arthist
        val genderTextView: TextView = v.gender
        val voiceRatingBar: RatingBar = v.voice_ratingBar
        val lengthRatingBar: RatingBar = v.lenght_ratingBar
        val lyricsRatingBar: RatingBar = v.lyric_ratingBar
        val imageView = v.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_result_artist, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Picassoを使ってURLの画像を読み込む
        val artist =  artistList[position]
        holder.nameTextView.text = artist.name
        holder.genderTextView.text = artist.gender.toString()
        holder.voiceRatingBar.rating = artist.voice.toFloat()
        holder.lengthRatingBar.rating = artist.length.toFloat()
        holder.lyricsRatingBar.rating = artist.lyrics.toFloat()
        var thumb = ""
        artist.thumb?.let{ thumb = it }
        if (thumb == "") {
            holder.imageView.visibility = View.GONE
        } else {
            holder.imageView.visibility = View.VISIBLE
            Picasso.with(context)
                .load(thumb)
                .fit()
                .centerInside()
                .into(holder.imageView)
        }

    }
}
