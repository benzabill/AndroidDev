package com.example.neurable.ui.components.focus

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.neurable.R
import com.example.neurable.data.focusscore.model.FocusScore

@Composable
fun FocusScoreCard(
    focusScore: FocusScore,
    shouldCalculateFocusScore: Boolean,
    context: Context,
    onToggleFocusScore: () -> Unit,
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
            )
            .height(200.dp)
            .fillMaxWidth()
    ) {
        FocusScoreCardContent(focusScore, shouldCalculateFocusScore, context, onToggleFocusScore)
    }
}

@Composable
private fun FocusScoreCardContent(
    focusScore: FocusScore = FocusScore(31),
    shouldCalculateFocusScore: Boolean = true,
    context: Context,
    onToggleFocusScore: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding))
    ) {
        Row(
            modifier = modifier
        ) {
            val focusText = if (shouldCalculateFocusScore) {
                stringResource(
                    R.string.current_focus_score,
                    focusScore.getDisplay(context)
                )
            } else {
                stringResource(
                    R.string.last_focus_score,
                    focusScore.getDisplay(context)
                )
            }
            Text(
                text = focusText, modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding))
            )
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(onClick = {
                onToggleFocusScore()
            }) {
                val buttonText = if (shouldCalculateFocusScore) R.string.stop else R.string.start
                Text(text = stringResource(buttonText))
            }
        }
        Text(
            text = focusScore.getFocusLevel(), modifier = Modifier
                .padding(dimensionResource(R.dimen.padding))
        )
    }
}