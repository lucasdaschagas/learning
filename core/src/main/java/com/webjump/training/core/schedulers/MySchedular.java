package com.webjump.training.core.schedulers;

import com.webjump.training.core.exceptions.MyScheduleExeption;
import com.webjump.training.core.util.ResolverUtil;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfig.class)

public class MySchedular implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MySchedular.class);
    private int schedulerID;
    @Reference
    private Scheduler scheduler;
    @Reference
    private  ResourceResolverFactory resolverFactory;

    @Activate
    protected void activate(SchedulerConfig config){
        schedulerID = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(){
        removeScheduler();
    }

    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerID));
    }

    private void addScheduler(SchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.time());
        scheduleOptions.name(String.valueOf(schedulerID));
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this,scheduleOptions);
        log.info("\n---------------Scheduler added---------------");
        ScheduleOptions scheduleOptionsNow = scheduler.NOW();
        scheduler.schedule(this,scheduleOptionsNow);

    }



    @Override
    public void run() {
            ResourceResolver resolver;
            Resource resource;
        try {
            resolver = ResolverUtil.newResolver(resolverFactory);
            resource = resolver.getResource("/content/web-train/us/en/jcr:content/root/container/container/product_of_mine");
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