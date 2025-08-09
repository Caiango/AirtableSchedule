package com.airtable.interview.airtableschedule.models

import java.util.Calendar
import java.util.Date
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

/**
 * Takes a list of [Event]s and assigns them to lanes based on start/end dates.
 */
fun assignLanes(events: List<Event>): List<List<Event>> {
    val lanes = mutableListOf<MutableList<Event>>()

    // Go through the list of events sorted by start date
    events.sortedBy { event -> event.startDate }
        .forEach { event ->
            // Attempt to assign the event to an existing lane
            val availableLane: MutableList<Event>? = lanes.find { lane ->
                lane.last().endDate < event.startDate
            }

            if (availableLane != null) {
                availableLane.add(event)
            } else {
                // Create a new lane if there are currently no free lanes to assign the event
                lanes.add(mutableListOf(event))
            }
        }
    return lanes
}

fun formatDateShort(date: Date): String {
    val calendar = Calendar.getInstance().apply { time = date }
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    return "%02d/%02d".format(day, month)
}

@OptIn(ExperimentalTime::class)
fun daysBetween(start: Date, end: Date): Int {
    val msPerDay = Duration.convert(1.0, DurationUnit.DAYS, DurationUnit.MILLISECONDS)
    val diff = (end.time - start.time).coerceAtLeast(0)
    // +0.5 to round properly before casting to int, but we use floor of days
    return (diff / msPerDay).toInt()
}

fun addDays(date: Date, days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DATE, days)
    return calendar.time
}
