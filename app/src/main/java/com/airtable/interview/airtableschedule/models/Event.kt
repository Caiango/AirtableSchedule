package com.airtable.interview.airtableschedule.models

import androidx.compose.ui.graphics.Color
import com.airtable.interview.airtableschedule.presentation.randomColor
import java.util.Date

data class Event(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val name: String,
    val color: Color = randomColor.random()
)