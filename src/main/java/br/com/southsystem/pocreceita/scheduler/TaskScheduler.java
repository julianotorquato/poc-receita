package br.com.southsystem.pocreceita.scheduler;

import br.com.southsystem.pocreceita.service.erros.CSVNotFountException;

public interface TaskScheduler {

    public static final String SYNC_ACCOUNT_SCHEDULER = "SyncAccountScheduler";

    public void executeTask(String absoluteInputCSV) throws CSVNotFountException;
}
