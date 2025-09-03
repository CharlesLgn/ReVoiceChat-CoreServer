package fr.revoicechat.core.quarkus.profile;

import java.util.HashMap;
import java.util.Map;

public class MultiServerProfile extends BasicIntegrationTestProfile {
  @Override
  public Map<String, String> getConfigOverrides() {
    var config = new HashMap<>(super.getConfigOverrides());
    config.put("revoicechat.global.sever-mode", "MULTI_SERVER");
    return config;
  }
}