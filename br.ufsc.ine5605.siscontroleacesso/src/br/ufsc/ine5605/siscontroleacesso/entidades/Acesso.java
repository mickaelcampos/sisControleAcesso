package br.ufsc.ine5605.siscontroleacesso.entidades;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Classe para objetos do tipo Acesso, onde serao contidos metodos para
 * retornar e alterar atributos de objetos do tipo Acesso.
 */
public class Acesso implements Serializable {

    private String id;
    private Date data;
    private String dataFormatada, tipoAcesso;
    private ICredenciavel ifuncionario, iporta;
    
    /** 
     * Inicia a classe Acesso
     * @param cpf - cpf do Funcionario que solicitou/realizou Acesso
     * @param codigo - codigo da Porta que foi acessada
     * @param autorizado - Se true, autorizado. Se false, negado.
     */
    public Acesso(Funcionario f, Porta p, boolean autorizado) {   
       this.id = gerarID();
       ICredenciavel funcionario = new Funcionario(f.getCPF(),f.getNome(),f.getCargo(),f.getMatricula(), f.getNivelAcesso().getNivel());
       this.ifuncionario = funcionario;
       
       ICredenciavel porta = new Porta(p.getCodigo(), p.getLocal(), p.getNivelAcesso().getNivel());
       this.iporta = porta;
       
       this.setTipoAcesso(autorizado);
       
       this.data = new Date();
       DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
       this.dataFormatada = formataData.format(data);   
    }
    
    /**
     * Metodo para gerar uma id hexadecimal
     * @return id com 8 caracteres selecionados randomicamente entre os disponiveis no array
     */
    private String gerarID() {
        Random rand = new Random();
        String idGerada = "";
        int tamanhoID = 8; //SETA O TAMANHO DA ID
        String[] caracteres = new String [] {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        for (int i = 0; i < tamanhoID; i++) {
            idGerada += caracteres[rand.nextInt(14)];
        }
        return idGerada;
    }
    
    /**
     * Metodo para retorno do codigo da Porta que foi acessada
     * @return int - codigoDaPorta que foi acessada
     */
    public Porta getPorta() {
        return (Porta) this.iporta;
    }
    
    /**
     * Metodo para o retorno do cpf do Funcionario que solicitou/realizou o Acesso
     * @return String - cpfDoFuncionario que solicitou/realizou o Acesso
     */
    public Funcionario getFuncionario() {
        return (Funcionario) this.ifuncionario;
    }
    
    /**
     * Metodo para retorno do tipo de acesso
     * @return String - tipoAcesso do Acesso
     */
    public String getTipoAcesso() {
        return this.tipoAcesso;
    }
    
    /**
     * Metodo para retorno da data/hora do acesso no formato dd/MM/yyyy hh:mm:ss 
     * @return String - dataFormatada do Acesso
     */
    public String getDataFormatada() {
        return this.dataFormatada;
    }
    
    /**
     * Metodo para retorno da data sem formatacao do Acesso
     * @return Date - data do Acesso sem formatacao
     */
    public Date getData() {
        return this.data;
    }
    
     /**
     * Metodo para alterar o tipo de acesso registrado no Acesso
     * @param autorizado - Se true, autorizado. Se false, negado.
     */
    public void setTipoAcesso(boolean tipoAcesso) {
        if (tipoAcesso) {
           this.tipoAcesso = "Autorizado";
       } else
           this.tipoAcesso = "Negado";
    }
    
     /**
     * Metodo para alterar a data/hora registrado no Acesso
     * registrando a data/hora da alteracao
     */
    public void setDataFormatada() {
        this.data = new Date();
        DateFormat formataData = new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
        this.dataFormatada = formataData.format(data);
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }
}
