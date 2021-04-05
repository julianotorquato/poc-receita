package br.com.southsystem.pocreceita.domain.validator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

@SpringBootTest
public class ValidatorLineCSVTests {

    @Test
    public void givenNull_thenExpectFalse(){
        ValidatorLineCSV validatorLineCSV = new ValidatorLineCSV();
        boolean espect_false = validatorLineCSV.validate(null);
        AssertionErrors.assertFalse("Null not ok",espect_false);
    }

    @Test
    public void givenLineAllCorrect_thenExpectTrue(){
        String line_csv = "0101;12225-6;100,00;A";
        ValidatorLineCSV validatorLineCSV = new ValidatorLineCSV();
        boolean espect_true = validatorLineCSV.validate(line_csv);
        AssertionErrors.assertTrue("All Line CSV is ok",espect_true);
    }

    @Test
    public void givenMore4Item_thenExpectFalse(){
        String line_csv = "0101;12225-6;100,00;A;ZZ";
        ValidatorLineCSV validatorLineCSV = new ValidatorLineCSV();
        boolean espect_false = validatorLineCSV.validate(line_csv);
        AssertionErrors.assertFalse("More 4 atributes",espect_false);
    }


    @Test
    public void givenStatusAccountWrong_thenExpectFalse(){
        String line_csv = "0101;12225-6;100,00;X";
        ValidatorLineCSV validatorLineCSV = new ValidatorLineCSV();
        boolean espect_false = validatorLineCSV.validate(line_csv);
        AssertionErrors.assertFalse("Status Account is diferent",espect_false);
    }

}
