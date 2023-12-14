package org.github.dfederico.javaproducer.infrastructure.kafka;

import org.github.dfederico.javaproducer.domain.model.ChuckFact;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ChuckAdapter {

  ChuckFact sendFact() throws JsonProcessingException;

  ChuckFact sendFact(String customSay) throws JsonProcessingException;
}
