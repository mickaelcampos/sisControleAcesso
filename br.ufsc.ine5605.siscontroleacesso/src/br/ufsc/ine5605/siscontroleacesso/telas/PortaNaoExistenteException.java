package br.ufsc.ine5605.siscontroleacesso.telas;

public class PortaNaoExistenteException extends Exception {
    
    public PortaNaoExistenteException() {
        super("Esta porta nao foi cadastrada!");
    }
}
