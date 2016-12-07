import com.earldouglas.xsbtwebplugin.PluginKeys

name := "Lift in 45 minutes"

version := "0.0.1"

organization := "com.gruuf"

scalaVersion := "2.11.7"

resolvers ++= Seq(
    "snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
    "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

seq(webSettings :_*)
seq(resourceManagementSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

targetJavaScriptDirectory in ResourceCompile <<= (PluginKeys.webappResources in Compile) apply { resources => (resources / "static" / "js").get.head }
styleDirectories in ResourceCompile <<= (PluginKeys.webappResources in Compile) map { resources => (resources / "static" / "css").get }

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftmodules"   %% "lift-jquery-module_2.6" % "2.8",
    "org.eclipse.jetty" % "jetty-webapp"        % "8.1.17.v20150415"  % "container,test",
    "org.eclipse.jetty" % "jetty-plus"          % "8.1.17.v20150415"  % "container,test", // For Jetty Config
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.1.3",
    "org.specs2"        %% "specs2-core"        % "3.6.4"           % "test"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")
