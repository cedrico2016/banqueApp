package com.mabanque;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mabanque.dao.*;
import com.mabanque.entities.*;
import com.mabanque.service.IBanqueMetier;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {  
	//L'interface CommandLineRunner permet de dire a SpringBoot de demarrer et d'appliquer la methode Run imediatement
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier iBanqueMetier;
	
	public static void main(String[] args) {
		
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
//		Client c1 = clientRepository.save( new Client("Hassan", "c.djahan@spirit47.com"));
//		Client c2 = clientRepository.save( new Client("Cedrico", "c.pipo@spirit47.com"));
//
//		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1, 6000));
//		Compte cp2 = compteRepository.save(new CompteCourant("c2", new Date(), 95000, c1, 12000));
//		Compte cp3 = compteRepository.save(new CompteEpargne("c3", new Date(), 95000, c1, 12000));
//		
//		operationRepository.save(new Retrait(new Date(), 1500, cp1));
//		operationRepository.save(new Versement(new Date(), 4544, cp2));
//		iBanqueMetier.verser("c1", 100000);
//		iBanqueMetier.verser("c1", 100000);
//		iBanqueMetier.verser("c1", 100000);
	}
}
