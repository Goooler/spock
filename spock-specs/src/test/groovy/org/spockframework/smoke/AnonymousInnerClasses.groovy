/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spockframework.smoke

import org.spockframework.EmbeddedSpecification
import org.spockframework.runtime.GroovyRuntimeUtil
import org.spockframework.runtime.UnallowedExceptionThrownError
import spock.lang.PendingFeature
import spock.lang.Requires

class AnonymousInnerClasses extends EmbeddedSpecification {
  @Requires({ GroovyRuntimeUtil.isGroovy2() })
  def "anonymous inner class and dot in feature name does not fail execution (Groovy 2)"() {
    when:
    runner.runSpecBody("""
def '.'() {
  expect:
  new Object() { }
}
    """)

    then:
    noExceptionThrown()
  }

  @Requires({ GroovyRuntimeUtil.isGroovy2() })
  def "enclosing method of anonymous inner class can be accessed (Groovy 2)"() {
    when:
    runner.runFeatureBody("""
expect:
new Object() { }.getClass().enclosingMethod
    """)

    then:
    noExceptionThrown()
  }

  @Requires({ GroovyRuntimeUtil.isGroovy3() })
  @PendingFeature(exceptions = UnallowedExceptionThrownError, reason = "Started failing after late rebase with master. To be fixed.")
  def "anonymous inner class and dot in feature name does not fail execution"() {
    when:
    runner.runSpecBody("""
def '.'() {
  expect:
  new Object() { }
}
    """)

    then:
    noExceptionThrown()
  }

  @Requires({ GroovyRuntimeUtil.isGroovy3() })
  @PendingFeature(exceptions = UnallowedExceptionThrownError, reason = "Started failing after late rebase with master. To be fixed.")
  def "enclosing method of anonymous inner class can be accessed"() {
    when:
    runner.runFeatureBody("""
expect:
new Object() { }.getClass().enclosingMethod
    """)

    then:
    noExceptionThrown()
  }
}
