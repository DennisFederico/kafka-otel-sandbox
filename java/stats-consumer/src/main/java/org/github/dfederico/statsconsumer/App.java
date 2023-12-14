package org.github.dfederico.statsconsumer;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.github.dfederico.statsconsumer.config.AppConfig;
import org.github.dfederico.statsconsumer.config.ConfigHandler;
import org.github.dfederico.statsconsumer.infrastructure.kafka.ConsumerAdapter;
import org.github.dfederico.statsconsumer.infrastructure.kafka.StatsConsumer;

public class App {

  public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

    ConfigHandler configHandler = new ConfigHandler();
    AppConfig config = configHandler.getAppConfig(args[0]);

    ConsumerAdapter workCountAdapter = new StatsConsumer(config);
    workCountAdapter.pollMessages();
  }
}
