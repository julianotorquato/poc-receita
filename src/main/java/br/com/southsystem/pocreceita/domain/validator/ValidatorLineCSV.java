package br.com.southsystem.pocreceita.domain.validator;

import br.com.southsystem.pocreceita.domain.enumeration.AccountStatus;
import br.com.southsystem.pocreceita.service.util.CastUtil;

import static br.com.southsystem.pocreceita.service.util.FileUtil.DELIMITER;

public class ValidatorLineCSV implements Validator {

    private static final int INDEX_AGENCY = 0;
    private static final int INDEX_ACCOUNT_NUMBER = 1;
    private static final int INDEX_BALANCE = 2;
    private static final int INDEX_STATUS = 3;
    private static final int LENGHT_CSV = 4;

    public boolean validate(Object o) {
        if (o == null || !(o instanceof String)){
            return false;
        }
        String line = (String) o;
        try{
            String[] accountLine = line.split(DELIMITER);
            if (accountLine.length != LENGHT_CSV)return false;

            String agency = accountLine[INDEX_AGENCY].replaceAll(" ", "");
            if (!agency.matches("[0-9]{4}")) {
                return false;
            }

            String fullAccountNumber = accountLine[INDEX_ACCOUNT_NUMBER].replaceAll(" ", "");;
            if (!fullAccountNumber.matches("[0-9]{5}-[0-9]{1}")) {
                return false;
            }

            try{
                CastUtil.toBigDecimal(accountLine[INDEX_BALANCE]);
            }catch (Exception e){
                return false;
            }

            if (AccountStatus.from(accountLine[INDEX_STATUS]) == null){
                return false;
            }

        }catch (Exception e){
            return false;
        }

        return true;
    }
}
