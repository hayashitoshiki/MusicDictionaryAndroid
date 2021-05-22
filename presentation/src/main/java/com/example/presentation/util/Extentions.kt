package com.example.presentation.util

import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.widget.CompoundButton
import androidx.databinding.BindingAdapter

private const val CLICKABLE_DELAY_TIME = 100L

/**
 * Extension function for [View.setOnClickListener].
 * It prevents fast clicking by user.
 */
@Suppress("UNCHECKED_CAST")
fun <T : View> T.setSafeClickListener(listener: (it: T) -> Unit) {
    setOnClickListener { view ->
        if (view == null) return@setOnClickListener
        view.isEnabled = false

        Handler().postDelayed(
            { view.isEnabled = true },
            CLICKABLE_DELAY_TIME
        )

        listener.invoke(view as T)
    }
}

/**
 * Extension function for [CompoundButton.setOnCheckedChangeListener].
 * It prevents fast clicking by user.
 */
@Suppress("UNCHECKED_CAST")
fun <T : CompoundButton> T.setSafeCheckListener(listener: (it: T, isChecked: Boolean) -> Unit) {
    setOnCheckedChangeListener { view, _ ->
        if (view == null) return@setOnCheckedChangeListener
        view.isEnabled = false

        Handler().postDelayed(
            { view.isEnabled = true },
            CLICKABLE_DELAY_TIME
        )

        listener.invoke(view as T, isChecked)
    }
}

@BindingAdapter("loadUrl")
fun WebView.setUrl(url: String?) {
    this.loadUrl(url)
}
