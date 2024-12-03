package com.example.neurable.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neurable.data.FocusScoreUseCase
import com.example.neurable.data.model.FocusScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val focusScoreUseCase: FocusScoreUseCase
) : ViewModel() {

    var isCalculateFocusScore = MutableLiveData(true)

    var flowJob: Job? = null
    var focusScoreLiveData = MutableLiveData<FocusScore>()

    fun toggleFlow(shouldCalculate: Boolean): MutableLiveData<FocusScore> {
        if (shouldCalculate) {
            initFlow()
        } else {
            flowJob?.cancel()
            flowJob = null
        }
        return focusScoreLiveData
    }

    fun initFlow(): MutableLiveData<FocusScore> {
        flowJob = viewModelScope.launch {
            focusScoreUseCase.getFocusScoreFlow().collect { focusScore ->
                focusScoreLiveData.postValue(focusScore)
            }
        }
        return focusScoreLiveData
    }
}