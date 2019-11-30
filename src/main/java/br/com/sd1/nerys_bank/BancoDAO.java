
package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import br.com.sd1.nerys_bank.Modelo.TransacaoList;

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
    public TransacaoList getTransacoes(Integer num_conta);
    public boolean saque(Integer num_conta, BigDecimal vlr_saque);
    public Transacao deposito(Integer num_conta, BigDecimal vlr_deposito);
    public Transacao transferencia(Integer num_conta_tr, Integer num_conta_dest, BigDecimal vlr_transferencia);
    
}
