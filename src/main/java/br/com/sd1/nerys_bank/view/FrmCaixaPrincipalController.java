package br.com.sd1.nerys_bank.view;

import java.awt.event.ActionEvent;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.fxml.FXML;
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
    void getSaldo() {
    	mudarTela("saldo");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
