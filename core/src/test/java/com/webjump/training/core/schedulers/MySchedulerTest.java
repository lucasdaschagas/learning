package com.webjump.training.core.schedulers;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.Assert.fail;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class MySchedulerTest {
    @Mock
    private ResourceResolverFactory factory;
    @Mock
    private Scheduler scheduler;
    @InjectMocks
    private MySchedular mySchedular = new MySchedular();

    private AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void setup(){

    }
    @Test
    void testRun(){
        fail("not yet implemented");
    }

    @Test
    void addSchedularTest(){
        fail("not yet implemented");
    }



}
