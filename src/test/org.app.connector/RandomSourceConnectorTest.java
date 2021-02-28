package org.app.connector;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class RandomSourceConnectorTest {

    @Mock
    RandomSourceConnectorConfig config;

    @InjectMocks
    private RandomSourceConnector connector;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @DataProvider
    public Object[][] startData() {
        return new Object[][]{
                {getOriginals(10)},
                {getOriginals(5)},
                {getOriginals(1)}
        };
    }

    @Test(dataProvider = "startData")
    public void startTest(Map<String, String> originals) {
        connector.start(originals);
    }

    @DataProvider
    public Object[][] taskConfigData() {
        return new Object[][]{
                {1, getOriginals(10)},
                {5, getOriginals(5)},
                {10, getOriginals(1)}
        };
    }

    @Test(dataProvider = "taskConfigData")
    public void taskConfigsTest(int maxTasks, Map<String, String> originals) {
        when(config.originalsStrings()).thenReturn(originals);

        List<Map<String, String>> taskConfigs = connector.taskConfigs(maxTasks);
        assertEquals(taskConfigs.size(), maxTasks);
        for (Map<String, String> config : taskConfigs) {
            assertEquals(config.entrySet().size(), originals.entrySet().size());
            for (Map.Entry<String, String> entry : config.entrySet()) {
                assertEquals(originals.get(entry.getKey()), entry.getValue());
            }
        }
    }

    @AfterMethod
    public void after() {
        this.connector = null;
    }

    private Map<String, String> getOriginals(int size) {
        Map<String, String> originals = new HashMap<>();
        for (int i = 0; i < size; i++) {
            originals.put("key" + i, "value" + i);
        }
        return originals;
    }

}