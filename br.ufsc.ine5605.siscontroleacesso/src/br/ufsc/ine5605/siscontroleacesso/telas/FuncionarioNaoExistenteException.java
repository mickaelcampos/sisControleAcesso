package br.ufsc.ine5605.siscontroleacesso.telas;

public class FuncionarioNaoExistenteException extends Exception {
    
    public FuncionarioNaoExistenteException() {
        super("Este funcionario nao foi cadastrado!");
    }
}
