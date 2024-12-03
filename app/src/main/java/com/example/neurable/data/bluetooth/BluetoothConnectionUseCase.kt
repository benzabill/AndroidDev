package com.example.neurable.data.bluetooth

import com.example.neurable.data.bluetooth.model.BluetoothConnectionResult
import com.example.neurable.data.bluetooth.model.BluetoothDevice
import kotlinx.coroutines.delay
import javax.inject.Inject

class BluetoothConnectionUseCase @Inject constructor() {
    suspend fun connect(bluetoothDevice: BluetoothDevice): BluetoothConnectionResult {
        delay(500) // Simulate connecting to a device
        return BluetoothConnectionResult.Success(bluetoothDevice)
    }
}