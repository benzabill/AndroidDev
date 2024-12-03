package com.example.neurable.data.bluetooth

import com.example.neurable.data.bluetooth.model.BluetoothDevice
import kotlinx.coroutines.delay
import javax.inject.Inject

class FindAvailableDevicesUseCase @Inject constructor() {
    suspend fun findAvailableDevices(): List<BluetoothDevice> {
        delay(500) // Simulate looking for devices
        return List(20) { index ->
            BluetoothDevice("Device ${index + 1}", "${index + 1}")
        }
    }
}