/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import br.com.sd1.nerys_bank.BancoDAOImplementacao;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;
import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FrmSaldoController implements Initializable {

	private static String URL_WEBSERVICE = "http://localhost:8989/";
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

	private void getSaldo(Conta conta) {

	/*	RestTemplate restTemplate = new RestTemplate();
		String url = gerarUrlSaldo(num_conta);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		txtValorsSaldo.setText(response.getBody());*/
		
		txtConta.setText(conta.getNumConta().toString());
		txtValorsSaldo.setText(getURLData(gerarUrlSaldo(conta.getNumConta())));
		
	}
	
	
	
	public static String getURLData( String url ) {
	    
	    // cria um StringBuilder para armazenar a saída
	    StringBuilder saida = new StringBuilder();
	    
	    try {
	        
	        // cria uma url com o endereço passado
	        URL u = new URL( url );
	        
	        // abre a conexão com a url criada
	        URLConnection uCon = u.openConnection();
	        
	        // obtém o input stream da conexão
	        InputStream in = uCon.getInputStream();
	        
	        // um buffer para a leitura dos dados obtidos no input stream
	        byte[] buffer = new byte[2048];	        
	        
	        // tenta colocar dados dentro do buffer. enquanto existirem dados 
	        //(resultado da leitura diferentede -1), a execução continua
	        while ( in.read( buffer ) != -1 ) {
	            
	            // faz o append dos dados lidos na saida (StringBuilder)
	            saida.append( new String( buffer ) );
	            
	        }
	        
	        // fecha o input stream
	        in.close();            
	    
	        // tratamento de excessões...   
	    } catch ( MalformedURLException exc )  {
	        
	        saida.append( "ERRO: URL mal formada." );
	        exc.printStackTrace();
	        
	    } catch ( IOException exc ) {
	        
	        saida.append( "IOException" );
	        exc.printStackTrace();
	        
	    } catch ( SecurityException exc ) {
	        
	        saida.append( "ERRO: Não há permissão para conexão." );
	        exc.printStackTrace();
	        
	    } catch ( IllegalArgumentException exc ) {
	        
	        saida.append( "ERRO: O proxy é null ou de tipo incorreto." );
	        exc.printStackTrace();
	        
	    } catch ( UnsupportedOperationException exc ) {
	        
	        saida.append( "ERRO: A subclasse que implementa o protocolo não suporta este método." );
	        exc.printStackTrace();
	        
	    }
	    
	    // retorna o que existe na saída na forma de uma String
	    return saida.toString();
	    
	}

	private String gerarUrlSaldo(int num_conta) {
		String retorno = "" + URL_WEBSERVICE + "saldo?num_conta=" + num_conta;
		return retorno;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Conta conta = new Conta(new Integer(1), new Integer(1),new BigDecimal(1), 1,new String(""));
		
		getSaldo(conta);

	}

}
