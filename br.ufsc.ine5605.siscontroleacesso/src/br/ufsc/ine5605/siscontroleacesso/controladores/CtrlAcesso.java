package br.ufsc.ine5605.siscontroleacesso.controladores;

import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import br.ufsc.ine5605.siscontroleacesso.persistencia.AcessoDAO;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.entidades.ICredenciavel;
import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import java.util.ArrayList;

/** 
 * Classe para objetos do tipo CtrlAcesso, onde serao contidos metodos para
 * controlar objetos do tipo Acesso armazenados em uma lista.
 */
public class CtrlAcesso implements IRelatorAcessos {
    
    private static CtrlAcesso instancia;
    
    /**
     * Inicia a classe CtrlAcesso
     */
    private CtrlAcesso() {

    }
    
    /**
     * Metodo para retornar a instancia da classe
     * @return 
     */
    public static CtrlAcesso getInstancia() {
        if (instancia == null) {
            instancia = new CtrlAcesso();
        }
        return instancia;
    }
    
    /**
     * Permite a inclusao de uma novo Acesso na lista de acessos
     * @param cpf - cpf do Funcionario que acessou ou tentou acessar uma Porta
     * @param codigo - codigo da Porta que foi acessada por um Funcionario
     * @param autorizado - true para autorizado, false para negado
     */
    public void registrarAcesso(Funcionario funcionario, Porta porta, Boolean autorizado) {
        AcessoDAO.getInstancia().put(new Acesso(funcionario, porta, autorizado));
    }
    
    
    /**
     * Metodo para retornar um objeto Acesso buscando pela id
     * @param id
     * @return 
     */
    private Acesso getAcessoByID(String id) {
        Acesso saida = null;
        for (Acesso acesso : AcessoDAO.getInstancia().getList())
            if (acesso.getID().equals(id))
                saida = acesso;
        return saida;
    }
    /**
     * Metodo para deletar um acesso
     * @param id 
     */
    public void delAcesso(String id) {
        if (getAcessoByID(id) != null) {
            AcessoDAO.getInstancia().remove(getAcessoByID(id));
        }
    }
    
    /**
     * Metodo para alterar os atributos do acesso
     * @param id
     * @param novoNome
     * @param novoCodigo
     * @param novoLocal
     * @param novoTipoAcesso 
     */
    public void alterarAcessoByID(String id, String novoNome, String novoCodigo, String novoLocal, Boolean novoTipoAcesso) {
        if (getAcessoByID(id) != null) {
            Acesso acessoAlterar = getAcessoByID(id);
            acessoAlterar.getFuncionario().setNome(novoNome);
            acessoAlterar.getPorta().setCodigo(novoCodigo);
            acessoAlterar.getPorta().setLocal(novoLocal);
            acessoAlterar.setTipoAcesso(novoTipoAcesso);
            acessoAlterar.setDataFormatada();
            delAcesso(id);
            registrarAcesso(acessoAlterar.getFuncionario(), acessoAlterar.getPorta(), novoTipoAcesso);
        }
    } 
    
    /**
     * Permite listar as informacoes dos acessos de determinado Funcionario
     * @param cpf - cpf do Funcionario que deseja listar os 
     * @return acessosEncontrados - ArrayList contendo os acessos  
     */
    @Override
    public ArrayList<Acesso> listarAcessosByCPF(String cpf) {
        ArrayList<Acesso> acessosEncontrados = new ArrayList<>();

        for (Acesso acesso : AcessoDAO.getInstancia().getList()) {
            if(acesso.getFuncionario().getCPF().equals(cpf)) {
                acessosEncontrados.add(acesso);
            }
        }
        return acessosEncontrados;
    }
    
    /** 
     * Metodo para permitir o Acesso, comparando o nivel de acesso do Funcionario
     * com o nivel necessario da Porta. Caso o Funcionario possuir o cargo de 
     * "DIRETOR", o Acesso eh permitido mesmo seu nivel de acesso sendo insuficiente
     * @param cpf - cpf do Funcionario que deseja acessar uma Porta
     * @param codigo - codigo da Porta que sera acessada
     * @return boolean - Se true, acesso autorizado. Se false, acesso negado por
     * nivel de acesso insuficiente ou por cpf/codigo invalidos
     */
    public boolean solicitarAcesso(String cpf, String codigo) {
        String cargoMax = "DIRETOR";
        Funcionario funcionario = (Funcionario) solicitarICredenciavel(cpf);
        Porta porta = (Porta) solicitarICredenciavel(codigo);
        
        if (funcionario != null && porta != null) {
            if ((funcionario.getCargo().equals(cargoMax)) || 
                    (funcionario.getNivelAcesso().getNivel() >= porta.getNivelAcesso().getNivel())) {
                        acessarLocal(funcionario,porta,true);
                        return true;
            } else {
                registrarAcesso(funcionario, porta, false);
                funcionario.incrementarTentativas();
                return false;
                }
        }
        return false;
    }
    
    /** 
     * Metodo para realizar o Acesso de um Funcionario em uma Porta, registrando
     * o Acesso e alterando o local do Funcionario para o local da Porta
     * @param funcionario - Funcionario que ira realizar o Acesso
     * @param porta  - Porta que sera acessada
     */
    private void acessarLocal(Funcionario funcionario, Porta porta, Boolean tipoDeAcesso) {
        funcionario.setLocalAtual(porta.getLocal());
        registrarAcesso(funcionario, porta, tipoDeAcesso);
    }

    /**
     * Metodo para retornar um funcionario ou porta que sera convertido em ICredencial
     * este metodo eh utilizado no solicitarAcesso() que faz o tratamento caso o retorno seja nulo
     * @param credencial - cpf do funcionario ou codigo da porta
     * @return ICredencial - funcionario ou porta, depende do valor passado por parametro
     * 
     */
    private ICredenciavel solicitarICredenciavel(String credencial) {
        Funcionario f = CtrlFuncionario.getInstancia().getFuncionarioByCPF(credencial);
        Porta p = CtrlPorta.getInstancia().getPortaByCodigo(credencial);
        
        if (p != null) {
            return p;
        }
        
        return f;
    }
}