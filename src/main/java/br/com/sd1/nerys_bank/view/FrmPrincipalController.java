/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import br.com.sd1.nerys_bank.MainApp;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Cliente;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Felip
 */
public class FrmPrincipalController implements Initializable {

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
    
    
    public void gravarCliente(){
        Cliente cliente = new Cliente();
        
        
        cliente.setNome_cliente(txtNomeCliente.getText());
        cliente.setNum_cpf(parseInt(txtCPF.getText()));
        cliente.setLogradouro(txtLogradouro.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setUf(txtUF.getText());
        BancoDAOImplementacao banco = new BancoDAOImplementacao();
        banco.cadastrarCliente(cliente);
        
        mudarTela("conta");
    }
    
}