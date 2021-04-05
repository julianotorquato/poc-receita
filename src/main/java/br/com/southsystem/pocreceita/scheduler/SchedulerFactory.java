package br.com.southsystem.pocreceita.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerFactory {

    @Autowired
    private List<TaskScheduler> schedulers;

    private final Map<String, TaskScheduler> cacheScheduler = new HashMap<>();

    @PostConstruct
    private void init(){
        for (TaskScheduler taskScheduler:schedulers) {
            cacheScheduler.put(taskScheduler.getClass().getSimpleName(), taskScheduler);
        }
    }

    /**
     * Factory method to get the algorithm instance depending singlename.
     *
     * @param nameBean the impl type
     * @return Service
     * @throws Exception the platform exception
     */
    public TaskScheduler getImpl(String nameBean) throws Exception {
        TaskScheduler serviceImpl = cacheScheduler.get(nameBean);
        if (null == serviceImpl) {
            throw new IllegalArgumentException("No impl found for this name.");
        }
        return serviceImpl;
    }


}
