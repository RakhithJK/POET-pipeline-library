/*
 * Copyright © 2019 T-Mobile USA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * See the LICENSE file for additional language around disclaimer of
 * warranties. Trademark Disclaimer: Neither the name of “T-Mobile, USA” nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 */
package com.tmobile.sre.pipeline.jenkins.environment

import com.tmobile.sre.pipeline.StubJenkinsContext
import org.junit.Test
import static org.junit.Assert.*

class JenkinsEnvironmentTest {
  @Test
  void testEnvironmentBuildPath() {
    def env = ["HUDSON_URL": "https://abc.dev.example.com/jenkins/", "BUILD_URL": "https://abc.dev.example.com/jenkins/job/Development/job/sre-pipeline-v2/job/sre-pipeline/job/feature%252Fgrapes/2/"]
    def context = new StubJenkinsContext(env: new StubJenkinsContext.StubEnvironment(env))

    def jenv = new JenkinsEnvironment(context).defaultEnvironment()

    def expectedBuildPath = "job/Development/job/sre-pipeline-v2/job/sre-pipeline/job/feature%252Fgrapes/2/"
    assertEquals(expectedBuildPath, jenv.get("PIPELINE_BUILD_URL_PATH"))
  }
}
