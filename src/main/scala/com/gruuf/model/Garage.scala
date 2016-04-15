package com.gruuf.model

import java.util.UUID

import net.liftweb.common.Box

object Garage {

  private var bikes: Seq[Bike] = Seq()

  def registerBike(friendlyName: String, vin: String, description: String): Bike = {
    val id = UUID.randomUUID().toString
    val bike = Bike(id, friendlyName, vin, description)

    bikes = bike +: bikes

    bike
  }

  def listBikes(searchName: String): Seq[Bike] = {
    if (searchName.isEmpty) {
      bikes.sortBy(_.friendlyName)
    } else {
      bikes.filter(_.friendlyName.startsWith(searchName)).sortBy(_.friendlyName)
    }
  }

  def find(bikeId: String): Option[Bike] = {
    bikes.find(_.id == bikeId)
  }

}
