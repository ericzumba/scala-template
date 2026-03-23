ThisBuild / scalaVersion := "2.12.21"

lazy val root = (project in file("."))
  .settings(
    name := "scala-template",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
  )