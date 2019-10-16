package br.ufsc.ine5605.siscontroleacesso.controladores;

import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import br.ufsc.ine5605.siscontroleacesso.persistencia.PortaDAO;

/**
 * Classe para objetos do tipo CtrlPorta, onde serao contidos metodos para
 * controlar objetos do tipo Porta armazenados em uma lista.
 */
public class CtrlPorta {
    
    private static CtrlPorta instancia;
    
    /**
     * Inicia a classe CtrlPorta
     */
    private CtrlPorta() {

    }
    
    public static CtrlPorta getInstancia() {
        if (instancia == null) {
            instancia = new CtrlPorta();
        }
        return instancia;
    }
    
    
    /**
    * Permite a inclusao de uma nova Porta na lista de portas
    * @param local - local da Porta
    * @param codigo - codigo da Porta
    * @param nivel - nivel de acesso necessario para acessar a Porta
    */    
    public void addPorta(String codigo, String local, int nivel) {
        if (getPortaByCodigo(codigo) == null)
            PortaDAO.getInstancia().put(new Porta(codigo, local, nivel));
    }
    /**
    * Permite a exclusao de uma Porta da lista de portas
    * @param codigo - codigo da Porta a ser excluida
    */    
    public void delPortaByCodigo(String codigo) {
        if (getPortaByCodigo(codigo) != null)
            PortaDAO.getInstancia().remove(getPortaByCodigo(codigo)); 
    }
    
    /**
    * Permite alterar informacoes de uma Porta da lista de portas
    * @param codigo - codigo da Porta que vai ser alterada
    * @param novoLocal - novo local a ser inserido na Porta
    * @param novoNivel - novo nivel de acesso a ser inserido na Porta
    */
    public void alterarPortaByCodigo(String codigo, String novoLocal, int novoNivel) {
        if (getPortaByCodigo(codigo) != null) {
            Porta p = getPortaByCodigo(codigo);
            p.setLocal(novoLocal);
            p.setNivelAcesso(novoNivel);
            delPortaByCodigo(codigo);
            addPorta(p.getCodigo(), p.getLocal(), p.getNivelAcesso().getNivel());
        }
    }
    
    /**
    * Permite listar as informacoes de todas as Portas da lista de portas 
    */    
    public void listarPortas() {
        System.out.println("LOCAL                      CODIGO              NIVEL DE ACESSO");
        for(Porta porta : PortaDAO.getInstancia().getList())
            System.out.println(porta.getLocal() + "              " + porta.getCodigo() + "                " + porta.getNivelAcesso());
    }
    
    /**
     * Permite encontrar uma Porta
     * @param codigo - codigo da Porta que deseja encontrar
     * @return Porta
     */ 
    public Porta getPortaByCodigo(String codigo) {
        Porta saida = null;
        for (Porta p : PortaDAO.getInstancia().getList())
            if (p.getCodigo().equals(codigo) )
                saida = p;
        return saida;
    }
}
