package org.github.dfederico.javaproducer.config;

import lombok.Data;

@Data
public class AppConfig {
   private int appPort;
   private String chuckClientId;
   private String chuckTopic;
}
