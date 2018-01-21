package com.mabanque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mabanque.entities.Compte;
import com.mabanque.entities.Operation;
import com.mabanque.service.IBanqueMetier;

@Controller
public class BanqueController {
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")
	public String comsulter(Model model, String codeCompte, 
			@RequestParam(name="page", defaultValue="0")int page, 
			@RequestParam(name="size", defaultValue="5")int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
			System.out.println("LISTZE_ " + pageOperations.getContent());
			model.addAttribute("listOperation", pageOperations.getContent());
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("compte", cp);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}
		
		return "comptes";
	}
	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, String codeCompte, String typeOperation, double montant, String codeCompte2) {
		
		try {
		if(typeOperation.equals("VERS")) {
			banqueMetier.verser(codeCompte, montant);
		}
		else if (typeOperation.equals("RETR")) {
			banqueMetier.retirer(codeCompte, montant);;
		}
		else {
			banqueMetier.virement(codeCompte, codeCompte2, montant);
		}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
