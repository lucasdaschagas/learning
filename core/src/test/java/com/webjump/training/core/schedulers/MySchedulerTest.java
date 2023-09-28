package com.webjump.training.core.schedulers;

import com.webjump.training.core.util.ResolverUtil;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.reflections.Reflections.log;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class MySchedulerTest {
    @InjectMocks
    private MySchedular myScheduler;

//    @Mock
//    private Scheduler schedulerMock;

    private SchedulerConfig config;
    private int schedulerId;
    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        myScheduler=context.registerService(new MySchedular());
//        LOG=TestLoggerFactory.getTestLogger(myScheduler.getClass());
        config=mock(SchedulerConfig.class);
        when(config.schedulerName()).thenReturn("My custom scheduler");
        when(config.time()).thenReturn("1/30 * * ? * * *");

//        myScheduler.bindScheduler(mock(schedulerMock.getClass()));


//        myScheduler.bindFactory(mock(ResourceResolverFactory.class));
//        myScheduler = context.registerService(new MySchedular());
//        context.registerService(Scheduler.class, schedulerMock);


//
//        SchedulerConfig schedulerConfig = mock(SchedulerConfig.class);
//        when(schedulerConfig.schedulerName()).thenReturn("My custom scheduler");
//        when(schedulerConfig.time()).thenReturn("1/30 * * ? * * *");

//        optionsTest = schedulerMock.EXPR(schedulerConfig.time());
//        optionsTest.name(String.valueOf(schedulerIdTest));
//        optionsTest.canRunConcurrently(false);
//        schedulerMock.schedule(this,optionsTest);
//        LOG.info("\n---------------Scheduler added---------------");
//        ScheduleOptions scheduleOptionsNow = schedulerMock.NOW();
//        schedulerMock.schedule(this,scheduleOptionsNow);

//
//        myScheduler.activate(schedulerConfig);
//        LOG = TestLoggerFactory.getTestLogger(myScheduler.getClass());
////        when(schedulerConfigMock.schedulerName()).thenReturn("My custom scheduler");
////        when(schedulerConfigMock.time()).thenReturn("1/30 * * ? * * *");
    }



    @Test
    void testRun() throws LoginException {
        myScheduler.run();
//        verify(log).info("---------------Scheduler running---------------");
    }
}