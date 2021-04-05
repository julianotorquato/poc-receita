package br.com.southsystem.pocreceita.service.mapper;

import br.com.southsystem.pocreceita.domain.Account;
import br.com.southsystem.pocreceita.domain.AccountResult;
import br.com.southsystem.pocreceita.domain.enumeration.AccountStatus;
import br.com.southsystem.pocreceita.service.util.CastUtil;
import br.com.southsystem.pocreceita.service.util.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AccountResultMapper extends EntityMapper<Account, AccountResult>{

    AccountResult toEntity(Account dto);


}


