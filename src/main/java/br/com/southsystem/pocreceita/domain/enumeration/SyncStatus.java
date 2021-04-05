package br.com.southsystem.pocreceita.domain.enumeration;
import static java.lang.Boolean.TRUE;


public enum SyncStatus {

    ERROR("error"),
    SUCCESS("success");

    private String label;

    SyncStatus(String label) {
        this.label = label;
    }

    public static SyncStatus from(boolean status){
        if (TRUE.equals(status)){
            return SUCCESS;
        }
        return ERROR;
    }

}
