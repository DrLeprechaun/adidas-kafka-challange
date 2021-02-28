package org.app.connector;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RandomSourceConnector extends SourceConnector {

    public static final String VERSION = "1.O.O";
    private static final Logger log = LoggerFactory.getLogger(RandomSourceConnector.class);
    private RandomSourceConnectorConfig config;

    public Class<? extends Task> taskClass() {
        return RandomSourceTask.class;
    }

    public void stop() {
        log.info("{} Stopping RandomSourceConnector.", this);
    }

    public ConfigDef config() {
        return RandomSourceConnectorConfig.conf();
    }

    public String version() {
        return VERSION;
    }

    public void start(Map<String, String> props) {
        log.info("{} Starting RandomSourceConnector", this);
        // TODO: Your implementation goes here
        this.config = new RandomSourceConnectorConfig(props);
    }

    public List<Map<String, String>> taskConfigs(int maxTasks) {
        // TODO: Your implementation goes here
        Map<String, String> taskConfig = new HashMap<>(config.originalsStrings());
        List<Map<String, String>> configs = new ArrayList<>(Collections.nCopies(maxTasks, taskConfig));
        return configs;
    }
}
