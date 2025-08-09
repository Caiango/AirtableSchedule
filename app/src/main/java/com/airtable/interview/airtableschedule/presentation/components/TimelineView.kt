package com.airtable.interview.airtableschedule.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airtable.interview.airtableschedule.models.Event
import com.airtable.interview.airtableschedule.models.SampleTimelineItems
import com.airtable.interview.airtableschedule.models.addDays
import com.airtable.interview.airtableschedule.models.assignLanes
import com.airtable.interview.airtableschedule.models.daysBetween
import com.airtable.interview.airtableschedule.presentation.DummyEventListener
import com.airtable.interview.airtableschedule.presentation.EventListener
import java.util.Date

@Composable
fun TimelineView(
    events: List<Event>,
    listener: EventListener
) {
    if (events.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No events", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    val minDate: Date = events.minByOrNull { it.startDate }!!.startDate
    val maxDate: Date = events.maxByOrNull { it.endDate }!!.endDate
    val totalDays = (daysBetween(minDate, maxDate) + 1).coerceAtLeast(1)

    val lanes = assignLanes(events)

    val horizontalScrollState = rememberScrollState()

    val baseDayWidth = 56.dp
    val laneWidth = baseDayWidth * totalDays

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        ScheduleHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .horizontalScroll(horizontalScrollState),
            totalDays = totalDays,
            minDate = minDate,
            baseDayWidth = baseDayWidth
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(lanes) { _, laneEvents: List<Event> ->
                Box(
                    modifier = Modifier
                        .horizontalScroll(horizontalScrollState)
                        .width(laneWidth)
                        .height(72.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE0E0E7))
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                val dayOffset = (offset.x / baseDayWidth.toPx()).toInt()
                                val eventDate = addDays(minDate, dayOffset)
                                listener.onEmptySpaceClick(eventDate)
                            }
                        }
                ) {
                    laneEvents.forEach { event ->
                        val startOffsetDays = daysBetween(minDate, event.startDate)
                        val durationDays: Int =
                            (daysBetween(event.startDate, event.endDate) + 1).coerceAtLeast(1)

                        val offsetDp = baseDayWidth * startOffsetDays
                        val widthDp = baseDayWidth * durationDays

                        EventItem(
                            event = event,
                            modifier = Modifier
                                .offset(
                                    x = offsetDp,
                                    y = 12.dp
                                )
                                .width(widthDp)
                                .height(48.dp),
                            listener = listener
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineViewPreview() {
    TimelineView(
        events = SampleTimelineItems.timelineItems,
        listener = DummyEventListener
    )
}