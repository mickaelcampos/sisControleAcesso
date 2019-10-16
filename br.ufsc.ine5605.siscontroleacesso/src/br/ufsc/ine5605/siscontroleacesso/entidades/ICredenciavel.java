package br.ufsc.ine5605.siscontroleacesso.entidades;

public interface ICredenciavel {
    
    public NivelAcesso getNivelAcesso();
    
    public void setNivelAcesso(int nivel);   
}
