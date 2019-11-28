package br.com.sd1.nerys_bank.view;
import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Conta;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class FrmLoginController {
	@FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtConta;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtSenha;
    
    @FXML
    void validarLogin() {
    	BancoDAOImplementacao banco = new BancoDAOImplementacao();
    	Conta conta = new Conta(Integer.parseInt(txtAgencia.getText()),Integer.parseInt(txtConta.getText()),txtSenha.getText());
    	//System.out.println(banco.getConta(conta).getNumConta());
    	if(banco.getConta(conta)!=null) {
    		mudarTela("principal");
    		DadosLogin.setNum_conta(Integer.parseInt(txtConta.getText()));
    		DadosLogin.setNum_agencia(Integer.parseInt(txtAgencia.getText()));
    		DadosLogin.setSenha(txtSenha.getText());
    		System.out.println(DadosLogin.getNum_conta());
    	}else {
    		
    	}
    }
    
}