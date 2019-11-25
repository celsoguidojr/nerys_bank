/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Cliente;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FrmSaldoController implements Initializable {

	  private static String URL_WEBSERVICE  =
			    "http://localhost:8080/";
    @FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtConta;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtValorsSaldo;

    @FXML
    void Cancelar() {

    }
    
    
    private void getSaldo(int num_conta)
    {
    	  HttpURLConnection connection = null;

    	/*  try {
    		  
    	     // URL url = new URL(gerarUrlSaldo(num_conta));      
    	    //  connection = (HttpURLConnection)url.openConnection();
    	    //  InputStream content = connection.getInputStream();
    	     // txtValorsSaldo.setText(out.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    
    private String gerarUrlSaldo(int num_conta)
    {
    	String retorno = ""+URL_WEBSERVICE+"saldo?num_conta="+num_conta;
    	return retorno;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getSaldo(1);
		
	}
    
    
}
