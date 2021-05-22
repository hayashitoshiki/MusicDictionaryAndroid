package com.example.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.LocalUserRepository
import com.example.domain.model.value.ArtistConditions

/**
 * HOME画面_UIロジック
 */
class HomeViewModel(
    private val localUserRepository: LocalUserRepository
) : ViewModel() {

    // 入力項目
    val searchText = MutableLiveData("")

    // ボタンバリデート
    private val _isEnableSearchBar = MutableLiveData(true)
    val isEnableSearchBar: LiveData<Boolean> = _isEnableSearchBar
    private val _isEnableCategoryButton = MutableLiveData(true)
    val isEnableCategoryButton: LiveData<Boolean> = _isEnableCategoryButton
    private val _isEnableDetailsButton = MutableLiveData(true)
    val isEnableDetailsButton: LiveData<Boolean> = _isEnableDetailsButton
    private val _isEnableSoaringButton = MutableLiveData(true)
    val isEnableSoaringButton: LiveData<Boolean> = _isEnableSoaringButton
    private val _isEnableRecommendButton = MutableLiveData(true)
    val isEnableRecommendButton: LiveData<Boolean> = _isEnableRecommendButton
    private val _isEnableSubmitButton = MutableLiveData(false)
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    init {
        val count = localUserRepository.getFavorite()
        _isEnableSearchBar.value = count != 0
        _isEnableCategoryButton.value = count >= 3
        _isEnableDetailsButton.value = count >= 5
        _isEnableSoaringButton.value = count >= 7
        _isEnableRecommendButton.value = count >= 10
    }

    // アーティスト取得
    fun getArtist(): ArtistConditions {
        val name = searchText.value.toString()
        return ArtistConditions(name, null, null, null, null, null, null)
    }

    // 検索ボタン活性・非活性制御
    fun changeSubmitButton(count: Int) {
        _isEnableSubmitButton.value = count != 0
    }
}
