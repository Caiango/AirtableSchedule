package com.airtable.interview.airtableschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airtable.interview.airtableschedule.models.Dialog
import com.airtable.interview.airtableschedule.models.Event
import com.airtable.interview.airtableschedule.repositories.EventDataRepository
import com.airtable.interview.airtableschedule.repositories.EventDataRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

/**
 * ViewModel responsible for managing the state of the timeline screen.
 */
class TimelineViewModel : ViewModel(), EventListener {
    private val eventDataRepository: EventDataRepository = EventDataRepositoryImpl()

    private val _informativeDialog = MutableStateFlow<Dialog?>(null)
    val informativeDialog: StateFlow<Dialog?> = _informativeDialog

    private val _newEventStartDate = MutableStateFlow<Date?>(null)
    val newEventStartDate: StateFlow<Date?> = _newEventStartDate

    private val _events = MutableStateFlow<List<Event>>(emptyList())

    val uiState: StateFlow<TimelineUiState> =
        _events.map(::TimelineUiState)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TimelineUiState())

    init {
        viewModelScope.launch {
            eventDataRepository.getTimelineItems().collect { items ->
                _events.value = items
            }
        }
    }

    override fun updateDialog(event: Event?) {
        event?.let {
            _informativeDialog.value = Dialog(event = event)
        } ?: run { _informativeDialog.value = null }
    }

    override fun updateEventTitle(eventId: Int, title: String) {
        _events.value = _events.value.map {
            if (it.id == eventId) {
                it.copy(name = title)
            } else {
                it
            }
        }
        _informativeDialog.value = null
    }

    override fun onEmptySpaceClick(startDate: Date) {
        _newEventStartDate.value = startDate
    }

    fun createEvent(title: String, startDate: Date, endDate: Date) {
        val newId = (_events.value.maxOfOrNull { it.id } ?: 0) + 1
        val newEvent = Event(
            id = newId,
            name = title,
            startDate = startDate,
            endDate = endDate
        )
        _events.value = _events.value.plus(newEvent)
        _newEventStartDate.value = null
    }

    fun cancelNewEvent() {
        _newEventStartDate.value = null
    }
}