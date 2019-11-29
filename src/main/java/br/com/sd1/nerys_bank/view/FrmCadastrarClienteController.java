/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Felip
 */
public class FrmCadastrarClienteController implements Initializable {

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtUF;

    @FXML
    private Button btnGravarCliente;

    @FXML
    private TextField txtID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void gravarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome_cliente(txtNomeCliente.getText());
        cliente.setNum_cpf(txtCPF.getText());
        cliente.setLogradouro(txtLogradouro.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setUf(txtUF.getText());

        BancoDAOImplementacao banco = new BancoDAOImplementacao();
        if(banco.cadastrarCliente(cliente) > 0)
        {
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Confirmação");
        	alert.setHeaderText("Cliente Cadastrado.");
        	alert.showAndWait();
        	mudarTela("conta");
        }
        else
        {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Erro");
        	alert.setHeaderText("Problema na gravação do cliente, tente novamente.");
        	alert.showAndWait();
        	return;
        }
    }
    
    
    @FXML
    public void cancel() {
    	mudarTela("principal");
    }
    
    @FXML
    void maskCPF(KeyEvent event) {
        TextFieldFormatter mask = new TextFieldFormatter();
        mask.setMask("###.###.###-##");
        mask.setCaracteresValidos("0123456789");
        mask.setTf(txtCPF);
        mask.formatter();
    }
    
    
}
