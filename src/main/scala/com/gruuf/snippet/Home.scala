package com.gruuf.snippet

import net.liftweb.http._
import net.liftweb.sitemap._
  import Menu._
  import Loc._
import net.liftweb.util._
  import Helpers._
import com.gruuf.model._
import org.joda.time.DateTime

object Home {
  val menu = Menu.i("Home") / "home" >>
    TemplateBox(() => Templates("static" :: "bikes" :: Nil))
}

class Home {

  var searchTerm = ""

  val bikesRenderer = SHtml.idMemoize { render =>
    Garage.listBikes(searchTerm).map { bike =>
      ".friendly-name *" #> bike.friendlyName &
      ".vin *" #> bike.vin &
      ".description *" #> bike.description &
      ".bike-events [href]" #> BikeEvents.menu.toLoc.calcHref(bike)
    }
  }

  def render = {
    ClearClearable andThen
    SHtml.makeFormsAjax andThen
    "name=search" #> SHtml.text(searchTerm, searchTerm = _) &
    "type=submit" #> SHtml.ajaxOnSubmit(bikesRenderer.setHtml) &
    "#bikes" #> bikesRenderer
  }

}