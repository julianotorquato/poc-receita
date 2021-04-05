package br.com.southsystem.pocreceita.service.mapper;

import br.com.southsystem.pocreceita.domain.Account;
import br.com.southsystem.pocreceita.domain.enumeration.AccountStatus;
import br.com.southsystem.pocreceita.service.util.CastUtil;
import br.com.southsystem.pocreceita.service.util.StringUtil;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<String[], Account>{

    default Account toEntity(String[] accountArray) {
        if (accountArray == null) {
            return null;
        }
      return Account.builder()
                .agency(accountArray[0])
                .accountDigit(StringUtil.getFirstValueWithSplitBy(accountArray[1], "-", 1))
                .accountNumber(StringUtil.getFirstValueWithSplitBy(accountArray[1], "-", 0))
                .balance(CastUtil.toBigDecimal(accountArray[2]))
                .status(AccountStatus.from(accountArray[3]))
                .build();
    }

}


