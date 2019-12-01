package br.com.sd1.nerys_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import br.com.sd1.nerys_bank.Comunicacao.DadosLogin;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import br.com.sd1.nerys_bank.Modelo.TransacaoList;

public class Conexao {

	private static Connection conexaoBanco = null;
	
	private static String urlServidorPrincipal = "http://25.8.16.130:8989/";
	private static String urlServidorSecundario = "http://25.33.127.207:8989/";
	
	private static String urlServidor = "";
	
	public Conexao()
	{
		String url = "jdbc:postgresql://localhost/nerys_bank?user=postgres&password=postgres";
		try {
			conexaoBanco = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConexao()
	{
		return conexaoBanco;
	}
	
	public static String getIpServidor()
	{
		Balanceamento();
		return urlServidor;
	}
	
	public static void setIpServidor(String ipServidor)
	{
		Conexao.urlServidor =  ipServidor;
	}	
	
	private static void Balanceamento()
	{
		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate. getForObject(urlServidorPrincipal, String.class);

		if(Integer.valueOf(response) > 0)
		{
			urlServidor = urlServidorPrincipal;	
		}
		else
		{
			response = restTemplate.getForObject(urlServidorSecundario, String.class);
			
			if(Integer.valueOf(response) > 0)
				urlServidor = urlServidorSecundario;
		}
	}
}
