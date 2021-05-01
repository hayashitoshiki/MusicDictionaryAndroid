package com.example.musicdictionaryandroid.ui.home

import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions

/**
 * ダイアログ用コールバックリスナー
 *
 */
interface DialogFragmentCallbackInterface {
    fun callBackMethod(data: ArtistConditions)
}
