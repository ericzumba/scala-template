ThisBuild / scalaVersion := "2.12.21"

lazy val root = (project in file("."))
  .settings(
    name := "scala-template",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.apache.spark" %% "spark-sql" % "3.5.7"
    ),

    fork := true,

    javaOptions ++= Seq(
      "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"
    )
  )