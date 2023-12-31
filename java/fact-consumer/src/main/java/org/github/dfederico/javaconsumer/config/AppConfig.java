package org.github.dfederico.javaconsumer.config;

import lombok.Data;

@Data
public class AppConfig {
   private String bootstrapServers;
   private String chuckClientId;
   private String chuckGroupId;
   private String chuckTopic;
   private String autoOffsetReset;
}
