package com.airtable.interview.airtableschedule.repositories

import com.airtable.interview.airtableschedule.models.Event
import com.airtable.interview.airtableschedule.models.SampleTimelineItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * A store for data related to events. Currently, this just returns sample data.
 */
interface EventDataRepository {
    fun getTimelineItems(): Flow<List<Event>>
}

class EventDataRepositoryImpl : EventDataRepository {
    override fun getTimelineItems(): Flow<List<Event>> {
        return flowOf(SampleTimelineItems.timelineItems)
    }
}
