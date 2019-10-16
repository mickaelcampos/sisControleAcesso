package br.ufsc.ine5605.siscontroleacesso.entidades;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Funcionario, onde serao contidos metodos para
 * retornar e alterar atributos de objetos do tipo Funcionario.
 */
public class Funcionario extends Pessoa implements Serializable, ICredenciavel {
    
    private String cargo, localAtual;
    private int matricula;
    private int nAcessosNegados = 0;
    private NivelAcesso nivelDeAcesso;
    
    /**
     * Inicia a classe Funcionario
     * @param cpf - cpf do Funcionario
     * @param nome - nome do Funcionario
     * @param cargo - cargo do Funcionario
     * @param matricula - matricula do Funcionario
     * @param nivel - nivel de acesso do Funcionario utilizado para entrar nas portas
     */    
    public Funcionario(String cpf, String nome, String cargo, int matricula, int nivel) { 
        super(cpf, nome);
        this.setCargo(cargo);
        this.matricula = matricula;
        this.setNivelAcesso(nivel);
    }
    
    /**
     * Metodo para retorno do cargo do Funcionario
     * @return String - cargo do Funcionario
     */
    public String getCargo() {
        return cargo;
    }
    
    /**
     * Metodo para alterar o cargo do Funcionario
     * @param cargo - cargo a ser inserido
     */
    public void setCargo(String cargo) {
        String strCargo = cargo.toUpperCase();
        this.cargo = strCargo;
    }
    
    /**
     * Metodo para retorno do local do Funcionario
     * @return String - local do Funcionario
     */
    public String getLocalAtual() {
        return localAtual;
    }
    
    /**
     * Metodo para alterar o local do Funcionario
     * @param local - local a ser inserido
     */
    public void setLocalAtual(String local) {
        this.localAtual = local;
    }
    
    /**
     * Metodo para retorno da matricula do Funcionario
     * @return int - matricula do Funcionario
     */
    public int getMatricula() {
        return matricula;
    }
    
    /**
     * Metodo para alterar a matricula do Funcionario
     * @param matricula - matricula a ser inserida
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    /**
     * Metodo para retorno do nivel de acesso do Funcionario
     * @return NivelDeAcesso - nivel de acesso do Funcionario
     */
    @Override
    public NivelAcesso getNivelAcesso() {
        return nivelDeAcesso;
    }
    
    /**
     * Metodo para alterar o nivel de acesso do Funcionario
     * @param nivel - nivel a ser inserido
     */
    @Override
    public void setNivelAcesso(int nivel) {
        if (nivel == NivelAcesso.BAIXO.getNivel())
            this.nivelDeAcesso = NivelAcesso.BAIXO;
        if (nivel == NivelAcesso.MEDIO.getNivel())
            this.nivelDeAcesso = NivelAcesso.MEDIO;
        if (nivel == NivelAcesso.ALTO.getNivel())
            this.nivelDeAcesso = NivelAcesso.ALTO;     
        
        if (nivel != 1 && nivel != 2 && nivel != 3)
            this.nivelDeAcesso = NivelAcesso.BLOQUEADO;
    }
    
    /**
     * Metodo para incrementar o numero de tentativas negadas de um Funcionario
     * Se o numero de tentativas chegar em tres, o funcionario fica bloqueado
     */
    public void incrementarTentativas() {
        this.nAcessosNegados++;
        if (nAcessosNegados == 3)
            setNivelAcesso(0); 
    }  
}