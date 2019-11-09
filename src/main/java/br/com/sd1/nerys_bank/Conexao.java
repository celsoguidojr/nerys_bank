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

	private Connection conexaoBanco = null;
	
	public Conexao()
	{
		String url = "jdbc:postgresql://localhost/nerys_bank?user=postgres&password=1029";
		try {
			conexaoBanco = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexao()
	{
		return conexaoBanco;
	}
	
}
