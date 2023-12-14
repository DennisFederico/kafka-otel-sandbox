package org.github.dfederico.javaproducer.domain;

import org.github.dfederico.javaproducer.domain.model.ChuckFact;

public interface ChuckFactPort {

  ChuckFact buildFact();

  ChuckFact buildFact(String customSay);
}
