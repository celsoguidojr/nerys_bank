package br.com.sd1.nerys_bank.view;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;


public class FrmCaixaPrincipalController implements Initializable {

    @FXML
    private MenuItem btnExtrato;

    @FXML
    private Menu btnMudarConta;

    @FXML
    private MenuItem btnSaldo;

    @FXML
    private Menu btnOperacoes;

    @FXML
    private Pane pnInfoConta;

    @FXML
    private Menu btnSair;

    @FXML
    private MenuItem btnDeposito;

    @FXML
    private MenuItem btnSaque;

    @FXML
    private MenuItem btnTransferencia;
    
    @FXML
    private MenuBar menuOpcoes;
    
      
    @FXML
    void getExtrato() {
		mudarTela("extrato");
    }

    @FXML
    void getSaldo() {
    	mudarTela("saldo");
    }
    
    @FXML
    void getDeposito() {
    	mudarTela("deposito");
    }
    
    @FXML
    void getSaque() {
    	mudarTela("saque");
    }
    
        @FXML
    void getTransferencia() {
    	mudarTela("transferencia");
    }
    
    @FXML
    void cancel() {
    	mudarTela("login");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
