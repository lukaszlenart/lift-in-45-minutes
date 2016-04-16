package com.gruuf.model

import org.joda.time.DateTime

case class BikeEvent(id: String, bikeId: String, name: String, eventDate: DateTime, description: String, important: Boolean = false)
