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

    
    private Integer id_client;
    private String nome_cliente;
    private Integer num_cpf;
    private String logradouro;
    private String cidade;
    private String uf;
    
    
    public Cliente(Integer id_client, String nome_cliente, Integer num_cpf, String logradouro, String cidade, String uf) {
        this.id_client = id_client;
        this.nome_cliente = nome_cliente;
        this.num_cpf = num_cpf;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
    }
      
}
