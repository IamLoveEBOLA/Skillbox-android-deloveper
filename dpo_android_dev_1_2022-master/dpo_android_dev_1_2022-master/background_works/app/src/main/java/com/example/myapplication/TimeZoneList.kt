package com.example.myapplication

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.stream.Collectors
import javax.inject.Inject

class TimeZonesList @Inject constructor() {
    fun getTimeZoneList(base: OffsetBase?): List<String?>? {
        val now: LocalDateTime = LocalDateTime.now()
        return ZoneId.getAvailableZoneIds().stream()
            .map(ZoneId::of)
            .sorted(ZoneComparator())
            .map { id ->
                java.lang.String.format(
                    "(%s%s) %s",
                    base, getOffset(now, id), id.getId()
                )
            }
            .collect(Collectors.toList())
    }
    enum class OffsetBase {
        GMT, UTC
    }

    private fun getOffset(dateTime: LocalDateTime , id: ZoneId): String? {
        return dateTime
            .atZone(id)
            .offset
            .id
            .replace("Z", "+00:00")
    }
    private class ZoneComparator : Comparator<ZoneId?> {
        override fun compare(zoneId1: ZoneId? , zoneId2: ZoneId?): Int {
            val now = LocalDateTime.now()
            val offset1: ZoneOffset = now.atZone(zoneId1).offset
            val offset2: ZoneOffset = now.atZone(zoneId2).offset
            return offset1.compareTo(offset2)
        }
    }
}