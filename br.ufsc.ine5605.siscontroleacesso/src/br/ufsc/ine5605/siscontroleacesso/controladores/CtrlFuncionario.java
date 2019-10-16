package br.ufsc.ine5605.siscontroleacesso.controladores;

import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.persistencia.FuncionarioDAO;

/**
 * Classe para objetos do tipo CtrlFuncionario, onde serao contidos metodos para
 * controlar objetos do tipo Funcionario armazenados em uma lista.
 */
public class CtrlFuncionario {
    
    private static CtrlFuncionario instancia;
    /**
     * Inicia a classe CtrlFuncionario
     */
    private CtrlFuncionario() {
    }
    
    public static CtrlFuncionario getInstancia() {
        if (instancia == null) {
            instancia = new CtrlFuncionario();
        }
        return instancia;
    }
    
    /**
     * Permite a inclusao de um novo Funcionario na lista de funcionarios
     * @param cpf - cpf do Funcionario
     * @param nome - nome do Funcionario
     * @param cargo - cargo do Funcionario
     * @param matricula - matricula do Funcionario
     * @param nivelDeAcesso - nivel de acesso do Funcionario para acessar as portas
     */    
    public void addFuncionario(String cpf, String nome, String cargo, int matricula, int nivelDeAcesso) {
        if (getFuncionarioByCPF(cpf) == null)
            FuncionarioDAO.getInstancia().put(new Funcionario(cpf, nome, cargo, matricula, nivelDeAcesso));
    }
   
    /**
     * Permite a exclusao de um Funcionario da lista de funcionarios
     * @param cpf - cpf do Funcionario a ser excluido
     */    
    public void delFuncionarioByCPF(String cpf) {
        if (getFuncionarioByCPF(cpf) != null) {
            FuncionarioDAO.getInstancia().remove(getFuncionarioByCPF(cpf));
        }
    }
    
    /**
     * Permite alterar informacoes de um Funcionario da lista de funcionarios
     * @param cpf - cpf do Funcionario que vai ser alterado
     * @param novoNome - a ser inserido no Funcionario
     * @param novoCargo - a ser inserido no Funcionario
     * @param novaMatricula - a ser inserido no Funcionario
     * @param novoNivelDeAcesso - a ser inserido no Funcionario
     */    
    public void alterarFuncionarioByCPF(String cpf, String novoNome, String novoCargo, int novaMatricula, int novoNivelDeAcesso) {
        if (getFuncionarioByCPF(cpf) != null) {
            Funcionario f = getFuncionarioByCPF(cpf);
            f.setNome(novoNome);
            f.setCargo(novoCargo);
            f.setMatricula(novaMatricula);
            f.setNivelAcesso(novoNivelDeAcesso);
            delFuncionarioByCPF(cpf);
            addFuncionario(f.getCPF(), f.getNome(), f.getCargo(),f.getMatricula(), f.getNivelAcesso().getNivel());
                    
        }
    }
    
    /**
     * Permite listar as informacoes de todos os Funcionarios da lista de funcionarios
     */    
    public void listarFuncionarios() {
        System.out.println("CPF              NOME                  CARGO            MATRICULA  NIVEL DE ACESSO");
        for(Funcionario f : FuncionarioDAO.getInstancia().getList())
            System.out.println(f.getCPF() + "   |  " + f.getNome() + "   |   " + f.getCargo() + "   |   " +
                               f.getMatricula() + "   |   " + f.getNivelAcesso());
    }
    
    /**
     * Permite encontrar um Funcionario
     * @param cpf - cpf do Funcionario que deseja encontrar
     * @return Funcionario
     */    
    public Funcionario getFuncionarioByCPF(String cpf) {
        Funcionario saida = null;
        for (Funcionario f : FuncionarioDAO.getInstancia().getList())
            if (f.getCPF().equals(cpf))
                saida = f;
        return saida;
    }
} 