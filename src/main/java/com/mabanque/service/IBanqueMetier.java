package com.mabanque.service;

import org.springframework.data.domain.Page;

import com.mabanque.entities.*;

public interface IBanqueMetier {
	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCpte, double montant);
	public void retirer(String codeCpte, double montant);
	public void virement(String codeCpte1, String codeCpte2, double montant);
	public Page<Operation> listOperation(String codeCpte, int page, int size);
	
}
