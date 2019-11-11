package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAOImplementacao implements BancoDAO {

    public Integer cadastrarCliente(Cliente cliente) {
        PreparedStatement ps = null;
        int rs;		
        Conexao conexaoBanco = null;
        try {
            
            conexaoBanco = new Conexao();

            StringBuilder comando = new StringBuilder();
            comando.append("INSERT INTO clientes(");
            comando.append("nome_cliente, ");
            comando.append("num_cpf, ");
            comando.append("logradouro, ");
            comando.append("cidade, ");
            comando.append("uf ) ");
            comando.append("VALUES (?,?,?,?,?)");
            
            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());
            
            ps.setString(1, cliente.getNome_cliente());
            ps.setInt(2, cliente.getNum_cpf());
            ps.setString(3, cliente.getLogradouro());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getUf());
                        
            rs = ps.executeUpdate();
            
            return rs; 
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

    public Integer abrirConta(Conta conta) {
         PreparedStatement ps = null;
        int rs;		
        Conexao conexaoBanco = null;
        try {
             conexaoBanco = new Conexao();
            StringBuilder comando = new StringBuilder();
            comando.append("INSERT INTO contas(");
            comando.append("id_titular, ");
            comando.append("vlr_saldo, ");
            comando.append("flg_tipo_conta, ");
            comando.append("senha ) ");
            comando.append("VALUES (?,?,?,?)");
            
            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());

            ps.setInt(1, conta.getId_titular());
            ps.setBigDecimal(2, conta.getVlr_saldo());
            ps.setInt(3, conta.getFlg_tipo_conta());
            ps.setString(4, conta.getSenha());
            
            rs = ps.executeUpdate();
            
            return rs; 
            
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

    public BigDecimal getSaldo(Integer num_conta) {
        PreparedStatement ps = null;
        ResultSet rs;
        BigDecimal vlr_saldo;
        Conexao conexao = null;

        try {
            conexao = new Conexao();

            StringBuilder comando = new StringBuilder();
            comando.append("SELECT vlr_saldo FROM contas ");
            comando.append("WHERE  num_conta = ? ");
            
            ps = conexao.getConexao().prepareStatement(comando.toString());
            ps.setInt(1, num_conta);
            rs = ps.executeQuery();

            if (rs.next()) {

                vlr_saldo = rs.getBigDecimal("vlr_saldo");

                return vlr_saldo;

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

    public Integer gravarTransacao(Transacao transacao) {
        PreparedStatement ps = null;
        int rs;		
        Conexao conexaoBanco = null;
        try {
            conexaoBanco = new Conexao();
            StringBuilder comando = new StringBuilder();
            comando.append("INSERT INTO transacoes(");
            comando.append("dt_transacao, ");
            comando.append("flg_tipo_transacao, ");
            comando.append("num_conta_tr, ");
            comando.append("num_conta_dest, ");
            comando.append("vlr_transacao, ");
            comando.append("flg_status_tr ) ");
            comando.append("VALUES (?,?,?,?,?,?)");
            
            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());

            ps.setTimestamp(1, transacao.getDt_transacao());
            ps.setString(2, transacao.getFlg_tipo_transacao());
            ps.setInt(3, transacao.getNum_conta_tr());
            ps.setInt(4, transacao.getNum_conta_dest());
            ps.setBigDecimal(5, transacao.getVlr_transacao());
            ps.setString(6, transacao.getFlg_status_tr());
                        
            rs = ps.executeUpdate();
            
            return rs; 
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

    public List<Transacao> getTransacoes(Integer num_conta) {
        PreparedStatement ps = null;
        ResultSet rs;
        Conta conta;
        Conexao conexao = null;

        try {
            conexao = new Conexao();

            StringBuilder comando = new StringBuilder();
            comando.append("SELECT num_transacao, dt_transacao, flg_tipo_transacao, ");
            comando.append("num_conta_tr,  num_conta_dest, vlr_transacao, ");
            comando.append("flg_status_tr ");
            comando.append("FROM transacoes WHERE num_conta_tr= ? ");
            
            ps = conexao.getConexao().prepareStatement(comando.toString());
            
            ps.setInt(1, num_conta);
            rs = ps.executeQuery();
            List<Transacao> lista = new ArrayList<Transacao>();
            
            while (rs.next()) {
                Transacao tr = new Transacao(rs.getInt("num_transacao"),
                rs.getString("dt_transacao"), rs.getString("flg_tipo_transacao"),
                rs.getInt("num_conta_tr"), rs.getInt("num_conta_dest"), 
                rs.getBigDecimal("vlr_transacao"), rs.getString("flg_status_tr"));

                lista.add(tr);
            } 
            
            if(lista.isEmpty())
                return null;
            else
                return lista;
            
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

    @Override
    public boolean saque(Integer num_conta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deposito(Integer num_conta, BigDecimal vlr_deposito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
