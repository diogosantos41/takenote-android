package com.dscoding.takenoteapp.presentation.list_notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.ui.theme.DividerGrey

@Composable
fun GreetingSection(visible: Boolean = true) {
    if (visible) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    0.dp,
                    dimensionResource(R.dimen.notes_margin_vertical_greeting_section),
                    0.dp,
                    0.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.notes_greeting_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(id = R.string.notes_greeting_message),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.notes_margin_vertical_greeting_section)))
                Divider(color = DividerGrey)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}