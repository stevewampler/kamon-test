import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtAspectj.{ Aspectj, aspectjSettings }
import com.typesafe.sbt.SbtAspectj.AspectjKeys.{ aspectjVersion, compileOnly, lintProperties, weaverOptions }

object AspectJ {

  lazy val aspectJSettings = aspectjSettings ++ Seq(
    aspectjVersion :=  Dependencies.Versions.aspectJ,
    compileOnly in Aspectj :=  true,
    fork in run := true,
    javaOptions in Test <++=  weaverOptions in Aspectj,
    javaOptions in run <++=  weaverOptions in Aspectj,
    lintProperties in Aspectj +=  "invalidAbsoluteTypeName = ignore"
  )

}
