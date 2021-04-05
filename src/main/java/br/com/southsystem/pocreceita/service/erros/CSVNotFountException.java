package br.com.southsystem.pocreceita.service.erros;

public class CSVNotFountException extends RuntimeException{

    public CSVNotFountException() {
        super("Arquivo CSV não encontrado.");
    }
}
