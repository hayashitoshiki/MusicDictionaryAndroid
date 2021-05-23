package com.example.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/**
 * Unit Test共通メソッド定義
 */
abstract class BaseTestUnit {

    // region Coroutine関連
    @ExperimentalCoroutinesApi
    protected val testDispatcher = TestCoroutineDispatcher()

    // endregion
}
