package com.gruuf.snippet

import net.liftweb.http._
  import js.JsCmds
import net.liftweb.sitemap._
  import Menu._
  import Loc._
import net.liftweb.util._
  import Helpers._

import com.gruuf.model._

object RegisterBike {

  val menu = Menu.i("Register Bike") / "register-bike" >>
    TemplateBox(() => Templates("static" :: "register-bike" :: Nil))

}

class RegisterBike {

  var friendlyName = ""
  var vin = ""
  var description = ""

  def render = {
    SHtml.makeFormsAjax andThen
    "#friendly-name" #> SHtml.text(friendlyName, friendlyName = _) &
    "#vin" #> SHtml.text(vin, vin = _) &
    "#description" #> SHtml.textarea(description, description = _) &
    "type=submit" #> SHtml.ajaxOnSubmit(storeBike)
  }

  def storeBike() = {
    if (validate) {
      Garage.registerBike(friendlyName, vin, description)

      JsCmds.RedirectTo(Home.menu.loc.calcDefaultHref)
    } else {
      JsCmds.Noop
    }
  }

  def validate = {
    if (friendlyName.isEmpty) {
      S.error("Friendly name is required")
    }
    if (vin.isEmpty) {
      S.error("VIN is requited")
    }
    S.errors.isEmpty
  }

}
