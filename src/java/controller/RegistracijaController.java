/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.KorisnikDAO;
import beans.Korisnik;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Obuka
 */
@Named(value = "registracijaController")
@Dependent
public class RegistracijaController {

    /**
     * Creates a new instance of RegistracijaController
     */
    public RegistracijaController() {
    }
   
    
}
