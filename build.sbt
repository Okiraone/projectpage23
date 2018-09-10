name := "projectpage"

version := "1.0-SNAPSHOT"

libraryDependencies += jdbc

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.41",
  cache
)



play.Project.playJavaSettings
