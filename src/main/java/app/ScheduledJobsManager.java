package app;

import factory.CDIJobFactory;
import job.MyJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class ScheduledJobsManager {

    private Scheduler scheduler;

    @Inject
    private CDIJobFactory jobFactory;

    @PostConstruct
    public void postConstruct() throws SchedulerException {
        // Get scheduler and start it
        scheduler = StdSchedulerFactory.getDefaultScheduler();

        // Use the CDI managed job factory
        scheduler.setJobFactory(jobFactory);

        // Start scheduler
        scheduler.start();

        // Create a QuarzJob to run
        final JobDetail job = JobBuilder.newJob(MyJob.class).build();

        // Create a Trigger to trigger the job every five minutes
        final CronScheduleBuilder runEveryFiveMinutes = CronScheduleBuilder.cronSchedule("0 0/5 * 1/1 * ? *");
        final CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(runEveryFiveMinutes)
                .forJob(job)
                .build();

        // Register Job and Trigger with the scheduler
        scheduler.scheduleJob(job, trigger);
    }

    @PreDestroy
    public void preDestroy() throws SchedulerException {
        if (scheduler != null && scheduler.isStarted()) {
            scheduler.shutdown(false);
        }
    }
}
