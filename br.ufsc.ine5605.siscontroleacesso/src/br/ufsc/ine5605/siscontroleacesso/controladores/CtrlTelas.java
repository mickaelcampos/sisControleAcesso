package br.ufsc.ine5605.siscontroleacesso.controladores;

import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcesso;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcessoAlteracao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcessoExclusao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcessoListagem;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcessoRealizar;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaAcessoRelatorios;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaPrincipal;

import br.ufsc.ine5605.siscontroleacesso.telas.TelaFuncionario;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaFuncAdicionar;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaFuncExclusao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaFuncAlteracao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaFuncListagem;

import br.ufsc.ine5605.siscontroleacesso.telas.TelaPorta;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaPortaAdicionar;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaPortaExclusao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaPortaAlteracao;
import br.ufsc.ine5605.siscontroleacesso.telas.TelaPortaListagem;

public class CtrlTelas {
    
    private static CtrlTelas instancia;
    
    private final TelaPrincipal telaPrincipal = new TelaPrincipal();
    private final TelaAcesso telaAcesso = new TelaAcesso();
    private final TelaAcessoListagem telaAcessoListar = new TelaAcessoListagem();
    private final TelaAcessoRealizar telaAcessoAcessar = new TelaAcessoRealizar();
    private final TelaAcessoExclusao telaAcessoExclusao = new TelaAcessoExclusao();
    private final TelaAcessoAlteracao telaAcessoAlteracao = new TelaAcessoAlteracao();
    private final TelaAcessoRelatorios telaAcessosRelatorios =  new TelaAcessoRelatorios();
    
    private final TelaFuncionario telaFuncionario = new TelaFuncionario();
    private final TelaFuncAdicionar telaFuncAdicionar = new TelaFuncAdicionar();
    private final TelaFuncExclusao telaFuncDeletar = new TelaFuncExclusao();
    private final TelaFuncAlteracao telaFuncAlteracao = new TelaFuncAlteracao();
    private final TelaFuncListagem telaFuncListagem = new TelaFuncListagem();
    
    private final TelaPortaAdicionar telaPortaAdicionar = new TelaPortaAdicionar();
    private final TelaPortaExclusao telaPortaDeletar = new TelaPortaExclusao();
    private final TelaPortaAlteracao telaPortaAlteracao = new TelaPortaAlteracao();
    private final TelaPortaListagem telaPortaListagem = new TelaPortaListagem();    
    private final TelaPorta telaPorta = new TelaPorta();   
    
    public CtrlTelas() {
    }
    
    public static CtrlTelas getInstancia() {
        if (instancia == null) {
            instancia = new CtrlTelas();
        }
        return instancia;
    }
    
    public TelaPrincipal mostrarTelaPrincipal() {
        return this.telaPrincipal;
    }
    
    public TelaAcesso mostrarTelaAcesso() {
        return this.telaAcesso;
    }

    public TelaAcessoListagem mostrarTelaAcessoListagem() {
        return this.telaAcessoListar;
    }

    public TelaAcessoRealizar mostrarTelaAcessoRealizar() {
        return this.telaAcessoAcessar;
    }

    public TelaAcessoExclusao mostrarTelaAcessoExclusao() {
        return this.telaAcessoExclusao;
    }

    public TelaAcessoAlteracao mostrarTelaAcessoAlteracao() {
        return this.telaAcessoAlteracao;    
    }

    public TelaAcessoRelatorios mostrarTelaAcessoRelatorios() {
        return this.telaAcessosRelatorios;
    }

    public TelaFuncAdicionar mostrarTelaFuncAdicionar() {
        return this.telaFuncAdicionar;
    }

    public TelaFuncExclusao mostrarTelaFuncExclusao() {
        return this.telaFuncDeletar;
    }

    public TelaFuncAlteracao mostrarTelaFuncAlteracao() {
        return this.telaFuncAlteracao;
    }

    public TelaFuncListagem mostrarTelaFuncListagem() {
        return this.telaFuncListagem;
    }

    public TelaPorta mostrarTelaPorta() {
        return this.telaPorta;
    }

    public TelaFuncionario mostrarTelaFunc() {
        return this.telaFuncionario;
    }

    public TelaPortaAdicionar mostrarTelaPortaAdicionar() {
        return this.telaPortaAdicionar;
    }

    public TelaPortaExclusao mostrarTelaPortaExclusao() {
        return this.telaPortaDeletar;
    }

    public TelaPortaAlteracao mostrarTelaPortaAlteracao() {
        return this.telaPortaAlteracao;
    }

    public TelaPortaListagem mostrarTelaPortaListagem() {
        return this.telaPortaListagem;
    }

}
