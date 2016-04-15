package com.gruuf.snippet

import com.gruuf.model.{Bike, Garage}
import net.liftweb.http.{SHtml, Templates}
import net.liftweb.sitemap.Loc.TemplateBox
import net.liftweb.sitemap.Menu
import net.liftweb.util.{ClearClearable, ClearNodes}
import net.liftweb.util.Helpers._
import org.joda.time.DateTime

object Home {
  val menu = Menu.i("menuHome") / "home"
}

class Home {

  def render = {
    ".date" #> DateTime.now.toString()
  }

}