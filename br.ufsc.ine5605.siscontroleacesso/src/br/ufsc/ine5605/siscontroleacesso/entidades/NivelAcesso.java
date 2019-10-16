package br.ufsc.ine5605.siscontroleacesso.entidades;


public enum NivelAcesso {
    
    BLOQUEADO(0),
    BAIXO(1),
    MEDIO(2),
    ALTO(3);
    
    private int nivel;
    
    private NivelAcesso(int nivel) {
        this.nivel = nivel;
    }
    
    public int getNivel() {
        return this.nivel;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }   
}
