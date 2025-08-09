package com.airtable.interview.airtableschedule.models

import java.util.Date

object SampleTimelineItems {
    const val YEAR = 2025 - 1900
    var timelineItems: List<Event> = listOf(
        Event(
            1,
            Date(YEAR, 1, 1),
            Date(YEAR, 1, 5),
            "First item"
        ),
        Event(
            2,
            Date(YEAR, 1, 2),
            Date(YEAR, 1, 8),
            "Second item"
        ),
        Event(
            3,
            Date(YEAR, 1, 6),
            Date(YEAR, 1, 13),
            "Another item"
        ),
        Event(
            4,
            Date(YEAR, 1, 14),
            Date(YEAR, 1, 14),
            "Another item"
        ),
        Event(
            5,
            Date(YEAR, 2, 1),
            Date(YEAR, 2, 15),
            "Third item"
        ),
        Event(
            6,
            Date(YEAR, 1, 12),
            Date(YEAR, 2, 16),
            "Fourth item with a super long name"
        ),
        Event(
            7,
            Date(YEAR, 2, 1),
            Date(YEAR, 2, 2),
            "Fifth item with a super long name"
        ),
        Event(
            8,
            Date(YEAR, 1, 3),
            Date(YEAR, 1, 5),
            "First item"
        ),
        Event(
            9,
            Date(YEAR, 1, 4),
            Date(YEAR, 1, 8),
            "Second item"
        ),
        Event(
            10,
            Date(YEAR, 1, 6),
            Date(YEAR, 1, 13),
            "Another item"
        ),
        Event(
            11,
            Date(YEAR, 1, 9),
            Date(YEAR, 1, 9),
            "Another item"
        ),
        Event(
            12,
            Date(YEAR, 2, 1),
            Date(YEAR, 2, 15),
            "Third item"
        ),
        Event(
            13,
            Date(YEAR, 1, 12),
            Date(YEAR, 2, 16),
            "Fourth item with a super long name"
        ),
        Event(
            14,
            Date(YEAR, 2, 1),
            Date(YEAR, 2, 1),
            "Fifth item with a super long name"
        )
    )
}