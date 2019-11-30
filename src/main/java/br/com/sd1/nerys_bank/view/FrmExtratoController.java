package br.com.sd1.nerys_bank.view;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import br.com.sd1.nerys_bank.Modelo.TransacaoList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class FrmExtratoController implements Initializable{

	private static String URL_WEBSERVICE = "http://localhost:8989/";

	
    @FXML
    private TableView<Transacao> tableDados;

    @FXML
    private TableColumn<Transacao, OffsetDateTime> colDtTransacao;
    
    private TableColumn<Transacao,Integer> colNumTransacao;

    @FXML
    private TableColumn<Transacao, Integer> colContaOrigem;
    
    @FXML
    private TableColumn<Transacao, Integer> colTipo;

    @FXML
    private TableColumn<Transacao, BigDecimal> colValor;

    @FXML
    private TableColumn<Transacao, Integer> colContaDestino;
    
    @FXML
    private TableColumn<Transacao, String> colStatus;
    
    @FXML
    private TextField txtAgencia;

    @FXML
    private DatePicker dtFinal;

    @FXML
    private Button btnConsultar;

    @FXML
    private DatePicker dtInicial;



    @FXML
    private TextField txtConta;
    

    @FXML
    private Button btnCancelar;



    @FXML
    private TextField txtNomeCliente;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
    
	@FXML
	void consultar() {

		RestTemplate restTemplate = new RestTemplate();
		String url = gerarUrl(DadosLogin.getNum_conta());

		TransacaoList response = restTemplate.getForObject(url, TransacaoList.class);
		//List<Transacao> lista = response.getTransacoes();

		// System.out.println(lista[0].getVlr_transacao());
		// txtValorsSaldo.setText(response.getBody());

		// List<Transacao> msgRetorno = (List<Transacao>)getURLData(gerarUrl());

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		// alert.setHeaderText(msgRetorno);
		alert.showAndWait();
	}

	@FXML
	void cancel() {
		txtConta.setText("");
		txtAgencia.setText("");
		txtNomeCliente.setText("");
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

	private String gerarUrl(int num_conta) {
		String retorno = URL_WEBSERVICE + "extrato?num_conta=" + num_conta;
		return retorno;
	}



}
