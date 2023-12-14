package org.github.dfederico.javaconsumer.infrastructure.kafka;

import org.github.dfederico.javaconsumer.config.AppConfig;

public class ChuckService implements ConsumerAdapter {

  private final ChuckConsumer consumer;

  public ChuckService(AppConfig appConfig) {
    this.consumer = new ChuckConsumer(appConfig);
  }

  @Override
  public void pollMessages() {
    consumer.pollMessage();
  }

}
