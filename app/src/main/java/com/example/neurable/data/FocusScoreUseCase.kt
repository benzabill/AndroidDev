package com.example.neurable.data

import com.example.neurable.data.model.FocusScore
import com.example.neurable.data.repository.FocusScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FocusScoreUseCase @Inject constructor(
    private val userRepository: FocusScoreRepository
) {
    fun getFocusScoreFlow(): Flow<FocusScore> {
        return userRepository.focusScoreFlow()
    }
}