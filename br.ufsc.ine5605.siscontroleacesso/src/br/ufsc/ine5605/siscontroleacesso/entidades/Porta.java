package br.ufsc.ine5605.siscontroleacesso.entidades;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Funcionario, onde serao contidos metodos para
 * retornar e alterar atributos de objetos do tipo Funcionario.
 */
public class Porta implements Serializable, ICredenciavel {
    
    private String codigo; // deveria ser FINAL, pode dar merda no persist
    private String local;
    private NivelAcesso nivelNecessario;
    
    /**
     * Inicia a classe Porta
     * @param codigo - codigo da Porta
     * @param local - local da Porta
     * @param nivel - nivel de acesso necessario
     */
    public Porta(String codigo, String local, int nivel) {
        this.local = local;
        this.codigo = codigo;
        this.setNivelAcesso(nivel);
    }
    
    /**
     * Metodo para retorno do local da Porta
     * @return String - local da Porta
     */
    public String getLocal() {
        return local;
    }
    
    /**
     * Metodo para alterar o local da Porta
     * @param local - local a ser inserido
     */
    public void setLocal(String local) {
        this.local = local;
    }
    
    /**
     * Metodo para retorno do codigo da Porta
     * @return String - codigo da Porta
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Metodo para alterar o codigo da Porta
     * @param codigo - codigo da Porta
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Metodo para alterar o nivel de acesso necessario da Porta
     * @param nivel - nivel a ser inserido
     */
    @Override
    public void setNivelAcesso(int nivel) {
        if (nivel == NivelAcesso.BAIXO.getNivel())
            this.nivelNecessario = NivelAcesso.BAIXO;
        if (nivel == NivelAcesso.MEDIO.getNivel())
            this.nivelNecessario = NivelAcesso.MEDIO;     
        if (nivel == NivelAcesso.ALTO.getNivel())
            this.nivelNecessario = NivelAcesso.ALTO;    
        if (nivel != 1 && nivel != 2 && nivel != 3)
            this.nivelNecessario = NivelAcesso.BLOQUEADO;
    }
    
    /**
     * Metodo para retorno do nivel de acesso necessario da Porta
     * @return NivelDeAcesso - nivel de acesso necessario da Porta
     */
    @Override
    public NivelAcesso getNivelAcesso() {
        return nivelNecessario;
    }
}
