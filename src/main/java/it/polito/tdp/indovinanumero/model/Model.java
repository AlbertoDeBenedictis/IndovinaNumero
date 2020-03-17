package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	
	
	public Model() {
		
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		
		// ho copiato dal controller originale SOLO LA LOGICA DEL GIOCO, perchè questa è di competenza del modello
		this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		
		//controllo se la partita è effettivamente in corso
		
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata (o non è iniziata)\n");
		}
		
		// controllare l'input  (ricordiamo che il controller controlla che l'input sia un numero 
		
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un NUOVO numero compreso tra 1 e "+NMAX+"\n");
		}
		
		//se arriviamo qui, il tentativo è valido, possiamo "provarlo":
		
    	this.tentativiFatti ++;
    	this.tentativi.add(tentativo);
    	
    	if(tentativiFatti == TMAX) {
    		this.inGioco = false;
    	}
    	
    	// se indovino:
    	
    	if(tentativo == this.segreto) {
    		//HO INDOVINATO!
    		
    		this.inGioco = false;
    		return 0;
    	}
    	if(tentativo < this.segreto) {
    		return -1;
    	}else {
    		return 1;
    	}
    	// se tentativo minore di segreto:
    	
    	
		
		
	}
	
	private boolean tentativoValido(int tentativo) {
		
		if(tentativo < 1 || tentativo>NMAX) {
			return false;
		}
		else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
	
}
