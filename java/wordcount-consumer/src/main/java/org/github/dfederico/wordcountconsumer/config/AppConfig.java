package org.github.dfederico.wordcountconsumer.config;

import lombok.Data;

@Data
public class AppConfig {
   private String wordCountClientId;
   private String wordCountTopic;
   private String wordCountGroupId;
   private String autoOffsetReset;
}
