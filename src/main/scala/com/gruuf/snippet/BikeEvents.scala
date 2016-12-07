package com.gruuf.snippet

import com.gruuf.model._
import net.liftweb.http._
import net.liftweb.sitemap.Loc._
import net.liftweb.util.Helpers._
import net.liftweb.sitemap._
import net.liftweb.util._
import org.joda.time.DateTime

object BikeEvents {

  val menu = Menu.param[Bike](
    "Bike events",
    "Bike events",
    (bikeId) => Garage.find(bikeId),
    (bike) => bike.id
  ) / "bike" / * / "events" >> Hidden >>
    TemplateBox(() => Templates("static" :: "bike-events" :: Nil))

}

class BikeEvents(bike: Bike) {

  def render = {
    ClearClearable andThen
    ".friendly-name *" #> bike.friendlyName &
    ".vin *" #> bike.vin &
    "#bike-events" #> History.bikeEvents(bike).map { event =>
      ".event-name *" #> event.name &
      ".event-date *" #> event.eventDate.toString() &
      ".event-description *" #> event.description
    }
  }

}
