package com.example.presentation.util.transition

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * アコーディオンアニメーション
 *
 * @param view 対象のView
 * @param addHeight 動かす高さ
 * @param startHeight 元の高さ
 */
class ResizeAnimation(var view: View, private val addHeight: Int, private var startHeight: Int) : Animation() {

    override fun applyTransformation(
        interpolatedTime: Float,
        t: Transformation?
    ) {
        val newHeight = (startHeight + addHeight * interpolatedTime).toInt()
        view.layoutParams.height = newHeight
        view.requestLayout()
    }

    override fun initialize(
        width: Int,
        height: Int,
        parentWidth: Int,
        parentHeight: Int
    ) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}
