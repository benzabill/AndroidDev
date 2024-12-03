package com.example.neurable.data.bluetooth.model

sealed class BluetoothState {
    data object Disconnected : BluetoothState()
    data class Connected(val bluetoothDevice: BluetoothDevice) : BluetoothState()
    data class Error(val bluetoothConnectionError: BluetoothConnectionError) : BluetoothState()
    data class AvailableDevices(val bluetoothDevices: List<BluetoothDevice>) : BluetoothState()
}