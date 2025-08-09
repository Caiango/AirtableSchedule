package com.airtable.interview.airtableschedule.models

import java.util.Date

data class Event(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val name: String
)