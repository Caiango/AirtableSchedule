package com.airtable.interview.airtableschedule.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airtable.interview.airtableschedule.models.Event
import com.airtable.interview.airtableschedule.models.SampleTimelineItems
import com.airtable.interview.airtableschedule.models.formatDateShort
import com.airtable.interview.airtableschedule.presentation.DummyEventListener
import com.airtable.interview.airtableschedule.presentation.EventListener

@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    event: Event,
    listener: EventListener
) {
    Card(
        modifier = modifier
            .padding(horizontal = 4.dp), onClick = { listener.updateDialog(event) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(event.color),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${formatDateShort(event.startDate)} - ${formatDateShort(event.endDate)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventItemPreview() {
    EventItem(
        modifier = Modifier
            .width(100.dp)
            .height(50.dp),
        event = SampleTimelineItems.timelineItems.first(),
        listener = DummyEventListener
    )
}