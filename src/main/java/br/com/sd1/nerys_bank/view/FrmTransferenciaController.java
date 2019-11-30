package br.com.sd1.nerys_bank.view;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FrmTransferenciaController {
	
	private static String URL_WEBSERVICE = "http://localhost:8989/";

	@FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtConta;

    @FXML
    private TextField txtValorTransferencia;

    @FXML
    private Button btnCancelar;

    @FXML
    void enviarTransferencia() {
		String msgRetorno = getURLData(
				gerarUrl(DadosLogin.getNum_conta(), Integer.valueOf(txtConta.getText()), BigDecimal.valueOf(Float.parseFloat(txtValorTransferencia.getText()))));

    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informação");
    	alert.setHeaderText(msgRetorno);
    	alert.showAndWait();

    }

    @FXML
    void cancel() {
    	txtConta.setText("");
    	txtValorTransferencia.setText("");
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

	private String gerarUrl(int num_conta_tr, int num_conta_dest, BigDecimal vlr_transferencia) {
		String retorno = "" + URL_WEBSERVICE + "transferencia?num_conta_tr=" + num_conta_tr +"&num_conta_dest="+ num_conta_dest  + "&vlr_transferencia=" + vlr_transferencia;
		return retorno;
	}

}

