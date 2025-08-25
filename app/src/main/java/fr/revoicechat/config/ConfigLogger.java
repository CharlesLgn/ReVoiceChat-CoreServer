package fr.revoicechat.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;

@ApplicationScoped
@Startup
public class ConfigLogger {

    @PostConstruct
    void logAllConfig() {
        Config config = ConfigProvider.getConfig();
        config.getPropertyNames()
              .forEach(name -> {
                  String value = config.getOptionalValue(name, String.class).orElse("undefined");
                  System.out.println(name + " = " + value);
              });
    }
}
