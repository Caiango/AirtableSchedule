package com.airtable.interview.airtableschedule.presentation

import com.airtable.interview.airtableschedule.models.Event

interface EventListener {
    fun updateDialog(event: Event?)
    fun updateEventTitle(eventId: Int, title: String)
}

object DummyEventListener : EventListener {
    override fun updateDialog(event: Event?) = Unit
    override fun updateEventTitle(eventId: Int, title: String) = Unit
}