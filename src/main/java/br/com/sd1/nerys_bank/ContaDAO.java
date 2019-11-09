/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Conta;
import java.util.List;

public interface ContaDAO {
	public Conta consultar(String nome_cliente);
	public Conta consultar(Integer num_conta);
	public boolean inserir (Conta conta);
	public boolean excluir (Conta conta);
	public List<Conta> listar(String nome_cliente);
}
