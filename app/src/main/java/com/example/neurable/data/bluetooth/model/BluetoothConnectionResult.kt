package com.example.neurable.data.bluetooth.model

sealed class BluetoothConnectionResult {
    data class Success(val bluetoothDevice: BluetoothDevice) : BluetoothConnectionResult()
    data class Error(val bluetoothConnectionError: BluetoothConnectionError) : BluetoothConnectionResult()
}

sealed class BluetoothConnectionError {
    data object DeviceNotFound : BluetoothConnectionError()
    data class ConnectionFailed(val exception: Exception) : BluetoothConnectionError()
    data class Unknown(val exception: Exception) : BluetoothConnectionError()
}