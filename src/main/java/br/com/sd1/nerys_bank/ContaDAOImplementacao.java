/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Conta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOImplementacao implements ContaDAO{
	
	public Conta consultar(String nome_cliente) {
		PreparedStatement ps = null;
		ResultSet rs;
		Conta conta;
		Conexao conexao = null;
		
		try {
			conexao = new Conexao();
			ps = conexao.getConexao().prepareStatement("SELECT num_conta, nome_cliente, vlr_saldo FROM contas WHERE nome=?");
			ps.setString(1, nome_cliente);
			rs = ps.executeQuery();

			if (rs.next()) {
				
				conta = new Conta();
				conta.setId(rs.getInt("num_conta"));
				conta.setNome(rs.getString("nome_cliente"));
				conta.setSaldo(rs.getBigDecimal("vlr_saldo"));

				return conta;
				
			} else {
				
				return null;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexao.getConexao() != null) {
				try {
					conexao.getConexao().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean inserir(Conta conta) {
		PreparedStatement ps = null;
		int rs;		
		Conexao conexaoBanco = null;
		try {
			conexaoBanco = new Conexao();
			
			ps = conexaoBanco.getConexao().prepareStatement("INSERT INTO Contas(num_conta, nome_cliente, vlr_saldo) VALUES (?,?,?)");
			
			ps.setInt(1, conta.getId());
			ps.setString(2, conta.getNome());
			ps.setBigDecimal(3, conta.getSaldo());
			rs = ps.executeUpdate();

			if (rs > 0) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.getConexao().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Conta consultar(Integer num_conta) {
		PreparedStatement ps = null;
		ResultSet rs;
		Conexao conexaoBanco = null;
		Conta conta;
		try {
			conexaoBanco = new Conexao();
			ps = conexaoBanco.getConexao().prepareStatement("SELECT num_conta, nome_cliente, vlr_saldo FROM contas Wid=?");
			ps.setInt(1, num_conta);
			rs = ps.executeQuery();

			if (rs.next()) {
				conta = new Conta();

				conta.setId(rs.getInt("num_conta"));
				conta.setNome(rs.getString("nome_cliente"));
				conta.setSaldo(rs.getBigDecimal("vlr_saldo"));

				return conta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco.getConexao() != null) {
				try {
					conexaoBanco.getConexao().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean excluir(Conta conta) {
		PreparedStatement ps = null;
		Conexao conexaoBanco = null;
		try {
			conexaoBanco = new Conexao();

			ps = conexaoBanco.getConexao().prepareStatement("DELETE FROM contas WHERE id=?");
			ps.setInt(1, conta.getId());

			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.getConexao().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public List<Conta> listar(String nome_cliente) {
		PreparedStatement ps = null;
		ResultSet rs;
		
		Conexao conexao = null;
		List<Conta> listaContas = null;
		try {
			conexao = new Conexao();
			ps = conexao.getConexao().prepareStatement("SELECT num_conta, nome_cliente, vlr_saldo FROM contas WHERE nome_cliente=?");
			ps.setString(1, nome_cliente);
			rs = ps.executeQuery();

			listaContas = new ArrayList<Conta>();
			
			while (rs.next()) {
				
				Conta conta = new Conta();
				conta.setId(rs.getInt("num_conta"));
				conta.setNome(rs.getString("nome_cliente"));
				conta.setSaldo(rs.getBigDecimal("vlr_saldo"));

				listaContas.add(conta);				
			} 
			
			return listaContas;
				
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexao.getConexao() != null) {
				try {
					conexao.getConexao().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
