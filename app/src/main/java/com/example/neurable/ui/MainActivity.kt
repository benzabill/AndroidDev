package com.example.neurable.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.neurable.R
import com.example.neurable.data.model.FocusScore
import com.example.neurable.ui.theme.NeurableTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeurableTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
        mainActivityViewModel.initFlow()
    }

    @Preview
    @Composable
    fun MyApp(
        modifier: Modifier = Modifier,
        names: List<String> = listOf("Ben", "Compose")
    ) {
        val focusScore = mainActivityViewModel.focusScoreLiveData.observeAsState()
        val isCalculateFocusScore = mainActivityViewModel.isCalculateFocusScore.observeAsState()

        val shouldCalculateFocusScore = remember { mutableStateOf(true) }

        if (focusScore.value == null || isCalculateFocusScore.value == null) {
            return
        }
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            FocusScoreCard(focusScore.value!!, {
                val newToggleState = !shouldCalculateFocusScore.value
                shouldCalculateFocusScore.value = newToggleState
                mainActivityViewModel.toggleFlow(newToggleState)
            })
        }
    }

    @Composable
    fun FocusScoreCard(
        focusScore: FocusScore,
        onToggleFocusScore: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .height(150.dp)
        ) {
            CardContent(focusScore, onToggleFocusScore)
        }
    }

    @Preview
    @Composable
    private fun CardContent(
        focusScore: FocusScore = FocusScore(31),
        onToggleFocusScore: () -> Unit = {},
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(
                text = "Focus Score: ${focusScore.score}", modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            )
            ElevatedButton(onClick = {
                onToggleFocusScore() // Pass the callback up the chain
            }) {
                val buttonText = R.string.toggle
                Text(text = stringResource(buttonText))
            }
        }
    }
}
