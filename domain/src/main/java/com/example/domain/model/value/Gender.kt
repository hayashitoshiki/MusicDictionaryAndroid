package com.example.domain.model.value

/**
 * 性別情報
 */
enum class Gender(val value: Int) {
    MAN(1),
    WOMAN(2);
    companion object {
        fun getEnumByValue(value: Int): Gender {
            return when (value) {
                1 -> MAN
                2 -> WOMAN
                else -> throw IllegalArgumentException("存在しない性別です")
            }
        }
    }
}
