package app;

import factory.CDIJobFactory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MyApp {

    @Inject
    private CDIJobFactory jobFactory;

    public static void main(String[] args) throws Exception {

        ScheduledJobsManager scheduledJobsManager = new ScheduledJobsManager();
        scheduledJobsManager.postConstruct();
    }

}
