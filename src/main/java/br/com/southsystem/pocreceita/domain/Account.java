package br.com.southsystem.pocreceita.domain;

import br.com.southsystem.pocreceita.domain.enumeration.AccountStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;



@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Account implements Serializable {

    private static final long serialVersionID = 1l;

    private String agency;
    private String accountNumber;
    private String accountDigit;
    private BigDecimal balance;
    private AccountStatus status;

    public String getFullAccount(){
        return getAccountNumber()+"-"+getAccountDigit();
    }





}
