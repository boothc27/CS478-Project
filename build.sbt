lazy val antwars = (project in file("."))
 .settings(
   name := "antwars",
   scalaVersion := "2.11.8",
   libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "1.0.1",
   libraryDependencies += "eu.infomas" % "annotation-detector" % "3.0.5",
   scalacOptions ++= Seq("-deprecation", "-feature"),
   javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
   mainClass in (Compile, run) := Some("scant.Main")
  )
