package org.github.dfederico.kstreams;

import java.io.FileNotFoundException;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.github.dfederico.kstreams.config.AppConfig;
import org.github.dfederico.kstreams.config.ConfigHandler;
import org.github.dfederico.kstreams.infrastructure.kafka.KafkaConfig;
import org.github.dfederico.kstreams.infrastructure.kafka.WordCountTopologyBuilder;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        ConfigHandler configHandler = new ConfigHandler();
        AppConfig appConfig = configHandler.getAppConfig(args[0]);

        WordCountTopologyBuilder wordCountTopologyBuilder = new WordCountTopologyBuilder();
        StreamsBuilder builder = wordCountTopologyBuilder.createWordCountTopology(appConfig);
        final KafkaStreams streams = new KafkaStreams(builder.build(),
        KafkaConfig.createStreamsConfigProperties("chuck-wordcount", appConfig));
        streams.cleanUp();
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
