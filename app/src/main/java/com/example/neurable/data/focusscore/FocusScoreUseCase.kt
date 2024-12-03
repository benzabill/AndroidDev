package com.example.neurable.data.focusscore

import com.example.neurable.data.focusscore.model.FocusScore
import com.example.neurable.data.focusscore.repository.FocusScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FocusScoreUseCase @Inject constructor(
    private val focusScoreRepository: FocusScoreRepository
) {
    fun getFocusScoreFlow(): Flow<FocusScore> {
        return focusScoreRepository.focusScoreFlow()
    }
}