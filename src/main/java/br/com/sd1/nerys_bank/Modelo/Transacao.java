/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.Modelo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 *
 * @author Felip
 */
public class Transacao {
    
    private Integer num_transacao;
    private OffsetDateTime dt_transacao;
    private char flg_tipo_transacao;
    private Integer num_conta_tr;
    private Integer num_conta_dest;
    private BigDecimal vlr_transacao;
    private char flg_status_tr;

    public Transacao(Integer num_transacao, OffsetDateTime dt_transacao, 
            char flg_tipo_transacao, Integer num_conta_tr, 
            Integer num_conta_dest, BigDecimal vlr_transacao, 
            char flg_status_tr) {
        
        this.num_transacao = num_transacao;
        this.dt_transacao = dt_transacao;
        this.flg_tipo_transacao = flg_tipo_transacao;
        this.num_conta_tr = num_conta_tr;
        this.num_conta_dest = num_conta_dest;
        this.vlr_transacao = vlr_transacao;
        this.flg_status_tr = flg_status_tr;
    }
    
    public Integer getNum_transacao() {
        return num_transacao;
    }

    public void setNum_transacao(Integer num_transacao) {
        this.num_transacao = num_transacao;
    }

    public OffsetDateTime getDt_transacao() {
        return dt_transacao;
    }

    public void setDt_transacao(OffsetDateTime dt_transacao) {
        this.dt_transacao = dt_transacao;
    }

    public char getFlg_tipo_transacao() {
        return flg_tipo_transacao;
    }

    public void setFlg_tipo_transacao(char flg_tipo_transacao) {
        this.flg_tipo_transacao = flg_tipo_transacao;
    }

    public Integer getNum_conta_tr() {
        return num_conta_tr;
    }

    public void setNum_conta_tr(Integer num_conta_tr) {
        this.num_conta_tr = num_conta_tr;
    }

    public Integer getNum_conta_dest() {
        return num_conta_dest;
    }

    public void setNum_conta_dest(Integer num_conta_dest) {
        this.num_conta_dest = num_conta_dest;
    }

    public BigDecimal getVlr_transacao() {
        return vlr_transacao;
    }

    public void setVlr_transacao(BigDecimal vlr_transacao) {
        this.vlr_transacao = vlr_transacao;
    }

    public char getFlg_status_tr() {
        return flg_status_tr;
    }

    public void setFlg_status_tr(char flg_status_tr) {
        this.flg_status_tr = flg_status_tr;
    }
    
}
