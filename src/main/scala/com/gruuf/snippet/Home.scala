package com.gruuf.snippet

import net.liftweb.http._
import net.liftweb.sitemap._
  import Menu._
  import Loc._
import net.liftweb.util._
  import Helpers._

import org.joda.time.DateTime

object Home {
  val menu = Menu.i("Home") / "home"
}

class Home {

  def render = {
    PassThru
  }

}