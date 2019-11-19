
package br.com.sd1.nerys_bank.Comunicacao;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BancoController {
    @RequestMapping("/")
    public String teste(){
        return "Deu certo";
    }
    
    @RequestMapping("/adicao")
    public BigDecimal getSaldo(Integer numConta){
        BancoDAOImplementacao banco = new BancoDAOImplementacao();
        return banco.getSaldo(numConta);
    }
}
