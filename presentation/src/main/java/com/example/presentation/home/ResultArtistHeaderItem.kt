package com.example.presentation.home

import android.content.Context
import android.view.View
import com.example.domain.model.value.ArtistConditions
import com.example.presentation.R
import com.example.presentation.databinding.ItemResultArtistHeaderBinding
import com.example.presentation.util.MessageUtilImp
import com.xwray.groupie.databinding.BindableItem

/**
 * アーティスト検索結果（ヘッダー部分）アイテム
 */
class ResultArtistHeaderItem(
    private val item: ArtistConditions,
    private val context: Context,
    private val listener: View.OnClickListener
) : BindableItem<ItemResultArtistHeaderBinding>() {

    override fun getLayout(): Int = R.layout.item_result_artist_header

    override fun bind(viewBinding: ItemResultArtistHeaderBinding, position: Int) {
        viewBinding.item = item
        viewBinding.state = ResultArtistHeaderState(MessageUtilImp(context))
        viewBinding.searchButton.setOnClickListener { view ->
            listener.onClick(view)
        }
    }
}
