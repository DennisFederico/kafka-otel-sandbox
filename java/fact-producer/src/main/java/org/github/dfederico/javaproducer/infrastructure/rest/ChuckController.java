package org.github.dfederico.javaproducer.infrastructure.rest;

import org.github.dfederico.javaproducer.config.AppConfig;
import org.github.dfederico.javaproducer.infrastructure.kafka.ChuckAdapter;
import org.github.dfederico.javaproducer.infrastructure.kafka.ChuckService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChuckController {

  private final FactResponseMapper mapper = new FactResponseMapper();
  private final ChuckAdapter adapter;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public ChuckController(AppConfig appConfig) {
    this.adapter = new ChuckService(appConfig);
  }

  public String sendFact() throws JsonProcessingException {
    return objectMapper.writeValueAsString(mapper.toResponse(adapter.sendFact()));
  }

  public String sendFact(String customSay) throws JsonProcessingException {
  return objectMapper.writeValueAsString(mapper.toResponse(adapter.sendFact(customSay)));
  }
}