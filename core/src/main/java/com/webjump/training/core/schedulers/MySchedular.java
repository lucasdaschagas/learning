package com.webjump.training.core.schedulers;

import com.webjump.training.core.exceptions.MyScheduleExeption;
import com.webjump.training.core.util.ResolverUtil;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;


@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfig.class)

public class MySchedular implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MySchedular.class);
    //    private int schedulerID;
    @Reference
    private Scheduler scheduler;
    @Reference
    private ResourceResolverFactory resolverFactory;

    private ScheduledFuture<?> scheduledFuture;

    @Activate
    protected void activate(SchedulerConfig config) {
//        schedulerID = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Modified
    protected void modified(SchedulerConfig config) {
        removeScheduler(config);
        activate(config);
    }

    private void removeScheduler(SchedulerConfig config) {
        scheduler.unschedule(config.schedulerName());
    }

    public void addScheduler(SchedulerConfig config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.time());
        scheduleOptions.name(config.schedulerName());
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this, scheduleOptions);
        log.info("\n---------------Scheduler added---------------");
        ScheduleOptions scheduleOptionsNow = scheduler.NOW();
        scheduler.schedule(this, scheduleOptionsNow);
    }


    @Override
    public void run() {
        ResourceResolver resolver;
        try {
            resolver = ResolverUtil.newResolver(resolverFactory);
            Resource resource = resolver.getResource("/content/web-train/us/en/jcr:content/root/container/container/product_of_mine");
            assert resource != null;
            ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
            assert valueMap != null;
            valueMap.put("name", "");
            valueMap.put("description", "");
            resolver.commit();

        } catch (PersistenceException | LoginException e) {
            log.error("\n---------------Scheduler Not Running---------------");
            throw new MyScheduleExeption("Scheduler could not operate normaly, please check the Resource path");
        }
        resolver.close();

        log.info("\n---------------Scheduler running---------------");
    }
}