package com.example.neurable.data.focusscore

import com.example.neurable.data.focusscore.model.FocusScore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FocusScoreUseCase @Inject constructor(
    private val userRepository: FocusScoreRepository
) {
    fun getFocusScoreFlow(): Flow<FocusScore> {
        return userRepository.focusScoreFlow()
    }
}