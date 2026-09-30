package org.setu.placemark

import java.util.concurrent.atomic.AtomicLong

class PlacedMarkList {

    private val placedMarks = ArrayList<PlacedMark>()
    private val lastId = AtomicLong(0L)

    fun findAll(): List<PlacedMark> {
        return placedMarks
    }

    fun create(mark: PlacedMark) {
        mark.id = lastId.incrementAndGet()
        placedMarks.add(mark)
    }

    fun update(mark: PlacedMark): Boolean {
        val index = placedMarks.indexOfFirst { it.id == mark.id }

        return if (index != -1) {
            placedMarks[index] = placedMarks[index].copy(
                title = mark.title,
                desc = mark.desc,
                x = mark.x,
                y = mark.y
            )
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val found = findOne(id) ?: return false
        return placedMarks.remove(found)
    }

    fun findOne(id: Long): PlacedMark? {
        return placedMarks.find { it.id == id }
    }
}
