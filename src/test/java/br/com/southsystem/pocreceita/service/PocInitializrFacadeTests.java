package br.com.southsystem.pocreceita.service;

import br.com.southsystem.pocreceita.service.erros.CSVNotFountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PocInitializrFacadeTests {

    @Autowired
    private PocInitializrFacade pocInitializrFacade;

    @Test
    public void givenWrongPath_thenReturnExeption() throws Exception{
        assertThrows(CSVNotFountException.class, () -> {
            pocInitializrFacade.startTask("teste");
        });
    }

    @Test
    public void givenPath_thenReturnExeption() throws Exception{
        pocInitializrFacade.startTask(null);
    }

}
