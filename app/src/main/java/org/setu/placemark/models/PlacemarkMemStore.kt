package org.setu.placemark.models

import java.util.concurrent.atomic.AtomicLong

class PlacemarkMemStore : PlacemarkStore {

    private val placemarks = ArrayList<PlacemarkModel>()
    private val lastId = AtomicLong(0L)

    override fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = lastId.incrementAndGet()
        placemarks.add(placemark)
    }

    override fun update(placemark: PlacemarkModel): Boolean {
        val index = placemarks.indexOfFirst { it.id == placemark.id }

        return if (index != -1) {
            placemarks[index] = placemarks[index].copy(
                title = placemark.title,
                description = placemark.description
            )
            true
        } else {
            false
        }
    }

    override fun delete(id: Long): Boolean {
        val found = findOne(id) ?: return false
        return placemarks.remove(found)
    }

    override fun findOne(id: Long): PlacemarkModel? {
        return placemarks.find { it.id == id }
    }
}