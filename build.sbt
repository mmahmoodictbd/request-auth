name := """advauth"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  "org.skinny-framework" %% "skinny-orm" % "2.0.7",
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.5.0",
  "mysql" % "mysql-connector-java" % "5.1.18"
)

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.common
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.common" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.client
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.client" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.authzserver
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.authzserver" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.httpclient4
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.httpclient4" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.resourceserver
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.resourceserver" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.dynamicreg.client
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.dynamicreg.client" % "1.0.1"

// http://mvnrepository.com/artifact/org.apache.oltu.oauth2/org.apache.oltu.oauth2.dynamicreg.server
libraryDependencies += "org.apache.oltu.oauth2" % "org.apache.oltu.oauth2.dynamicreg.server" % "1.0.1"

libraryDependencies += "com.nulab-inc" %% "play2-oauth2-provider" % "0.17.2"
