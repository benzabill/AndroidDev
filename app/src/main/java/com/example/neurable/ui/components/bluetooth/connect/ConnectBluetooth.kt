package com.example.neurable.ui.components.bluetooth.connect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.neurable.R
import com.example.neurable.data.bluetooth.model.BluetoothDevice
import com.example.neurable.data.bluetooth.model.BluetoothState

@Composable
fun ConnectBluetoothCard(
    bluetoothState: BluetoothState.AvailableDevices,
    onToggleFocusScore: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.select_device), modifier = Modifier
                .padding(dimensionResource(R.dimen.padding))
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding))
        ) {
            ConnectBluetoothCardContent(bluetoothState, onToggleFocusScore, modifier)
        }
    }
}

@Preview
@Composable
private fun ConnectBluetoothCardContent(
    bluetoothState: BluetoothState.AvailableDevices = BluetoothState.AvailableDevices(
        listOf(BluetoothDevice("Device 1", "1"))
    ),
    connectDevice: (BluetoothDevice) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(bluetoothState.bluetoothDevices) { bluetoothDevice ->
                Row(
                    modifier = modifier
                        .padding(
                            vertical = dimensionResource(R.dimen.padding_vertical),
                            horizontal = dimensionResource(R.dimen.padding_horizontal)
                        )
                ) {
                    Text(
                        text = bluetoothDevice.name, modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ElevatedButton(modifier = modifier, onClick = {
                        connectDevice(bluetoothDevice)
                    }) {
                        Text(text = stringResource(R.string.connect_device))
                    }
                }
            }
        }
    }
}