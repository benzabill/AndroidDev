package com.example.neurable.data.repository

import com.example.neurable.data.model.FocusScore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FocusScoreRepository @Inject constructor(
//    private val remoteUserRepository: RemoteUserRepository
) {
    fun focusScoreFlow(): Flow<FocusScore> = flow {
        while (true) {
            emit(generateRandomFocusScore())
            delay(1000L)
        }
    }

    private fun generateRandomFocusScore(): FocusScore {
        return FocusScore((Math.random() * 100).toInt())
    }
}