package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*

/**
 * HOME画面_UIロジック
 *
 * @property preferenceRepository
 */
class HomeViewModel(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    val searchText = MutableLiveData("")

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

    /**
     * タップ可能ボタンのバリデート
     */
    init {
        val count = preferenceRepository.getFavorite()

        if (count == 0) {
            _isEnableSearchBar.value = false
        }
        if (count < 3) {
            _isEnableCategoryButton.value = false
        }
        if (count < 5) {
            _isEnableDetailsButton.value = false
        }
        if (count < 7) {
            _isEnableSoaringButton.value = false
        }
        if (count < 10) {
            _isEnableRecommendButton.value = false
        }
    }

    fun getArtist(): Artist {
        val name = searchText.value.toString()
        val gender = Gender.getEnumByValue(0)
        val length = Length(0)
        val voice = Voice(0)
        val lyrics = Lyrics(0)
        val genre1 = Genre1(0)
        val genre2 = Genre2(0)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    /**
     * 検索ボタン活性・非活性制御
     *
     * @param count 検索バーの入力文字数
     */
    fun changeSubmitButton(count: Int) {
        _isEnableSubmitButton.value = count != 0
    }
}
