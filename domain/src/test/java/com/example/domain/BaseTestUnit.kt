package com.example.domain

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope

/**
 * Unit Test共通メソッド定義
 */
abstract class BaseTestUnit {

    // region Coroutine関連
    @ExperimentalCoroutinesApi
    protected val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    protected val testScope = TestCoroutineScope(testDispatcher)

    // endregion

    // region observer関連
    protected val observerBoolean = mock<Observer<Boolean>>()

    // endregion
}
