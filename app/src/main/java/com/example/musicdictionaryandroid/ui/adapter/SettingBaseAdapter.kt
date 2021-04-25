package com.example.musicdictionaryandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.data.database.entity.Artist

/**
 * アーティスト一覧画面(アーティストリスト)用Adapter
 *
 */
class SettingBaseAdapter(context: Context?, private var items: List<Artist>) : BaseAdapter() {

    private val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var holder: ViewHolder

    internal class ViewHolder {
        var textView: TextView? = null
        var updateButton: Button? = null
        var deleteButton: Button? = null
    }

    // Viewの生成
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            view = inflater.inflate(R.layout.list_view_setting, parent, false)
            holder = ViewHolder()
        } else {
            holder = view.tag as ViewHolder
        }

        // layoutと紐づけ
        holder.textView = view?.findViewById(R.id.name_label)
        holder.updateButton = view?.findViewById(R.id.update_button)
        holder.deleteButton = view?.findViewById(R.id.delete_button)

        // 値代入
        holder.textView?.text = items[position].name
        // アーティスト削除ボタン
        holder.deleteButton!!.setOnClickListener {
            (parent as ListView).performItemClick(it, position, R.id.delete_button.toLong())
        }
        // アーティスト編集ボタン
        holder.updateButton!!.setOnClickListener {
            (parent as ListView).performItemClick(it, position, R.id.update_button.toLong())
        }

        view!!.tag = holder
        return view
    }

    fun setData(items: List<Artist>) {
        this.items = items
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
