package br.com.sd1.nerys_bank;

import br.com.sd1.nerys_bank.Modelo.Cliente;
import br.com.sd1.nerys_bank.Modelo.Conta;
import br.com.sd1.nerys_bank.Modelo.Transacao;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
            ps.setString(2, cliente.getNum_cpf());
            ps.setString(3, cliente.getLogradouro());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getUf());

            rs = ps.executeUpdate();
            
            cliente.setId_client(rs);

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

            Timestamp dataTransacao = Timestamp.valueOf(transacao.getDt_transacao().
                    atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());

            ps.setTimestamp(1, dataTransacao);
            ps.setInt(2, (transacao.getFlg_tipo_transacao()));
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

                Transacao tr = new Transacao();

                tr.setNum_transacao(rs.getInt("num_transacao"));
                tr.setDt_transacao(OffsetDateTime.of(convertStringToLocalDateTime(rs.getString("dt_transacao")), ZoneOffset.UTC));
                tr.setFlg_status_tr(rs.getString("flg_status_tr"));
                tr.setFlg_tipo_transacao(rs.getInt("flg_tipo_transacao"));
                tr.setNum_conta_tr(rs.getInt("num_conta_tr"));
                tr.setNum_conta_dest(rs.getInt("num_conta_dest"));
                tr.setVlr_transacao(rs.getBigDecimal("vlr_transacao"));

                lista.add(tr);
            }

            if (lista.isEmpty()) {
                return null;
            } else {
                return lista;
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

    private LocalDateTime convertStringToLocalDateTime(String date) {
        String str = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public boolean saque(Integer num_conta, BigDecimal vlr_saque) {
        BigDecimal vlr_saldo;

        Conexao conexao = null;

        try {
            conexao = new Conexao();

            vlr_saldo = getSaldo(num_conta);

            if (vlr_saldo.compareTo(vlr_saque) == 1) {

                //INICIA O REGISTRO DA TRANSAÇÃO 
                Transacao novaTransacao = new Transacao();

                novaTransacao.setDt_transacao(OffsetDateTime.now());
                novaTransacao.setNum_conta_tr(num_conta);
                novaTransacao.setFlg_status_tr("Em Aberto");
                novaTransacao.setVlr_transacao(vlr_saque);
                novaTransacao.setFlg_tipo_transacao(1);

                gravarTransacao(novaTransacao);

                //CONCLUIR O REGISTRO DA TRANSAÇÃO
                if (atualizarTransacao(novaTransacao)) {
                    return gravarDebito(num_conta, vlr_saque);
                }
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Transacao deposito(Integer num_conta, BigDecimal vlr_deposito) {

        try {

            StringBuilder comando = new StringBuilder();

            //INICIA O REGISTRO DA TRANSAÇÃO 
            Transacao novaTransacao = new Transacao();

            novaTransacao.setDt_transacao(OffsetDateTime.now());
            novaTransacao.setNum_conta_tr(num_conta);
            novaTransacao.setFlg_status_tr("Em Aberto");
            novaTransacao.setVlr_transacao(vlr_deposito);
            novaTransacao.setFlg_tipo_transacao(2);

            gravarTransacao(novaTransacao);

            //CONCLUIR O REGISTRO DA TRANSAÇÃO
            if (atualizarTransacao(novaTransacao)) {
                if (gravarCredito(num_conta, vlr_deposito)) {
                    return novaTransacao;
                }
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Transacao transferencia(Integer num_conta_tr, Integer num_conta_dest, BigDecimal vlr_transferencia) {

        try {

            if (getSaldo(num_conta_tr).compareTo(vlr_transferencia) == 1) {

                //INICIA O REGISTRO DA TRANSAÇÃO 
                Transacao novaTransacao = new Transacao();

                novaTransacao.setDt_transacao(OffsetDateTime.now());
                novaTransacao.setNum_conta_tr(num_conta_tr);
                novaTransacao.setNum_conta_dest(num_conta_dest);
                novaTransacao.setFlg_status_tr("Em Aberto");
                novaTransacao.setVlr_transacao(vlr_transferencia);
                novaTransacao.setFlg_tipo_transacao(3);

                gravarTransacao(novaTransacao);

                //CONCLUIR O REGISTRO DA TRANSAÇÃO
                if (atualizarTransacao(novaTransacao)) {
                    gravarDebito(num_conta_tr, vlr_transferencia);
                    if(gravarCredito(num_conta_dest, vlr_transferencia))
                        return novaTransacao;
                    
                }
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
    }

    private boolean atualizarTransacao(Transacao transacao) {
        PreparedStatement ps = null;
        int rs;
        Conexao conexaoBanco = null;
        try {
            conexaoBanco = new Conexao();
            StringBuilder comando = new StringBuilder();

            comando.append("UPDATE transacoes SET flg_status_tr = ? WHERE num_transacao = ?  ");

            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());

            ps.setString(1, "Concluida");
            ps.setInt(2, transacao.getNum_transacao());

            rs = ps.executeUpdate();

            return rs > 0;

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

    private boolean gravarDebito(Integer num_conta, BigDecimal vlr_debito) {
        PreparedStatement ps = null;
        int rs;
        Conexao conexaoBanco = null;
        try {
            conexaoBanco = new Conexao();
            StringBuilder comando = new StringBuilder();

            comando.append("UPDATE contas SET vlr_saldo = ? WHERE num_conta = ?  ");

            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());

            ps.setBigDecimal(1, getSaldo(num_conta).subtract(vlr_debito));
            ps.setInt(2, num_conta);

            rs = ps.executeUpdate();

            return rs > 0;

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

    private boolean gravarCredito(Integer num_conta, BigDecimal vlr_credito) {
        PreparedStatement ps = null;
        int rs;
        Conexao conexaoBanco = null;
        try {
            conexaoBanco = new Conexao();
            StringBuilder comando = new StringBuilder();

            comando.append("UPDATE contas SET vlr_saldo = ? WHERE num_conta = ?  ");

            ps = conexaoBanco.getConexao().prepareStatement(comando.toString());

            ps.setBigDecimal(1, getSaldo(num_conta).add(vlr_credito));
            ps.setInt(2, num_conta);

            rs = ps.executeUpdate();

            return rs > 0;

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
}
