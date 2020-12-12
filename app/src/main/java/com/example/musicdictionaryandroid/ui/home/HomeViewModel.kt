package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp

/**
 * HOME画面_UIロジック
 *
 * @property preferenceRepository
 */
class HomeViewModel(
    private val preferenceRepository: PreferenceRepositoryImp
) : ViewModel() {

    val artistsFrom = ArtistsForm()
    val searchText = MutableLiveData<String>("")

    val isSearchBar = MutableLiveData<Boolean>(true)
    val isSubmitButton = MutableLiveData<Boolean>(true)
    val isCategoryButton = MutableLiveData<Boolean>(true)
    val isDetailsButton = MutableLiveData<Boolean>(true)
    val isSoaringButton = MutableLiveData<Boolean>(true)
    val isRecommendButton = MutableLiveData<Boolean>(true)
    val isEnableSubmitButton = MutableLiveData<Boolean>(false)
    val isEnableCategoryButton = MutableLiveData<Boolean>(false)
    val isEnableDetailsButton = MutableLiveData<Boolean>(false)
    val isEnableSoaringButton = MutableLiveData<Boolean>(false)
    val isEnableRecommendButton = MutableLiveData<Boolean>(false)

    /**
     * タップ可能ボタンのバリデート
     */
    fun set() {
        val count = preferenceRepository.getFavorite()

        if (count == 0) {
            isSubmitButton.value = false
            isSearchBar.value = false
        }
        if (count < 3) {
            isCategoryButton.value = false
            isEnableCategoryButton.value = true
        }
        if (count < 5) {
            isDetailsButton.value = false
            isEnableDetailsButton.value = true
        }
        if (count < 7) {
            isSoaringButton.value = false
            isEnableSoaringButton.value = true
        }
        if (count < 10) {
            isRecommendButton.value = false
            isEnableRecommendButton.value = true
        }
    }

    /**
     * 検索ボタン活性・非活性制御
     *
     * @param count 検索バーの入力文字数
     */
    fun changeSubmitButton(count: Int) {
        isEnableSubmitButton.value = count != 0
        artistsFrom.name = searchText.value.toString()
    }
}
