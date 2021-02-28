package org.app.connector;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.source.SourceRecord;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.app.connector.RandomSourceConnectorConfig.TOPIC_CONFIG;
import static org.app.connector.RestSinkConnectorConfig.API_URI_CONFIG;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class RestSinkTaskTest {

    @Mock
    private RestSinkConnectorConfig config;

    @InjectMocks
    private RestSinkTask task;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void putTest() {
        when(config.getString(API_URI_CONFIG)).thenReturn("http://someUrl/api");
        Collection<SinkRecord> records = new ArrayList<>();
        SinkRecord sinkRecord = new SinkRecord("main", 1, Schema.STRING_SCHEMA, "key", Schema.STRING_SCHEMA, "value", 1l);
        records.add(sinkRecord);

        task.put(records);

        Mockito.verify(config, Mockito.times(1)).getString(API_URI_CONFIG);
    }
}
