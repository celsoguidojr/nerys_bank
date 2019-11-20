package br.com.sd1.nerys_bank.Comunicacao;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;


@RestController
public class BancoController {
	BancoDAOImplementacao banco = new BancoDAOImplementacao();
	
	@RequestMapping("/")
	public String teste(){
		return "Deu certo";
	}
	
	@RequestMapping("/saldo")
	public BigDecimal getSaldo(Integer num_conta) {
		return banco.getSaldo(num_conta);
	}
	
	
	@RequestMapping("/saque")
	public String saque(Integer num_conta, BigDecimal vlr_saque) {
		if(banco.saque(num_conta, vlr_saque)) {
			return "Saque realizado no valor de: "+vlr_saque;
		}else {
			return "Impossivel realizar o saque";
		}
	}
	
	@RequestMapping("/deposito")
	public String deposito(Integer num_conta, BigDecimal vlr_deposito) {
		if(banco.deposito(num_conta, vlr_deposito)!=null) {
			return "Deposito realizado no valor de: "+vlr_deposito;
		}else {
			return "Impossivel realizar o deposito";
		}
	}
	
	
	@RequestMapping("/transferencia")
	public String transferencia(Integer num_conta_tr, Integer num_conta_dest, BigDecimal vlr_transferencia) {
		if(banco.transferencia(num_conta_tr, num_conta_dest, vlr_transferencia)!=null) {
			return "Transferencia realizada no valor de: "+vlr_transferencia;
		}else {
			return "Impossivel realizar a transferencia";
		}
	}
	
	
	
	
	
}
