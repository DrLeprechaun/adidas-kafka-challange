package org.app.connector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

public class RestSinkConnectorConfig extends AbstractConfig {

    // TODO: topic property and its documentation goes here
    public static final String API_URI_CONFIG = "api.uri";
    private static final String API_URI_DOC = "API URI";

    public static final String TOPIC_CONFIG = "topics";
    private static final String TOPIC_DOC = "Topic name";

    // TODO If needed your getters goes here

    public RestSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public RestSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
                // TODO Attache your config to ConfigDef
                .define(API_URI_CONFIG, Type.STRING, Importance.HIGH, API_URI_DOC)
                .define(TOPIC_CONFIG, Type.STRING, "main", Importance.HIGH, TOPIC_DOC);
    }


}
