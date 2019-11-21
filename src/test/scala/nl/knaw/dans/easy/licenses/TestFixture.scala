package nl.knaw.dans.easy.licenses

import java.io.File

import org.scalatest.{ FlatSpec, Inspectors, Matchers }

trait TestFixture extends FlatSpec with Matchers with Inspectors  {
  val LICENSES_DIR = "src/main/resources/licenses"
  val extensionRegExp = ".[^.]+$"
  val licenseFiles: List[String] = new File(LICENSES_DIR)
    .listFiles.filter(_.isFile).map(_.getName)
    .filterNot(n => n.endsWith("properties") || n.endsWith("json"))
    .toList
  val baseFileNames: List[String] = licenseFiles
    .map(_.replaceAll(extensionRegExp,""))
    .sortBy(identity).distinct

}
