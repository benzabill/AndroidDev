package com.example.neurable.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.neurable.R
import com.example.neurable.data.bluetooth.model.BluetoothState
import com.example.neurable.data.focusscore.model.FocusScore
import com.example.neurable.ui.components.bluetooth.connect.ConnectBluetoothCard
import com.example.neurable.ui.components.bluetooth.manage.BluetoothCard
import com.example.neurable.ui.components.focus.FocusScoreCard
import com.example.neurable.ui.theme.NeurableTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeurableTheme {
                HomePage()
            }
        }
        mainActivityViewModel.initializeFocusFlow()
    }

    @Preview
    @Composable
    fun HomePage(
        modifier: Modifier = Modifier,
    ) {
        val focusScore = mainActivityViewModel.focusScoreLiveData.observeAsState()
        val bluetoothState = mainActivityViewModel.bluetoothStateLiveData.observeAsState()

        val shouldCalculateFocusScore = remember { mutableStateOf(true) }

        val bluetoothStateValue = bluetoothState.value
        if (bluetoothStateValue is BluetoothState.AvailableDevices) {
            ConnectBluetoothCard(bluetoothStateValue, { bluetoothDevice ->
                mainActivityViewModel.connectDevice(bluetoothDevice)
            })
        } else {
            Column(
                modifier = modifier
                    .padding(vertical = dimensionResource(R.dimen.padding_vertical))
                    .fillMaxWidth()
            ) {
                FocusScoreCard(
                    focusScore.value ?: FocusScore(null),
                    shouldCalculateFocusScore.value,
                    context = this@MainActivity,
                    {
                        val newToggleState = !shouldCalculateFocusScore.value
                        shouldCalculateFocusScore.value = newToggleState
                        mainActivityViewModel.toggleFocusFlow(newToggleState)
                    })
                BluetoothCard(bluetoothState.value ?: BluetoothState.Unknown, {
                    mainActivityViewModel.triggerBluetoothConnection()
                })
            }
        }
    }
}
