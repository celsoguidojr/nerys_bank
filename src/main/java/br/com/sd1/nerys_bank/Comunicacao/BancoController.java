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
			return "Saque realizado no valor de: ";
		}else {
			return "Impossivel realizar o saque";
		}
	}
	
}
