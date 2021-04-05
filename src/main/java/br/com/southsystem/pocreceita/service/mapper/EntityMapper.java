package br.com.southsystem.pocreceita.service.mapper;

public interface EntityMapper  <D, E> {

    E toEntity(D dto);

}
