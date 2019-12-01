package br.com.sd1.nerys_bank.view;
import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.Conexao;
import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import br.com.sd1.nerys_bank.Modelo.Conta;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import br.com.sd1.nerys_bank.Modelo.TransacaoList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;


public class FrmLoginController {
	
	private static String URL_WEBSERVICE = Conexao.getIpServidor();
	
	@FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtConta;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtSenha1;

    @FXML
    private PasswordField txtSenha;
	
    @FXML
	void cancelar() {
		mudarTela("sair");
	}
	
    @FXML
    void validarLogin() {	
    	DadosLogin.setNum_conta(Integer.parseInt(txtConta.getText()));
		DadosLogin.setNum_agencia(Integer.parseInt(txtAgencia.getText()));
		DadosLogin.setSenha(txtSenha.getText());
		
    	RestTemplate restTemplate = new RestTemplate();
		String url = gerarUrl(DadosLogin.getNum_agencia(), DadosLogin.getNum_conta(), DadosLogin.getSenha());
		Conta response = restTemplate.getForObject(url, Conta.class);

    	if(response != null) {
    		mudarTela("principal");
    		txtConta.setText("");
    		txtAgencia.setText("");
    		txtSenha.setText("");
    	}else if(Integer.parseInt(txtConta.getText()) == 0 && Integer.parseInt(txtAgencia.getText()) == 0 && txtSenha.getText().equals("omelhorbanco")) {
    			mudarTela("cliente");	
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Atenção");
    		alert.setHeaderText("Problema no Login");
    		alert.setContentText("Verifique os dados digitados e tente novamente.");
    		alert.showAndWait();
    	}
    }
    
	private String gerarUrl(int num_conta,int num_agencia,String senha) {
		String retorno = URL_WEBSERVICE + "consultar_conta?num_conta=" + num_conta+"&num_agencia="+num_agencia+"&senha="+ senha;
		return retorno;
	}
    
    
    

    
}