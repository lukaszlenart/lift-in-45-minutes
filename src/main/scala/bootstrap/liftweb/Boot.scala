package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import com.gruuf.model._
import com.gruuf.snippet._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._
import org.joda.time.DateTime

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {

  def boot {
    // where to search snippet
    LiftRules.addToPackages("com.gruuf")

    // Build SiteMap
    val entries = List(
      Menu.i("home") / "index" >>
        EarlyResponse(() => {
          Full(RedirectResponse(Home.menu.loc.calcDefaultHref))
      }) >> Hidden,

      Home.menu,

      // /static path to be visible
      Menu.i("Static") / "static" / ** >> Hidden
    )

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries:_*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery191
    JQueryModule.init()

/*
    val bike1 = Garage.registerBike(Bike("d745d5ee-867a-4a5c-a7e1-42d2c2af434a", "Dragstar", "123456789", "Yamaha XVS 1100 Dragstar Classic, 2001"))
    val bike2 = Garage.registerBike(Bike("044d9878-8e81-4f4c-8157-613a581d9826", "Caponord", "987654321", "Aprilia ETV 1000 Caponord, 2003"))
*/


/*
    History.addEvent(bike1, "Changed oil", DateTime.now, true, "Motul 5100")
    History.addEvent(bike1, "Cleaned up", DateTime.now, false, "Soap and water")

    History.addEvent(bike2, "Changed oil", DateTime.now, true, "Motul 7100")
    History.addEvent(bike2, "Cleaned up", DateTime.now, false, "Karsher and brush")
*/

  }
}