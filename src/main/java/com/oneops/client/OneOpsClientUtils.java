package com.oneops.client;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Map;

public class OneOpsClientUtils {
  
  private static final int DEFAULT_TIME_OUT = 3 * 1000; // 3s

  public static boolean oneOpsAvailable(String host, int port) {
    return oneOpsAvailable(host, port, DEFAULT_TIME_OUT);
  }
  
  public static boolean oneOpsAvailable(String host, int port, int timeoutInMs) {
    try (Socket socket = new Socket()) {
      InetSocketAddress socketAddress = new InetSocketAddress(new java.net.URI(host).getHost(), port);
      // Waiting 3 seconds even on the VPN is likely sufficient
      socket.connect(socketAddress, timeoutInMs);
      return true;
    } catch (IOException | URISyntaxException ex) {
      System.out.format("Unable to reach %s. Skipping tests%n", host);
      return false;
    }
  }
  
  public static boolean developerConfigurationPresent() throws IOException {
    OneOpsConfigReader configReader = new OneOpsConfigReader();
    File defaultConfig = configReader.defaultConfig();
    if (defaultConfig != null) {
      OneOpsConfigReader reader = new OneOpsConfigReader();
      Map<String, String> config = reader.readDefaultConfig();
      String organization = config.get("organization");
      if (organization != null) {
        return true;
      }
    }
    return false;
  }  
}
