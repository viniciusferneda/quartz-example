package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import service.MyService;

import javax.inject.Inject;

public class MyJob implements Job {

    @Inject
    private MyService service;

    @Override
    public void execute(JobExecutionContext context) {
        // Print all names of the registered persons every 5 minutes
        this.service.getPersons().forEach(person -> System.out.println(person.getId() + " " + person.getName()));
    }
}
