package br.com.southsystem.pocreceita.service.file;

import au.com.bytecode.opencsv.CSVWriter;
import br.com.southsystem.pocreceita.domain.AccountResult;
import br.com.southsystem.pocreceita.service.util.FileSystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static br.com.southsystem.pocreceita.service.util.FileUtil.createCSVWriter;

@Service
public class ExportAccountResultCSVService implements FileCreator<List<AccountResult>> {

    private static final Logger log = LoggerFactory.getLogger(ExportAccountResultCSVService.class);

    private static final String CSV_SEPARATOR = ";";

    @Override
    public String genetareFilePath(List<AccountResult> accountResults) {
        String file = FileSystemUtil.getResultCSV();
        try(CSVWriter writer = createCSVWriter(file)) {
            createHeads(writer);
            for (AccountResult accountResult : accountResults) {
                if (accountResult == null) continue;
                String[] line = { accountResult.getAgency(),
                                  accountResult.getFullAccount(),
                                  emptyIfNull(accountResult.getBalance()),
                                  emptyIfNull(accountResult.getStatus()),
                                  emptyIfNull(accountResult.getSyncStatus())
                };
                writer.writeNext(line);
            }
        }catch (IOException e){
            log.error("ERROR export result of accounts to CSV. Caused by: {}", e.getMessage());
        }
        return file;
    }

    @Override
    public FileType strategy() {
        return FileType.EXPORT_ACCOUNT_CSV;
    }

    private void createHeads(CSVWriter writer) {
        String [] heads = "agencia;conta;saldo;status;resultado".split(CSV_SEPARATOR);
        writer.writeNext(heads);
    }

    private String emptyIfNull(Object o){
        if (o == null) return "";
        return o.toString();
    }

}
