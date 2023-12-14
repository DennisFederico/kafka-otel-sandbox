package org.github.dfederico.wordcountconsumer;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.github.dfederico.wordcountconsumer.config.AppConfig;
import org.github.dfederico.wordcountconsumer.config.ConfigHandler;
import org.github.dfederico.wordcountconsumer.infrastructure.kafka.ConsumerAdapter;
import org.github.dfederico.wordcountconsumer.infrastructure.kafka.WorkCountService;

public class App {

  public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

    ConfigHandler configHandler = new ConfigHandler();
    AppConfig config = configHandler.getAppConfig(args[0]);

    ConsumerAdapter workCountAdapter = new WorkCountService(config);
    workCountAdapter.pollMessages();
  }
}
