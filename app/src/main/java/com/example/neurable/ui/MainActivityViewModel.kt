package com.example.neurable.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neurable.data.bluetooth.BluetoothConnectionUseCase
import com.example.neurable.data.bluetooth.FindAvailableDevicesUseCase
import com.example.neurable.data.bluetooth.model.BluetoothConnectionResult
import com.example.neurable.data.bluetooth.model.BluetoothDevice
import com.example.neurable.data.bluetooth.model.BluetoothState
import com.example.neurable.data.focusscore.FocusScoreUseCase
import com.example.neurable.data.focusscore.model.FocusScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val focusScoreUseCase: FocusScoreUseCase,
    private val findAvailableDevicesUseCase: FindAvailableDevicesUseCase,
    private val bluetoothConnectionUseCase: BluetoothConnectionUseCase,
) : ViewModel() {

    private var flowJob: Job? = null // We hold on to the Job so that we can cancel the flow.
    var focusScoreLiveData = MutableLiveData<FocusScore>()
    var bluetoothStateLiveData = MutableLiveData<BluetoothState>()

    fun toggleFocusFlow(shouldCalculate: Boolean): MutableLiveData<FocusScore> {
        if (shouldCalculate) {
            initializeFocusFlow()
        } else {
            flowJob?.cancel()
            flowJob = null
        }
        return focusScoreLiveData
    }

    fun initializeFocusFlow(): MutableLiveData<FocusScore> {
        flowJob = viewModelScope.launch {
            focusScoreUseCase.getFocusScoreFlow().collect { focusScore ->
                focusScoreLiveData.postValue(focusScore)
            }
        }
        return focusScoreLiveData
    }

    fun triggerBluetoothConnection() {
        viewModelScope.launch {
            bluetoothStateLiveData.postValue(
                BluetoothState.AvailableDevices(
                    findAvailableDevicesUseCase.findAvailableDevices()
                )
            )
        }
    }

    fun connectDevice(bluetoothDevice: BluetoothDevice) {
        viewModelScope.launch {
            val bluetoothConnectionResult = bluetoothConnectionUseCase.connect(bluetoothDevice)
            val newState = when (bluetoothConnectionResult) {
                is BluetoothConnectionResult.Error -> {
                    BluetoothState.Error(bluetoothConnectionResult.bluetoothConnectionError)
                }

                is BluetoothConnectionResult.Success -> {
                    BluetoothState.Connected(bluetoothConnectionResult.bluetoothDevice)
                }
            }
            bluetoothStateLiveData.postValue(newState)
            initializeFocusFlow()
        }
    }
}