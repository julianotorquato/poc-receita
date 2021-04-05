package br.com.southsystem.pocreceita.scheduler;

import br.com.southsystem.pocreceita.service.util.FileSystemUtil;
import br.com.southsystem.pocreceita.service.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.io.File;

@SpringBootTest
public class SyncAccountSchedulerTests {

    @Autowired
    private SyncAccountScheduler syncAccountScheduler;

    private static final String ACCOUNT_OK = "/in/account_ok.csv";
    private static final String ACCOUNT_NOOK = "/in/account_nook.csv";
    private static final String PATH_STORAGE_OUT = "/in/account_ok.csv";
    private static final String ACCOUNT_RESULT = "account_result.csv";

    @BeforeAll
    static void preInit(){
        TestUtil.deleteIfExists(new File(FileSystemUtil.getStorageOut()));
        TestUtil.deleteIfExists(new File(FileSystemUtil.getStorageLog()));
    }

    @Test
    public void givenAccount_thenGenerateAccountResult() throws Exception{
        syncAccountScheduler.executeTask(TestUtil.getPathSilence(ACCOUNT_OK));
        File file = new File(FileSystemUtil.getStorageOut());
        File[] allContents = file.listFiles();
        boolean CSV_ACCOUNT_RESULT_GENERETED =  allContents != null && allContents.length > 0;
        AssertionErrors.assertTrue("Account Result generated",CSV_ACCOUNT_RESULT_GENERETED);
    }


    @Test
    public void givenAccount_thenGenerateErrorsLog() throws Exception{
        syncAccountScheduler.executeTask(TestUtil.getPathSilence(ACCOUNT_NOOK));
        File file = new File(FileSystemUtil.getStorageLog());
        File[] allContents = file.listFiles();
        boolean ERRO_LOG_GENERETED =  allContents != null && allContents.length > 0;
        AssertionErrors.assertTrue("ERROS log generated",ERRO_LOG_GENERETED);
    }

}
