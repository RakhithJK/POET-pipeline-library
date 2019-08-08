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
 */
package com.tmobile.sre.pipeline.environment

class EnvironmentSubstitutionTest extends GroovyTestCase {
    void testSubstitute() {
        def env = new PipelineEnvironment(["a": "x", "b": "z"])
        def subst = new EnvironmentSubstitution(environment: env)

        assertEquals("x2z3", subst.substitute("\${a}2\${b}3"))
    }

    void testSubstituteWithDefault() {
        def env = new PipelineEnvironment(["a": "x"])
        def subst = new EnvironmentSubstitution(environment: env)

        def input = "\${x:-abcdef}\${a}2\${b:-z}3\${c:-y9}"
        assertEquals("abcdefx2z3y9", subst.substitute(input))
    }


    void testSubstituteThrowsErrorWhenMissing() {
        def subst = new EnvironmentSubstitution(environment: new PipelineEnvironment([]))

        shouldFail(EnvironmentSubstitution.MissingKeyException.class) {
            subst.substitute("\${missing}")
        }
    }

    void testNullInputReturnsNull() {
        def subst = new EnvironmentSubstitution(environment: new PipelineEnvironment([]))
        assertNull(subst.substitute(null))
    }
}