package com.webjump.training.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "SchedulerConfig", description = "Scheduler config")
public @interface SchedulerConfig {
    @AttributeDefinition(
            name = "Scheduler of mine",
            description = "Name of the scheduler of mine",
            type = AttributeType.STRING)
    public String schedulerName() default "My custom scheduler";

    @AttributeDefinition(
            name = "My Cron Expression",
            description = "My cron expression used in scheduler",
            type = AttributeType.STRING)
    public String time() default "1/30 * * ? * * *";
}
