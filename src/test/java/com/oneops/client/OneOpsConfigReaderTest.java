/*
 * Copyright 2017 Walmart, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.oneops.client;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.oneops.client.OneOpsConfigReader;

public class OneOpsConfigReaderTest {

  private String basedir;

  @Before
  public void beforeTests() {
    basedir = System.getProperty("basedir", new File("").getAbsolutePath());
  }

  @Test
  public void validateInliningFiles() throws Exception {
    OneOpsConfigReader reader = new OneOpsConfigReader();
    Map<String,String> config = reader.read(config("config"), OneOpsConfigReader.ONEOPS_DEFAULT_PROFILE);
    assertEquals("https://localhost:9090", config.get("host"));
    assertEquals("bfd", config.get("organization"));
    assertEquals("BOO!!", config.get("api_key"));
    assertEquals("boo@walmart.com", config.get("email"));
  }

  protected File config(String name) {
    return new File(basedir, String.format("src/test/config/%s", name));
  }
}
