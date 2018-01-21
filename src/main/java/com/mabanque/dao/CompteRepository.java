package com.mabanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mabanque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
