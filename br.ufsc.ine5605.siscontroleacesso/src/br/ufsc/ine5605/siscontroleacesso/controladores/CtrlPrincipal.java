package br.ufsc.ine5605.siscontroleacesso.controladores;


/**
 * Classe para objetos do tipo CtrlPrincipal, onde serao contidos metodos para
 * controlar objetos do tipo CtrlFuncionario, CtrlPorta, CtrlAcesso e CtrlTelas.
 */
public class CtrlPrincipal {
    
    private static CtrlPrincipal instancia;
    private final CtrlFuncionario ctrlFuncionario = CtrlFuncionario.getInstancia();
    private final CtrlAcesso ctrlAcesso = CtrlAcesso.getInstancia();
    private final CtrlPorta ctrlPorta = CtrlPorta.getInstancia();
    private final CtrlTelas ctrlTelas = CtrlTelas.getInstancia();
    

    private CtrlPrincipal() {
    }
    
    public static CtrlPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new CtrlPrincipal();
        }
        return instancia;
    }
    
    /**
     * Metodo para retorno da instancia da classe CtrlFuncionario
     * @return CtrlTelas - ctrlTelas
     */    
    public CtrlFuncionario getCtrlF() {
        return this.ctrlFuncionario;
    }

    /**
     * Metodo para retorno da instancia da classe CtrlPorta
     * @return CtrlTelas - ctrlTelas
     */    
    public CtrlPorta getCtrlP() {
        return this.ctrlPorta;
    }

    /**
     * Metodo para retorno da instancia da classe CtrlAcesso
     * @return CtrlTelas - ctrlTelas
     */
    public CtrlAcesso getCtrlA() {
        return this.ctrlAcesso;
    }
    
    /**
     * Metodo para retorno da instancia da classe CtrlTelas
     * @return CtrlTelas - ctrlTelas
     */
    public CtrlTelas getCtrlTelas() {
        return this.ctrlTelas;
    }
}
