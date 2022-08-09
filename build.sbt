ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "functional-programming-intro",
    libraryDependencies += "dev.zio" %% "zio" % "2.0.2"
  )
