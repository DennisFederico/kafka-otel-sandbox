package org.github.dfederico.javaproducer.infrastructure.kafka;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.github.dfederico.javaproducer.config.AppConfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaConfig {

  public static final String SERIALIZATION_STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
  private static final String ACKS_ALL = "all";

  static KafkaProducer<String, String> createKafkaProducer(AppConfig appConfig) {

    Properties props = new Properties();
    try (Reader reader = Files.newBufferedReader(Paths.get("connection.properties"))) {
      props.load(reader);
    } catch (Exception e) {
      System.err.printf("Exception reading properties file %s%n", e.getMessage());
      e.printStackTrace(System.err);
      System.exit(1);
    }

    //props.put(BOOTSTRAP_SERVERS_CONFIG, appConfig.getBootstrapServers());
    props.put(KEY_SERIALIZER_CLASS_CONFIG, SERIALIZATION_STRING_SERIALIZER);
    props.put(CLIENT_ID_CONFIG, appConfig.getChuckClientId());
    props.put(ACKS_CONFIG, ACKS_ALL);
    props.put(VALUE_SERIALIZER_CLASS_CONFIG, SERIALIZATION_STRING_SERIALIZER);
    return new KafkaProducer<>(props);

  }

}
