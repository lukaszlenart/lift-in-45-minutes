package com.gruuf.snippet

import net.liftweb.sitemap.Menu
import net.liftweb.util.PassThru

object Home {
  val menu = Menu.i("Home") / "home"
}

class Home {

  def render = {
    PassThru
  }

}