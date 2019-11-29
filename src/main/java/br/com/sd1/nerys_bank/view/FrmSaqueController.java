package br.com.sd1.nerys_bank.view;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import br.com.sd1.nerys_bank.Modelo.Conta;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FrmSaqueController {
	private static String URL_WEBSERVICE = "http://localhost:8989/";

	@FXML
	private TextField txtAgencia;

	@FXML
	private TextField txtConta;

	@FXML
	private TextField txtValorSaque;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label txtSacado;

	@FXML
	void Cancelar() {
		mudarTela("principal");
	}

	@FXML
	void enviarSaque() {
		Conta conta = new Conta(DadosLogin.getNum_agencia(), DadosLogin.getNum_conta(), DadosLogin.getSenha());
		txtSacado.setText(getURLData(
				gerarUrlSaque(conta.getNumConta(), BigDecimal.valueOf(Float.parseFloat(txtValorSaque.getText())))));
	}
	
	@FXML
    void cancel() {
    	mudarTela("principal");
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

	private String gerarUrlSaque(int num_conta, BigDecimal vlr_saque) {
		String retorno = "" + URL_WEBSERVICE + "saque?num_conta=" + num_conta + "&vlr_saque=" + vlr_saque;
		return retorno;
	}

}
