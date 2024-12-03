package com.example.neurable.data.focusscore.repository

import com.example.neurable.data.focusscore.model.FocusScore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FocusScoreRepository @Inject constructor(
) {
    fun focusScoreFlow(): Flow<FocusScore> = flow {
        while (true) {
            emit(generateRandomFocusScore())
            delay(5000L)
        }
    }

    private fun generateRandomFocusScore(): FocusScore {
        return FocusScore((Math.random() * 100).toInt())
    }
}