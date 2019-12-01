package br.com.sd1.nerys_bank.view;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class FrmContaController implements Initializable {
	
    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtAgencia;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtTipoConta;

    @FXML
    private TextField txtIDCliente;



	private Cliente clienteAtual = new Cliente();
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    @FXML
    private void gravarConta()
    {
		BancoDAOImplementacao banco = new BancoDAOImplementacao();
	
    	Conta novaConta = new Conta();
    	novaConta.setId_titular(Integer.valueOf(txtIDCliente.getText()));
    	novaConta.setNum_agencia(Integer.valueOf(txtAgencia.getText()));
    	novaConta.setFlg_tipo_conta(Integer.valueOf(txtTipoConta.getText()));
    	novaConta.setVlr_saldo(BigDecimal.valueOf(0));
     	novaConta.setNum_agencia(Integer.valueOf(txtAgencia.getText()));
    	novaConta.setSenha(txtSenha.getText());
    		
		banco.abrirConta(novaConta);    
    }
    
    @FXML
    public void cancel() {
    	mudarTela("principal");
    }
    
}
