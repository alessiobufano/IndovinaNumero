package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi;  //Per memorizzare tutti i tentativi

	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int t) {
		//restituisce 0 se corretto, 1 se più alto e -1 se più basso
		
		//controllo se partita in gioco:
		if(!inGioco)
			throw new IllegalStateException("La partita e' gia' terminata");
			
		if(!tentativoValido(t))
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX+"\n");
		
		//il tentativo e' valido --> possiamo provarlo
		this.tentativiFatti ++;
		this.tentativi.add(t);
		
		if(tentativiFatti == TMAX) 
    		this.inGioco = false; //Ho esaurito i tentativi -> HO PERSO
		
		if(t == this.segreto) {  //ho indovinato
    		this.inGioco = false;
    		return 0;
		}
		else if(t < this.segreto)
    		return -1;
    	else
    		return 1;	
	}
	
	public boolean tentativoValido(int t) { //può essere utile per verificare altri controlli
		if(t<1 || t>NMAX)
			return false;
		else if(this.tentativi.contains(t))
			return false;
		else
			return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

}
