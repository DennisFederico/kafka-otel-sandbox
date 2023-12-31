package org.github.dfederico.factstats.infrastructure.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface ConsumerAdapter extends AutoCloseable {
  ConsumerRecords<String, String> pollMessages();
}
