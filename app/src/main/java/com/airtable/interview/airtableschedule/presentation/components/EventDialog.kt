package com.airtable.interview.airtableschedule.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.airtable.interview.airtableschedule.models.Event
import com.airtable.interview.airtableschedule.models.SampleTimelineItems
import com.airtable.interview.airtableschedule.models.formatDateShort
import com.airtable.interview.airtableschedule.presentation.DummyEventListener
import com.airtable.interview.airtableschedule.presentation.EventListener

@Composable
fun EventDialog(
    modifier: Modifier = Modifier,
    event: Event,
    listener: EventListener
) {
    var title by remember { mutableStateOf(event.name) }

    AlertDialog(
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {},
        title = {
            TextField(
                value = title,
                onValueChange = { title = it },
                singleLine = true
            )
        },
        text = {
            Text(
                "${formatDateShort(event.startDate)} - ${formatDateShort(event.endDate)}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    listener.updateEventTitle(
                        eventId = event.id,
                        title = title,
                    )
                }
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
@Preview
private fun EventDialogPreview() {
    EventDialog(
        modifier = Modifier.wrapContentSize(),
        event = SampleTimelineItems.timelineItems.first(),
        listener = DummyEventListener
    )
}