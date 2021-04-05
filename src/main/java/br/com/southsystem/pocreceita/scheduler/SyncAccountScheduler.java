package br.com.southsystem.pocreceita.scheduler;

import br.com.southsystem.pocreceita.domain.Account;
import br.com.southsystem.pocreceita.domain.AccountResult;
import br.com.southsystem.pocreceita.domain.enumeration.SyncStatus;
import br.com.southsystem.pocreceita.domain.validator.Validator;
import br.com.southsystem.pocreceita.domain.validator.ValidatorLineCSV;
import br.com.southsystem.pocreceita.service.ReceitaService;
import br.com.southsystem.pocreceita.service.erros.CSVNotFountException;
import br.com.southsystem.pocreceita.service.file.FileFactory;
import br.com.southsystem.pocreceita.service.file.FileType;
import br.com.southsystem.pocreceita.service.log.CreateLogTXTService;
import br.com.southsystem.pocreceita.service.mapper.AccountMapper;
import br.com.southsystem.pocreceita.service.mapper.AccountResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.southsystem.pocreceita.service.util.FileUtil.DELIMITER;
import static br.com.southsystem.pocreceita.service.util.StreamUtil.of;

@Component
public class SyncAccountScheduler implements TaskScheduler {

    private static final Logger log = LoggerFactory.getLogger(SyncAccountScheduler.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountResultMapper accountResultMapper;

    @Autowired
    private FileFactory fileFactory;

    @Autowired
    private CreateLogTXTService createLogTXTService;

    @Override
    public void executeTask(String absoluteInputCSV) {
        boolean inputCSVNotExists = absoluteInputCSV == null || !new File(absoluteInputCSV).exists();
        if (inputCSVNotExists){
            throw new CSVNotFountException();
        }
        String fileName = createLogTXTService.createNewFileName();
        Pattern pattern = Pattern.compile(DELIMITER);
        ReceitaService receitaService = new ReceitaService();
        Validator validator = new ValidatorLineCSV();
        try (Stream<String> lines = of(absoluteInputCSV)) {
            List<Account> accounts = lines.skip(1).map(line -> {
                if (validator.validate(line)){
                    return accountMapper.toEntity(pattern.split(line));
                }
                createLogTXTService.addInfos(fileName, "Linha com problema: ", line);
                return null;
            }).filter(item -> item != null)
            .collect(Collectors.toList());
            List<AccountResult> results = accounts.parallelStream()
                .map(account -> {
                    return createAccountResult(fileName, receitaService, account);
                }).filter(item -> item != null)
                .collect(Collectors.toList());
            fileFactory.getImpl(FileType.EXPORT_ACCOUNT_CSV).genetareFilePath(results);
        } catch (IOException e) {
            log.error("ERROR : Unable to read the file in the directory: {}. Caused By", absoluteInputCSV, e.getMessage());
        }
    }

    private AccountResult createAccountResult(String fileName, ReceitaService receitaService, Account account) {
        try {
            boolean result = receitaService.atualizarConta(
                    account.getAgency(),
                    account.getFullAccount(),
                    account.getBalance().doubleValue(),
                    account.getStatus().name());
            AccountResult accountResult = accountResultMapper.toEntity(account);
            accountResult.setSyncStatus(SyncStatus.from(result));
            return accountResult;
        } catch (Exception e) {
            log.error("ERROR : Service Atualizar Conta with problems. Account {}. Caused By", account.toString(), e.getMessage());
            createLogTXTService.addInfos(fileName, "Conta com problema: ", e.getMessage());
            return null;
        }
    }

}



