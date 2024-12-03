package com.example.neurable.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeurableTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Neurable")
                            }
                        )
                    },
                ) { innerPadding ->
                    HomePage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
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
                BluetoothCard(bluetoothState.value ?: BluetoothState.Disconnected, {
                    mainActivityViewModel.triggerBluetoothConnection()
                })
                if (bluetoothState.value is BluetoothState.Connected) {
                    FocusScoreCard(
                        focusScore.value ?: FocusScore(null),
                        shouldCalculateFocusScore.value,
                        context = this@MainActivity,
                        {
                            val newToggleState = !shouldCalculateFocusScore.value
                            shouldCalculateFocusScore.value = newToggleState
                            mainActivityViewModel.toggleFocusFlow(newToggleState)
                        })
                }
            }
        }
    }
}
