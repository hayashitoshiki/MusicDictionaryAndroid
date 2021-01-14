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

    private val isSearchBar = MutableLiveData<Boolean>(true)
    val mIsSearchBar : LiveData<Boolean> = isSearchBar
    private val isCategoryButton = MutableLiveData<Boolean>(true)
    val mIsCategoryButton : LiveData<Boolean> = isCategoryButton
    private val isDetailsButton = MutableLiveData<Boolean>(true)
    val mIsDetailsButton : LiveData<Boolean> = isDetailsButton
    private val isSoaringButton = MutableLiveData<Boolean>(true)
    val mIsSoaringButton : LiveData<Boolean> = isSoaringButton
    private val isRecommendButton = MutableLiveData<Boolean>(true)
    val mIsRecommendButton : LiveData<Boolean> = isRecommendButton
    private val isEnableSubmitButton = MutableLiveData<Boolean>(false)
    val mIsEnableSubmitButton : LiveData<Boolean> = isEnableSubmitButton

    /**
     * タップ可能ボタンのバリデート
     */
    fun set() {
        val count = preferenceRepository.getFavorite()

        if (count == 0) {
            isSearchBar.value = false
        }
        if (count < 3) {
            isCategoryButton.value = false
        }
        if (count < 5) {
            isDetailsButton.value = false
        }
        if (count < 7) {
            isSoaringButton.value = false
        }
        if (count < 10) {
            isRecommendButton.value = false
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
