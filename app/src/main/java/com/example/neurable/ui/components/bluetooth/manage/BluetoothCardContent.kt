package com.example.neurable.ui.components.bluetooth.manage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.neurable.data.bluetooth.model.BluetoothState

@Composable
fun BluetoothCard(
    bluetoothState: BluetoothState,
    triggerBluetoothConnection: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(
                vertical = dimensionResource(R.dimen.padding_vertical),
                horizontal = dimensionResource(R.dimen.padding_horizontal)
            ).wrapContentSize()
    ) {
        BluetoothCardContent(bluetoothState, triggerBluetoothConnection, modifier)
    }
}

@Preview
@Composable
private fun BluetoothCardContent(
    bluetoothState: BluetoothState = BluetoothState.Disconnected,
    triggerBluetoothConnection: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding))
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
        ) {
            when (bluetoothState) {
                is BluetoothState.Connected -> {
                    Row(
                        modifier = modifier
                    ) {
                        Text(
                            text = stringResource(
                                R.string.connected,
                            ), modifier = Modifier
                                .padding(dimensionResource(R.dimen.padding))
                        )
                        Text(
                            text = bluetoothState.bluetoothDevice.name, modifier = Modifier
                                .padding(dimensionResource(R.dimen.padding))
                        )
                    }
                }

                BluetoothState.Disconnected -> {
                    Text(
                        text = stringResource(R.string.not_connected), modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ElevatedButton(onClick = {
                        triggerBluetoothConnection()
                    }) {
                        Text(text = stringResource(R.string.connect_device))
                    }
                }

                is BluetoothState.AvailableDevices -> {
                    // No op
                }

                is BluetoothState.Error -> {
                    // No op
                }
            }
        }
    }
}