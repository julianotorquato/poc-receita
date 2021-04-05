package br.com.southsystem.pocreceita.domain.enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum AccountStatus {

    A, I, B, P;

    private static final Logger log = LoggerFactory.getLogger(AccountStatus.class);

    public static AccountStatus from(String value){
        try {
            return AccountStatus.valueOf(value);
        }catch (Exception e){
            log.error("ERROR: cast string to StatusAccount. Caused by: {}", e.getMessage());
            return null;
        }
    }

}
