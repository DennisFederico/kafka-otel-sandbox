package org.github.dfederico.wordcountconsumer.infrastructure.kafka;

import org.github.dfederico.wordcountconsumer.config.AppConfig;

public class WorkCountService implements ConsumerAdapter {

  private final WorkCountConsumer consumer;

  public WorkCountService(AppConfig appConfig) {
    this.consumer = new WorkCountConsumer(appConfig);
  }

  @Override
  public void pollMessages() {
    consumer.pollMessage();
  }

}
