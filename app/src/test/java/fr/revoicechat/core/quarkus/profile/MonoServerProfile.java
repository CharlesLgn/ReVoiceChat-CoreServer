package fr.revoicechat.core.quarkus.profile;

import java.util.HashMap;
import java.util.Map;

public class MonoServerProfile extends BasicIntegrationTestProfile {
  @Override
  public Map<String, String> getConfigOverrides() {
    var config = new HashMap<>(super.getConfigOverrides());
    config.put("revoicechat.global.sever-mode", "MONO_SERVER");
    return config;
  }
}