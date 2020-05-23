name := """well-planner"""
 
version := "1.0" 
      
lazy val `wellplanner` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( ehcache , ws , specs2 % Test , guice )
libraryDependencies += evolutions
libraryDependencies += cache
libraryDependencies += cacheApi

libraryDependencies += "com.h2database" % "h2" % "1.4.197"

libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.2"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc4"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.2.0"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test

// https://mvnrepository.com/artifact/com.sun.mail/javax.mail
libraryDependencies += "com.sun.mail" % "javax.mail" % "1.6.2"

libraryDependencies += "org.skinny-framework" %% "skinny-http-client" % "2.3.7"
libraryDependencies += ws
libraryDependencies += ehcache


// https://mvnrepository.com/artifact/org.jsoup/jsoup
libraryDependencies += "org.jsoup" % "jsoup" % "1.11.3"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json-joda
libraryDependencies += "com.typesafe.play" %% "play-json-joda" % "2.6.0-RC1"


libraryDependencies += "com.pauldijou" %% "jwt-play" % "0.19.0"
libraryDependencies += "com.pauldijou" %% "jwt-core" % "0.19.0"
libraryDependencies += "com.auth0" % "jwks-rsa" % "0.6.1"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )