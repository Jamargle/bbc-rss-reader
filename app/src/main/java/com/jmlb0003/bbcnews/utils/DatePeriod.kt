package com.jmlb0003.bbcnews.utils

sealed class DatePeriod {
    object JustNow : DatePeriod()
    class MinutesAgo(val timeGap: Int) : DatePeriod()
    class HoursAgo(val timeGap: Int) : DatePeriod()
    class FullTimeDate(val dateToShow: String) : DatePeriod()
}
