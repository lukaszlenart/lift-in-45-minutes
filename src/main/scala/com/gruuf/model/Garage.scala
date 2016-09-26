package com.gruuf.model

import java.util.UUID

object Garage {

  private var bikes: Seq[Bike] = Seq()

  def registerBike(friendlyName: String, vin: String, description: String): Bike = {
    val id = UUID.randomUUID().toString
    val bike = Bike(id, friendlyName, vin, description)

    registerBike(bike)
  }

  def registerBike(bike: Bike): Bike = {
    bikes = bike +: bikes
    bike
  }

  def listBikes(searchName: String): Seq[Bike] = {
    if (searchName.isEmpty) {
      bikes.sortBy(_.friendlyName)
    } else {
      bikes.filter(_.friendlyName.toLowerCase.startsWith(searchName)).sortBy(_.friendlyName)
    }
  }

  def listBikes: Seq[Bike] = {
    bikes.sortBy(_.friendlyName)
  }

  def find(bikeId: String): Option[Bike] = {
    bikes.find(_.id == bikeId)
  }

}
