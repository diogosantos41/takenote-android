package com.dscoding.takenoteapp.presentation.search_notes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.TestTags
import com.dscoding.takenoteapp.presentation.common.CustomTextField
import com.dscoding.takenoteapp.ui.theme.FixSearchGrey
import com.dscoding.takenoteapp.ui.theme.White

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    showBackButton: Boolean = true,
    onBackPressed: () -> Unit = {},
    focusRequester: FocusRequester
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.search_notes_toolbar_height)),
        elevation = 0.dp,
        color = FixSearchGrey
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .testTag(TestTags.SEARCH_QUERY_TEXT_FIELD),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.notes_search_field_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = White),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                if (showBackButton) {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.notes_search_content_description_back),
                            tint = White,
                        )
                    }
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = onCloseClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.notes_search_content_description_close),
                        tint = White,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
    }
}