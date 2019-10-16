package br.ufsc.ine5605.siscontroleacesso.entidades;

import java.io.Serializable;

/**
 * Classe abstrata para objetos do tipo Pessoa, onde serao contidos metodos para
 * retornar e alterar atributos de objetos que herdam Pessoa.
 */
public abstract class Pessoa implements Serializable {
    
    private final String cpf;
    private String nome; 
    
    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    
    /**
     * Metodo para retorno do CPF 
     * @return String - cpf da Pessoa
     */
    public String getCPF() {
        return this.cpf;
    }
    
    /**
     * Metodo para retorno do nome
     * @return String - nome da Pessoa
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo para alterar o nome da Pessoa
     * @param nome - nome da Pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}    
