package org.github.dfederico.javaproducer.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ChuckFact {
  String id;
  Long timestamp;
  String fact;
}
