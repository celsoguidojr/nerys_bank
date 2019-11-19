/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sd1.nerys_bank.Modelo;

/**
 *
 * @author Felip
 */
public class Cliente {

    public Cliente() {
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNum_cpf() {
        return num_cpf;
    }

    public void setNum_cpf(String num_cpf) {
        this.num_cpf = num_cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    
    private Integer id_client;
    private String nome_cliente;
    private String num_cpf;
    private String logradouro;
    private String cidade;
    private String uf;
    
    
    public Cliente(Integer id_client, String nome_cliente, String num_cpf, String logradouro, String cidade, String uf) {
        this.id_client = id_client;
        this.nome_cliente = nome_cliente;
        this.num_cpf = num_cpf;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
    }
      
}
