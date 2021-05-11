package factory;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CDIJobFactory implements JobFactory {

    @Inject
    private BeanManager beanManager;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) {
        final Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
        final Bean<?> bean = beanManager.getBeans(jobClass).stream().findAny().get();
        final CreationalContext<?> creationalContext = beanManager.createCreationalContext(bean);
        return (Job) beanManager.getReference(bean, jobClass, creationalContext);
    }
}
