# OneOps Client Config

The OneOps client config library is a standard way for OneOps client applications to retrieve configuration for connecting to a OneOps instance. The default configuration location is `~/.oneops/config`, but we also support the `~/.boo/config` location for Boo users. A simple INI format is used:

```
[default]
host = https://prod.oneops.walmart.com
organization = megatron
api_key = XXXXXX
email = oneops@walmart.com
cloud = magic-cloud-5
```

And to load the standard OneOps client configuration from `~/.oneops/config` with the `default` profile you can use the following example:

```java
import java.io.File;
import java.util.Map;

public class OneOpsClient {

  // ...

  public Map<String,String> loadOneOpsConfig() throws Exception {
    OneOpsConfigReader reader = new OneOpsConfigReader();
    Map<String,String> config = reader.readDefaultConfig();
    return config;
  }
}
```
