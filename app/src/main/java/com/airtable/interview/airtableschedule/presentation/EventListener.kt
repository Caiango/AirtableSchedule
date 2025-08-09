package com.airtable.interview.airtableschedule.presentation

import com.airtable.interview.airtableschedule.models.Event
import java.util.Date

interface EventListener {
    fun updateDialog(event: Event?)
    fun updateEventTitle(eventId: Int, title: String)
    fun onEmptySpaceClick(startDate: Date)
}

object DummyEventListener : EventListener {
    override fun updateDialog(event: Event?) = Unit
    override fun updateEventTitle(eventId: Int, title: String) = Unit
    override fun onEmptySpaceClick(startDate: Date) = Unit
}