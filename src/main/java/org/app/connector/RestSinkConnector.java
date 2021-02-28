package org.app.connector;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RestSinkConnector extends SinkConnector {

    public static final String VERSION = "1.O.O";
    private static final Logger log = LoggerFactory.getLogger(RestSinkConnector.class);
    private RestSinkConnectorConfig config;

    public Class<? extends Task> taskClass() {
        return RestSinkTask.class;
    }

    public ConfigDef config() {
        return RestSinkConnectorConfig.conf();
    }

    public void stop() {
        log.info("{} Stopping RestSinkConnector.", this);
    }

    public String version() {
        return VERSION;
    }

    public void start(Map<String, String> props) {
        log.info("{} Starting RestSinkConnector", this);
        // TODO: your implementation goes here
        this.config = new RestSinkConnectorConfig(props);
    }

    public List<Map<String, String>> taskConfigs(int maxTasks) {
        // TODO: your implementation goes here
        Map<String, String> taskConfig = new HashMap<>(config.originalsStrings());
        List<Map<String, String>> configs = new ArrayList<>(Collections.nCopies(maxTasks, taskConfig));
        return configs;
    }
}
