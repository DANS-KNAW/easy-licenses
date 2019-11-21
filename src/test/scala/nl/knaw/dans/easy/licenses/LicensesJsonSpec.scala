/**
 * Copyright (C) 2019 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.easy.licenses

import java.io.File

import org.json4s._
import org.json4s.native.JsonMethods._

class LicensesJsonSpec extends TestFixture {
  implicit val jsonFormats: Formats = new DefaultFormats {}
  private val json = parse(new File(LICENSES_DIR, "licenses.json"))
  private val viewNames = for {
    JObject(file) <- json
    JField("title", viewName) <- file
  } yield Extraction.extract[String](viewName)

  "all the files in licenses.json" should "be present in the licenses directory and vice versa" in {
    viewNames.sortBy(identity) shouldBe baseFileNames
  }
}
