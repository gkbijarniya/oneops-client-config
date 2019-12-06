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

import org.ini4j.Wini;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class OneOpsConfigReader {

  private static final String HOME = System.getProperty("user.home");
  public static File ONEOPS_CONFIG = new File(new File(HOME, ".oneops"), "config");
  public static final File ONEOPS_CONFIG_BOO = new File(new File(HOME, ".boo"), "config");
  public static final String ONEOPS_CONFIG_MESSAGE = HOME + "/{.boo|.oneops}/config";
  public static final String ONEOPS_DEFAULT_PROFILE = "default";

  public File defaultConfig() {
    if(System.getProperty("ONEOPS_CONFIG") != null) {
      ONEOPS_CONFIG = new File(System.getProperty("ONEOPS_CONFIG"));
    }
    if(ONEOPS_CONFIG.exists()) {
      return ONEOPS_CONFIG;      
    }
    if(ONEOPS_CONFIG_BOO.exists()) {
      return ONEOPS_CONFIG_BOO;
    }
    return null;
  }

  public Map<String, String> readDefaultConfig() throws IOException {
    return readDefaultConfig(ONEOPS_DEFAULT_PROFILE);
  }

  public Map<String, String> readDefaultConfig(String profile) throws IOException {
    if(ONEOPS_CONFIG.exists()) {
      return read(ONEOPS_CONFIG, profile);      
    }
    if(ONEOPS_CONFIG_BOO.exists()) {
      return read(ONEOPS_CONFIG_BOO, profile);            
    }
    return ImmutableMap.of();
  }
  
  public Map<String, String> read(File config, String profile) throws IOException {
    Wini ini = new Wini(config);
    return ini.get(profile);
  }
}
