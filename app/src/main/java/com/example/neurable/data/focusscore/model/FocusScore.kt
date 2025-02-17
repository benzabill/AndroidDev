package com.example.neurable.data.focusscore.model

import android.content.Context
import com.example.neurable.R

data class FocusScore(val score: Int?) {
    // TODO move to strings.xml
    fun getFocusLevel(): String {
        return when (score) {
            in 0..33 -> "Your focus is Low - you should take a break."
            in 34..67 -> "Your focus is good - keep at it!"
            in 68..100 -> "Wow your focus is Extremely High. This is flow state, don't stop now!!!"
            else -> "Your score hasn't been calculated yet."
        }
    }
    fun getDisplay(context: Context): String {
        return when (score) {
            null -> context.getString(R.string.calculating)
            else -> score.toString()
        }
    }
}
