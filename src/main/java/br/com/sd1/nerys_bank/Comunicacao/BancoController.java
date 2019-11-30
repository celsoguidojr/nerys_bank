package br.com.sd1.nerys_bank.Comunicacao;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Transacao;

@RestController
public class BancoController {
	BancoDAOImplementacao banco = new BancoDAOImplementacao();

	@RequestMapping(value = "/saldo", method = RequestMethod.GET)
	public BigDecimal getSaldo(Integer num_conta) {
		return banco.getSaldo(num_conta);
	}

	@RequestMapping("/saque")
	public String saque(Integer num_conta, BigDecimal vlr_saque) {
		if (banco.saque(num_conta, vlr_saque)) {
			return "Saque realizado: " + vlr_saque;
		} else {
			return "Impossível realizar o saque.";
		}
	}

	@RequestMapping("/deposito")
	public String deposito(Integer num_agencia, Integer num_conta, BigDecimal vlr_deposito) {
		Transacao deposito = banco.deposito(num_conta, vlr_deposito);
		if(deposito != null) {
			deposito.getNum_transacao();
			StringBuilder msgRetorno = new StringBuilder();
			msgRetorno.append("-----Depósito realizado!-----\n");
			msgRetorno.append("Nº Operação: "+ deposito.getNum_transacao() + "\n");
			msgRetorno.append("Nº Conta: " + deposito.getNum_conta_dest() + "\n");
			msgRetorno.append("Valor: "+ deposito.getVlr_transacao()+"\n");
			
			return msgRetorno.toString();			
		}else {
			return "Impossível realizar o depósito";
		}
	}

	@RequestMapping("/transferencia")
	public String transferencia(Integer num_conta_tr, Integer num_conta_dest, BigDecimal vlr_transferencia) {
		
		Transacao transferencia = banco.transferencia(num_conta_tr, num_conta_dest, vlr_transferencia);
		
		if(transferencia != null) {
			
			StringBuilder msgRetorno = new StringBuilder();
			msgRetorno.append("-----Transferência realizada!-----\n");
			msgRetorno.append("Nº Operação: "+ transferencia.getNum_transacao() + "\n");
			msgRetorno.append("Nº Conta: " + transferencia.getNum_conta_dest() + "\n");
			msgRetorno.append("Valor: "+ transferencia.getVlr_transacao()+"\n");
			
			return msgRetorno.toString();			
		}else {
			return "Impossível realizar a transferência.";
		}
		
	}

	@RequestMapping("/cadastrar_cliente")
	public String cadastrarCliente(Integer id_client, String nome_cliente, String num_cpf, String logradouro,
			String cidade, String uf) {
		Cliente cliente = new Cliente(id_client, nome_cliente, num_cpf, logradouro, cidade, uf);
		if (banco.cadastrarCliente(cliente) > 0) {
			return "Cliente cadastrado: ";
		} else {
			return "Impossível cadastrar cliente.";
		}
	}

	@RequestMapping("/extrato")
	public String getTransacoes(Integer num_conta) {
		ObjectMapper mapper = new ObjectMapper();
		String retorno = "";
		try {
			retorno = mapper.writeValueAsString(banco.getTransacoes(num_conta));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}
