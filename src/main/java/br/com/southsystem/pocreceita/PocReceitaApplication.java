package br.com.southsystem.pocreceita;

import br.com.southsystem.pocreceita.service.PocInitializrFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static br.com.southsystem.pocreceita.service.util.StringUtil.getPositionArray;


@SpringBootApplication
public class PocReceitaApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PocReceitaApplication.class);

	@Autowired
	private PocInitializrFacade pocInitializrFacade;

	public static void main(String[] args) {
		SpringApplication.run(PocReceitaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("\n----------------------------------------------------------\n\t" +
						"Application 'POC Receita' is running! \n----------------------------------------------------------"
				);
		pocInitializrFacade.startTask(getPositionArray(args, 0));
	}
}
