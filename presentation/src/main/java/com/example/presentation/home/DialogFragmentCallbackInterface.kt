package com.example.presentation.home

import com.example.domain.model.value.ArtistConditions

/**
 * ダイアログ用コールバックリスナー
 *
 */
interface DialogFragmentCallbackInterface {
    fun callBackMethod(data: ArtistConditions)
}
