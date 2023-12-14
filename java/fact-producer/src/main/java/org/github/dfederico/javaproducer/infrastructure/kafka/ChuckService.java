package org.github.dfederico.javaproducer.infrastructure.kafka;

import org.github.dfederico.javaproducer.config.AppConfig;
import org.github.dfederico.javaproducer.domain.ChuckFactPort;
import org.github.dfederico.javaproducer.domain.ChuckFactService;
import org.github.dfederico.javaproducer.domain.model.ChuckFact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChuckService implements ChuckAdapter {

  private final ChuckFactPort chuckFactPort = new ChuckFactService();
  private final ChuckProducer producer;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public ChuckService(AppConfig appConfig) {
    this.producer = new ChuckProducer(appConfig);
  }

  @Override
  public ChuckFact sendFact() throws JsonProcessingException {
    ChuckFact fact = chuckFactPort.buildFact();
    String message = objectMapper.writeValueAsString(fact);
    producer.produceJsonMessage(message);
    return fact;
  }

  @Override
  public ChuckFact sendFact(String customSay) throws JsonProcessingException {
    ChuckFact fact = chuckFactPort.buildFact(customSay);
    String message = objectMapper.writeValueAsString(fact);
    producer.produceJsonMessage(message);
    return fact;
  }
}
