package br.com.sd1.nerys_bank.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sd1.nerys_bank.Modelo.Cliente;
import javafx.fxml.Initializable;


public class FrmContaController implements Initializable {

	private Cliente clienteAtual = new Cliente();
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	criarCliente();
    }    
    
    private void criarCliente()
    {
    	clienteAtual.setId_client(1);
    }
    
}
