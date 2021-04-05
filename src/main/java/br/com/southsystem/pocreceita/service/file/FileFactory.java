package br.com.southsystem.pocreceita.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileFactory {

    @Autowired
    private List<FileCreator> fileCreators;

    private final Map<FileType, FileCreator> cacheScheduler = new HashMap<>();

    @PostConstruct
    private void init(){
        for (FileCreator fileCreator: fileCreators) {
            cacheScheduler.put(fileCreator.strategy(), fileCreator);
        }
    }

    /**
     * Factory method to get the algorithm instance depending enum type.
     *
     * @param fileType the impl type
     * @return Service
     * @throws RuntimeException the platform exception
     */
    public FileCreator getImpl(FileType fileType) {
        FileCreator serviceImpl = cacheScheduler.get(fileType);
        if (null == serviceImpl) {
            throw new RuntimeException("No impl found for this name.");
        }
        return serviceImpl;
    }


}
