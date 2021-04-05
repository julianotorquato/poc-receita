package br.com.southsystem.pocreceita.service.log;

import br.com.southsystem.pocreceita.service.util.FileSystemUtil;
import br.com.southsystem.pocreceita.service.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CreateLogTXTService {

    private static final Logger log = LoggerFactory.getLogger(CreateLogTXTService.class);

    private static final String NAME_BASE =  "LOG_";
    private static final String EXTENSION =  ".txt";
    public static final String LINE_BREAK = "\r\n";

    public String createNewFileName(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String format = now.format(formatter);
        return NAME_BASE + format + EXTENSION;
    }

    public void addInfos(String fileName, String... mensagem){
        this.createLog(fileName,null, mensagem);
    }

    private void createLog(String fileName, String title, String... messages){
        StringBuilder builder = new StringBuilder();
        if (title != null){
            builder.append(title).append(LINE_BREAK);
        }
        if (messages != null){
            for (String message:messages)
                builder.append(message).append(LINE_BREAK);
        }
        try{
            FileUtil.writeFile(FileSystemUtil.getStorageLog(), fileName, builder.toString(), Boolean.TRUE);
        }catch (Exception e){
            log.error("ERROR : Failed to write to the log file with the name: {}. Caused By: {}, {}", fileName, e.getMessage(), e.getLocalizedMessage());
        }
    }
}
