/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Felip
 */
public interface BancoDAO {
    
    //Metodos para interagir com o banco de dados
    // As regras serão feitas em outros métodos
    public Integer cadastrarCliente(Cliente cliente);
    //Fará o registro da conta no banco de dados VLW FLW TAMO JUNTO E MISTURADO
    public Integer abrirConta(Conta conta);
    public BigDecimal getSaldo(Integer num_conta);
    public Integer gravarTransacao(Transacao transacao);
    public List<Transacao> getTransacoes(Integer num_conta);
    public boolean saque(Integer num_conta);
    public boolean deposito(Integer num_conta, BigDecimal vlr_deposito);
}
