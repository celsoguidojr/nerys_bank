package br.com.sd1.nerys_bank.view;

import static br.com.sd1.nerys_bank.MainApp.mudarTela;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.text.TabExpander;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sd1.nerys_bank.Conexao;
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
import javafx.scene.control.cell.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.collections.*;

public class FrmExtratoController implements Initializable {

	private static String URL_WEBSERVICE = Conexao.getIpServidor();

	ObservableList<Transacao> obList = FXCollections.observableArrayList();

	@FXML
	private TableView<Transacao> tableDados;

	@FXML
	private TableColumn<Transacao, LocalDateTime> colDtTransacao;

	@FXML
	private TableColumn<Transacao, Integer> colNumTransacao;

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
		colDtTransacao.setCellValueFactory(new PropertyValueFactory("dt_transacao"));
		colNumTransacao.setCellValueFactory(new PropertyValueFactory("num_transacao"));
		colContaOrigem.setCellValueFactory(new PropertyValueFactory("num_conta_tr"));
		colContaDestino.setCellValueFactory(new PropertyValueFactory("num_conta_dest"));
		colValor.setCellValueFactory(new PropertyValueFactory("vlr_transacao"));
		colStatus.setCellValueFactory(new PropertyValueFactory("flg_status_tr"));
		tableDados.setItems(obList);

		txtAgencia.setText(DadosLogin.getNum_agencia().toString());
		txtConta.setText(DadosLogin.getNum_conta().toString());
	}

	@FXML
	void consultar() {
		RestTemplate restTemplate = new RestTemplate();

		String url = gerarUrl(DadosLogin.getNum_conta());

		TransacaoList response = restTemplate.getForObject(url, TransacaoList.class);

		List<Transacao> lista = response.getTransacoes();

		convertListToObjList(lista);

		tableDados.setItems(obList);
	}

	private void convertListToObjList(List<Transacao> lista) {
		for (int i = 0; i < lista.size(); i++) {
			obList.add(lista.get(i));
		}
	}

	@FXML
	void cancel() {
		txtConta.setText("");
		txtAgencia.setText("");
		txtNomeCliente.setText("");
		mudarTela("principal");
	}


	private String gerarUrl(int num_conta) {
		String retorno = URL_WEBSERVICE + "extrato?num_conta=" + num_conta;
		return retorno;
	}

}
