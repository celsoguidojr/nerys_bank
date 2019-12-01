/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.view;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import br.com.sd1.nerys_bank.Modelo.Conta;

import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	private Button btnMostrarSaldo;

	@FXML
	void Cancelar() {
		mudarTela("principal");
	}

	@FXML
	private void getSaldo() {

		/*
		 * RestTemplate restTemplate = new RestTemplate(); String url =
		 * gerarUrlSaldo(num_conta); ResponseEntity<String> response =
		 * restTemplate.getForEntity(url, String.class);
		 * 
		 * txtValorsSaldo.setText(response.getBody());
		 */

		Conta conta = new Conta(DadosLogin.getNum_agencia(), DadosLogin.getNum_conta(), DadosLogin.getSenha());
		txtAgencia.setText(conta.getNum_agencia().toString());
		txtConta.setText(conta.getNumConta().toString());
		txtValorsSaldo.setText(getURLData(gerarUrlSaldo(conta.getNumConta())));

	}

	public static String getURLData(String url) {

		// cria um StringBuilder para armazenar a saída
		StringBuilder saida = new StringBuilder();

		try {

			// cria uma url com o endereço passado
			URL u = new URL(url);

			// abre a conexão com a url criada
			URLConnection uCon = u.openConnection();

			// obtém o input stream da conexão
			InputStream in = uCon.getInputStream();

			// um buffer para a leitura dos dados obtidos no input stream
			byte[] buffer = new byte[2048];

			// tenta colocar dados dentro do buffer. enquanto existirem dados
			// (resultado da leitura diferentede -1), a execução continua
			while (in.read(buffer) != -1) {

				// faz o append dos dados lidos na saida (StringBuilder)
				saida.append(new String(buffer));

			}

			// fecha o input stream
			in.close();

			// tratamento de excessões...
		} catch (MalformedURLException exc) {

			saida.append("ERRO: URL mal formada.");
			exc.printStackTrace();

		} catch (IOException exc) {

			saida.append("IOException");
			exc.printStackTrace();

		} catch (SecurityException exc) {

			saida.append("ERRO: Não há permissão para conexão.");
			exc.printStackTrace();

		} catch (IllegalArgumentException exc) {

			saida.append("ERRO: O proxy é null ou de tipo incorreto.");
			exc.printStackTrace();

		} catch (UnsupportedOperationException exc) {

			saida.append("ERRO: A subclasse que implementa o protocolo não suporta este método.");
			exc.printStackTrace();

		}

		// retorna o que existe na saída na forma de uma String
		return saida.toString();

	}

	private String gerarUrlSaldo(int num_conta) {
		String retorno = "" + URL_WEBSERVICE + "saldo?num_conta=" + num_conta;
		return retorno;
	}
	
	@FXML
    public void cancel() {
		txtConta.setText("");
		txtAgencia.setText("");
		txtValorsSaldo.setText("");
    	mudarTela("principal");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
