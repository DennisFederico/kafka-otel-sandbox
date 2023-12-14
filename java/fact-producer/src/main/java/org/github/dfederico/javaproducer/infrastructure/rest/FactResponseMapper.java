package org.github.dfederico.javaproducer.infrastructure.rest;

import org.github.dfederico.javaproducer.domain.model.ChuckFact;
import org.github.dfederico.javaproducer.infrastructure.rest.model.ChuckFactResponse;

class FactResponseMapper {

  ChuckFactResponse toResponse(ChuckFact fact) {
    return ChuckFactResponse.builder()
        .fact(fact.getFact())
        .timestamp(fact.getTimestamp())
        .build();
  }
}
