package com.airtable.interview.airtableschedule.presentation.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airtable.interview.airtableschedule.models.Dialog
import com.airtable.interview.airtableschedule.presentation.TimelineViewModel

@Composable
fun TimelineScreen(
    viewModel: TimelineViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val dialog: Dialog? = viewModel.informativeDialog.collectAsStateWithLifecycle().value

    dialog?.let {
        EventDialog(
            modifier = Modifier.wrapContentSize(),
            event = dialog.event,
            listener = viewModel
        )
    }

    TimelineView(uiState.events, listener = viewModel)
}

@Preview(showBackground = true)
@Composable
private fun TimelineScreenPreview() {
    TimelineScreen()
}