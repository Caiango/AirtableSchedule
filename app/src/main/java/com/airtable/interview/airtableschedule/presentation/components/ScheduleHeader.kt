package com.airtable.interview.airtableschedule.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airtable.interview.airtableschedule.models.SampleTimelineItems
import com.airtable.interview.airtableschedule.models.SampleTimelineItems.YEAR
import com.airtable.interview.airtableschedule.models.addDays
import com.airtable.interview.airtableschedule.models.formatDateShort
import java.util.Date

@Composable
fun ScheduleHeader(
    modifier: Modifier = Modifier,
    totalDays: Int,
    minDate: Date,
    baseDayWidth: Dp,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until totalDays) {
            val d = addDays(minDate, i)
            val label = formatDateShort(d)
            Box(
                modifier = Modifier
                    .width(baseDayWidth)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScheduleHeaderPreview() {
    ScheduleHeader(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        totalDays = 30,
        minDate = Date(YEAR, 1, 1),
        baseDayWidth = 56.dp
    )
}