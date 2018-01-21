package com.mabanque.service;


import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mabanque.dao.*;
import com.mabanque.entities.*;

import javassist.CodeConverter;

@Service
@Transactional  //Pour definir les methodes comme transactionnelles. 
public class BanqueMetierImpl implements IBanqueMetier {
	
	//Cette annotation permet de dire a Spring d'injecter une implementation de cette interface.
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Compte consulterCompte(String codeCompte) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findOne(codeCompte);
		if (cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		
		double facilitesCaisse = 0;
		
		if(cp instanceof CompteCourant) {
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		}
		
		if(cp.getSolde() + facilitesCaisse < montant) {
			throw new RuntimeException("Solde Insuffisant");
		}
		
		
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		// TODO Auto-generated method stub
		if(codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("Imnpossible virement sur le meme compte");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		
		return operationRepository.listOperation(codeCpte, new PageRequest(page, size));
	}

}
