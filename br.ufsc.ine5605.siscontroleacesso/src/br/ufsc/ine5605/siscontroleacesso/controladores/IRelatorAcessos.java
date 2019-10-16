package br.ufsc.ine5605.siscontroleacesso.controladores;

import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import java.util.ArrayList;

public interface IRelatorAcessos {
    
    /**
     * Retorna lista com os Acessos feitos pelo Funcionario portador do CPF
     * @param cpf - cpf do Funcionario que deseja encontrar 
     * @return ArrayList com os Acessos encontrados
     */
    public ArrayList<Acesso> listarAcessosByCPF(String cpf);
}
