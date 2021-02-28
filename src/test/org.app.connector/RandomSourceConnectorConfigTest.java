package org.app.connector;

import org.apache.kafka.common.config.ConfigDef;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class RandomSourceConnectorConfigTest {

    @Test
    public void confTest() {
        ConfigDef configDef = RestSinkConnectorConfig.conf();
        assertNotNull(configDef);
    }
}
