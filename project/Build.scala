import com.typesafe.sbt.SbtAspectj.AspectjKeys
import sbt._
import Keys._

object Build extends Build {
  import BuildSettings._
  import Dependencies._
  import AspectJ.aspectJSettings

  // Here we are effectively adding the `-javaagent` JVM startup
  // option with the location of the AspectJ Weaver provided by
  // the sbt-aspectj plugin.
  javaOptions in run <++= AspectjKeys.weaverOptions // in Aspectj

  // We need to ensure that the JVM is forked for the
  // AspectJ Weaver to kick in properly and do it's magic.
  fork in run := true

  lazy val root = Project(id = "root",
                          base = file("."))
    .settings(basicSettings: _*)
    .aggregate(myEngine)

  lazy val myEngine = Project(id = "myEngine",
                              base = file("myEngine"))
    .settings(basicSettings: _*)
    .settings(aspectJSettings: _*)
    .settings(sprayRunSettings: _*)
    .settings(aspectJSettings: _*)
    .settings(libraryDependencies ++=
      asCompile(logstashEncoder) ++
      asCompile(slf4j) ++
      asCompile(slf4j_log4j) ++
      asCompile(typesafeConfig) ++
      asCompile(nscalaTime) ++
      // asCompile(casbah) ++
      // asCompile(sprayRouting) ++
      // asCompile(sprayCaching) ++
      // asCompile(sprayClient) ++
      // asCompile(sprayJson) ++
      asCompile(kamon) ++
      asCompile(kamonAkka) ++
      asCompile(akkaActor) ++
      asCompile(akkaSlf4j) ++
      asCompile(aspectJ) ++
      asCompile(aspectjWeaver) ++
      // asTest(specs2) ++
      // asTest(sprayTestKit) ++
      asTest(akkaTestKit) // ++
      // asTest(scalaMeter)
    )
}
