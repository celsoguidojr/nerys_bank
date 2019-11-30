/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.Modelo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoList {
    
	private List<Transacao> movimentacoes;
	    
    public TransacaoList()
    {
    	super();
    	movimentacoes = new ArrayList<Transacao>(); 
    }
    
    public List<Transacao> getTransacoes()
    {
    	return movimentacoes;
    }
    
    public void setList(List<Transacao> lista)
    {
    	this.movimentacoes = lista;
    }

}
