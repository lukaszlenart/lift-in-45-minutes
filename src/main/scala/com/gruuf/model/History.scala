package com.gruuf.model

import java.util.UUID

import org.joda.time.DateTime

object Joda {
  implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)
}

object History {

  import Joda._

  private var bikeEvents: Seq[BikeEvent] = Seq()

  def bikeEvents(bike: Bike): Seq[BikeEvent] = {
    bikeEvents.filter(_.bikeId == bike.id).sortBy(_.eventDate)
  }

  def addEvent(bike: Bike, eventName: String, eventDate: DateTime, important: Boolean, description: String): BikeEvent = {
    val id = UUID.randomUUID().toString
    val bikeEvent = BikeEvent(id, bike.id, eventName, eventDate, description, important)

    bikeEvents = bikeEvent +: bikeEvents

    bikeEvent
  }

}
