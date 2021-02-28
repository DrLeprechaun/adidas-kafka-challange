package org.app.connector;

import com.github.javafaker.ChuckNorris;
import com.github.javafaker.Faker;
import org.apache.kafka.connect.source.SourceRecord;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.app.connector.RandomSourceConnectorConfig.TOPIC_CONFIG;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RandomSourceTaskTest {

    @Mock
    private Faker faker;

    @Mock
    private ChuckNorris chuckNorris;

    @Mock
    private RandomSourceConnectorConfig config;

    @InjectMocks
    private RandomSourceTask task;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void pollTest() throws InterruptedException {
        when(faker.chuckNorris()).thenReturn(chuckNorris);
        when(chuckNorris.fact()).thenReturn("someFact");
        when(config.getString(TOPIC_CONFIG)).thenReturn("main");

        List<SourceRecord> records = task.poll();

        assertNotNull(records);
        assertEquals(records.get(0).topic(), "main");
    }
}
