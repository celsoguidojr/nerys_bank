package br.com.sd1.nerys_bank.view;
import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Conta;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;


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
    private TextField txtSenha1;

    @FXML
    private PasswordField txtSenha;
	
    @FXML
	void cancelar() {
		mudarTela("sair");
	}
	
    @FXML
    void validarLogin() {
    	BancoDAOImplementacao banco = new BancoDAOImplementacao();
    	Conta conta = new Conta(Integer.parseInt(txtAgencia.getText()),Integer.parseInt(txtConta.getText()),txtSenha.getText());

    	if(banco.getConta(conta) != null) {
    		mudarTela("principal");
    		DadosLogin.setNum_conta(Integer.parseInt(txtConta.getText()));
    		DadosLogin.setNum_agencia(Integer.parseInt(txtAgencia.getText()));
    		DadosLogin.setSenha(txtSenha. getText());
    		txtConta.setText("");
    		txtAgencia.setText("");
    		txtSenha.setText("");
    	}else if(Integer.parseInt(txtConta.getText())== 0 && Integer.parseInt(txtAgencia.getText())== 0 && txtSenha.getText().equals("omelhorbanco")) {
    			mudarTela("cliente");	
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Atenção");
    		alert.setHeaderText("Problema no Login");
    		alert.setContentText("Verifique os dados digitados e tente novamente.");
    		alert.showAndWait();
    	}
    }
    
    

    
}