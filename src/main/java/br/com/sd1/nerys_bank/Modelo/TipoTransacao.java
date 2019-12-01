package br.com.sd1.nerys_bank.Modelo;

import java.util.Arrays;
import java.util.Optional;

public enum TipoTransacao {
   
	    SAQUE(1), DEPOSITO(2), TRANSFERENCIA(3);
	     
	    private int valor;
	    
	    TipoTransacao(int valorOpcao){
	        valor = valorOpcao;
	    }
	    
	    public int getValor(){
	        return valor;
	    } 
}
