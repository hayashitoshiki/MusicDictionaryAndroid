package com.example.musicdictionaryandroid.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import java.util.ArrayList

/**
 * CustomListViewの生成用クラス
 */

internal class CustomBaseAdapter(context: Context?, private val resourcedId: Int, items: ArrayList<ArtistsForm>) : BaseAdapter() {
    private val inflater: LayoutInflater
    private val items: ArrayList<ArtistsForm>
    private lateinit var holder: ViewHolder

    internal class ViewHolder {
        var textView: TextView? = null
        var genre1TextView: TextView? = null
        var genre2TextView: TextView? = null
        var genderTextView: TextView? = null
        var voiceRatingBar: RatingBar? = null
        var lengthRatingBar: RatingBar? = null
        var lyricsRatingBar: RatingBar? = null
    }

    init {
        this.inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.items = items
    }

    // Viewの生成
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            view = inflater.inflate(R.layout.activity_custom_base_adapter, null)
            holder = ViewHolder()
        } else {
            holder = view.tag as ViewHolder
        }

        holder.textView = view?.findViewById(R.id.arthist)
        holder.genre1TextView = view?.findViewById(R.id.genre1)
        holder.genre2TextView = view?.findViewById(R.id.genre2)
        holder.genderTextView = view?.findViewById(R.id.gender)
        holder.voiceRatingBar = view?.findViewById(R.id.voice_ratingBar)
        holder.lengthRatingBar = view?.findViewById(R.id.lenght_ratingBar)
        holder.lyricsRatingBar = view?.findViewById(R.id.lyric_ratingBar)

        holder.textView?.text = items[position].name
        holder.genre1TextView?.text = items[position].genre1
        holder.genre2TextView?.text = items[position].genre2
        holder.genderTextView?.text = items[position].gender.toString()
        holder.voiceRatingBar?.rating = items[position].voice.toFloat()
        holder.lengthRatingBar?.rating = items[position].length.toFloat()
        holder.lyricsRatingBar?.rating = items[position].lyrics.toFloat()
        view!!.tag = holder

        if (items[position].gender.toString() == "1") {
            holder.genderTextView?.text = "男"
            holder.genderTextView?.setTextColor(Color.BLUE)
        } else {
            holder.genderTextView?.text = "女"
            holder.genderTextView?.setTextColor(Color.RED)
        }
        return view
    }

    // ListViewの数
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
