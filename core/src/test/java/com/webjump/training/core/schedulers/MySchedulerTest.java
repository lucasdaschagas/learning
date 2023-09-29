package com.webjump.training.core.schedulers;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;



@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class MySchedulerTest {
    @InjectMocks
    private MySchedular myScheduler;
    @Mock
    private SchedulerConfig config;
    private final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);
    @Mock
    private ResourceResolverFactory factory;
    @Mock
    private ResourceResolver resolver;
    @Mock
    private Resource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myScheduler = mock(MySchedular.class);
        context.load().json("src/test/resources/com.webjump.training.core.models.impl/ProductOfMineJsonTest.json","/content");

    }


    @Test
    void testRun() throws LoginException {
        config = mock(SchedulerConfig.class);
        factory = mock(ResourceResolverFactory.class);
        resource = mock(Resource.class);
        resolver = mock(ResourceResolver.class);

        resource = context.resourceResolver().getResource("/content/product_of_mine");

        Resource mockedResource = resolver.getResource(String.valueOf(resource));

        lenient().when(factory.getServiceResourceResolver(any())).thenReturn(resolver);
        lenient().when(resolver.getResource(any())).thenReturn(mockedResource);

        lenient().when(config.schedulerName()).thenReturn("My custom scheduler");
        lenient().when(config.time()).thenReturn("1/30 * * ? * * *");

        myScheduler.activate(config);
        myScheduler.run();


//        verify(log).info("---------------Scheduler running---------------");
    }
}