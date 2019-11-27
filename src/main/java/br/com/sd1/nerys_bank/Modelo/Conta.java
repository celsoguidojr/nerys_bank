/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.Modelo;

import java.math.BigDecimal;

public class Conta {

    
	private Integer num_conta;
        private Integer id_titular;
	private BigDecimal vlr_saldo;
        private char flg_tipo_conta;
        private String senha;
        private Integer num_agencia;

        public Integer getNum_agencia() {
			return num_agencia;
		}


		public void setNum_agencia(Integer num_agencia) {
			this.num_agencia = num_agencia;
		}


		public Conta(Integer num_agencia, Integer num_conta, Integer id_titular, BigDecimal vlr_saldo, char flg_tipo_conta, String senha) {
            this.num_agencia = num_agencia;
        	this.num_conta = num_conta;
            this.id_titular = id_titular;
            this.vlr_saldo = vlr_saldo;
            this.flg_tipo_conta = flg_tipo_conta;
            this.senha = senha;
        }


	public Conta() {
		this.vlr_saldo = new BigDecimal(0);
	}

	public Integer getNumConta() {
		return num_conta;
	}
        
        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

	public void setNumConta(Integer id) {
		this.num_conta = id;
	}


	public BigDecimal getVlr_saldo() {
		return vlr_saldo;
	}

	public void setVlr_saldo(BigDecimal vlr_saldo) {
		this.vlr_saldo = vlr_saldo;
	}
        
        public Integer getId_titular() {
            return id_titular;
        }

        public void setId_titular(Integer id_titular) {
            this.id_titular = id_titular;
        }

        public char getFlg_tipo_conta() {
            return flg_tipo_conta;
        }

        public void setFlg_tipo_conta(char flg_tipo_conta) {
            this.flg_tipo_conta = flg_tipo_conta;
        }

	@Override
	public String toString() {
		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append("-------- ");
		strRetorno.append("\nConta: ");
		//strRetorno.append("\nId: "+getId());
		//strRetorno.append("\nNome:"+getNome());
		strRetorno.append("\nSaldo: "+getVlr_saldo().doubleValue());
		strRetorno.append("\n-------- ");
		
		return strRetorno.toString(); 
	}

    

}
