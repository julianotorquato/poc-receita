package br.com.southsystem.pocreceita.domain;


import br.com.southsystem.pocreceita.domain.enumeration.AccountStatus;
import br.com.southsystem.pocreceita.domain.enumeration.SyncStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class AccountResult implements Serializable {

    private static final long serialVersionID = 1l;

    private String agency;
    private String accountNumber;
    private String accountDigit;
    private BigDecimal balance;
    private AccountStatus status;
    private SyncStatus syncStatus;

    public String getFullAccount(){
        return getAccountNumber()+"-"+getAccountDigit();
    }
}
