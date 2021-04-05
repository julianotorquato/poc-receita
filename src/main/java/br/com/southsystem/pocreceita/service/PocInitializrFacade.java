package br.com.southsystem.pocreceita.service;

import br.com.southsystem.pocreceita.scheduler.SchedulerFactory;
import br.com.southsystem.pocreceita.service.util.FileSystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static br.com.southsystem.pocreceita.scheduler.SyncAccountScheduler.SYNC_ACCOUNT_SCHEDULER;


@Service
public class PocInitializrFacade {

    @Value("${application.storage.in}")
    private String storageIn;

    @Value("${application.storage.out}")
    private String storageOut;

    @Value("${application.storage.log}")
    private String storageLog;

    @Autowired
    private SchedulerFactory schedulerFactory;

    public void startTask(String absoluteInputCSV) throws Exception{
        FileSystemUtil.setStorageIn(storageIn);
        FileSystemUtil.setStorageOut(storageOut);
        FileSystemUtil.setStorageLog(storageLog);
        schedulerFactory.getImpl(SYNC_ACCOUNT_SCHEDULER).executeTask(pathDefaulIfNull(absoluteInputCSV));
    }

    private String pathDefaulIfNull(String absoluteInputCSV){
        if (absoluteInputCSV == null)return FileSystemUtil.getCsvDefault();
        return absoluteInputCSV;
    }

}
