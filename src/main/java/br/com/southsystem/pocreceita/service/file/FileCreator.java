package br.com.southsystem.pocreceita.service.file;

public interface FileCreator<Data> {

    String genetareFilePath(Data data);

    FileType strategy();
}
