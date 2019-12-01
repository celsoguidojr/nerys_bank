/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection conexaoBanco = null;
	
	private static String urlServidor = "http://localhost:8989/";
	
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
		return urlServidor;
	}
	
	public static void setIpServidor(String ipServidor)
	{
		Conexao.urlServidor =  ipServidor;
	}
}
