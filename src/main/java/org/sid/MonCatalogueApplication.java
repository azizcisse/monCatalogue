package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MonCatalogueApplication implements CommandLineRunner{
	
    @Autowired
	private ProduitRepository produitRepository;

	public static void main(String[] args) {
		SpringApplication.run(MonCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		produitRepository.save(new Produit(null, "Ordinateur HP 5643", 6500, 54));
        produitRepository.save(new Produit(null, "Imprmante HP 1122", 345, 11));		
		produitRepository.save(new Produit(null, "Smart Phone", 1200, 145));	
		
		Page<Produit> produits=produitRepository.findByDesignationContains("H", PageRequest.of(0, 2));
		System.out.println(produits.getSize());
		System.out.println(produits.getTotalElements());
		System.out.println(produits.getTotalPages());
		produits.getContent().forEach(p->{
			System.out.println(p.toString());
		});

		System.out.println("*********************************************");

		Page<Produit> prods=produitRepository.chercher("%H%", 400, PageRequest.of(0, 2));
		System.out.println(prods.getSize());
		System.out.println(prods.getTotalElements());
		System.out.println(prods.getTotalPages());
		prods.getContent().forEach(p->{
			System.out.println(p.toString());
		});
	}

}
