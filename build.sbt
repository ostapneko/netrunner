name := "netrunner"

version := "1.0"

scalaVersion := "2.11.6"

val monocle = "1.2.0-M1"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.github.julien-truffaut"  %%  "monocle-core" % monocle,
  "com.github.julien-truffaut"  %%  "monocle-generic" % monocle,
  "com.github.julien-truffaut"  %%  "monocle-macro" % monocle
)
