package com.example.neurable.data.focusscore.repository

import com.example.neurable.data.focusscore.model.FocusScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class FocusScoreFlowTest {

    lateinit var focusScoreRepository: FocusScoreRepository
    private fun getFocusScoreFlow(): Flow<FocusScore> {
        focusScoreRepository = FocusScoreRepository()
        return focusScoreRepository.focusScoreFlow()
    }

    @Test
    fun `FocusScoreFlow emits values in expected range`() = runTest {
        // Run the flow under test
        val results = mutableListOf<FocusScore>()

        getFocusScoreFlow()
            .take(3)
            .collect { focusScore ->
                results.add(focusScore)
            }

        // Assertions
        assertTrue("Should emit 3 values", results.size == 3)
        results.forEach { focusScore ->
            assertTrue("Value should be in the range 0-100", focusScore.score in 0..100)
        }
    }
}