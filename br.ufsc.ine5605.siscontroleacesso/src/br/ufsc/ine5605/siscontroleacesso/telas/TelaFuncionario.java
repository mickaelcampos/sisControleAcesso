package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaFuncionario extends JFrame implements ITelas {
    
    private JButton botaoAdicionar, botaoDeletar, botaoAlterar, botaoListar, botaoVoltar;
    private JLabel titulo;

    public TelaFuncionario() {
        this.inicializarComponentes();
    }
    
    @Override
    public void abrirTela(){
        setVisible(true); 
    }
    
    @Override
    public void fecharTela(){
        setVisible(false);
    }
    
    @Override
    public void inicializarComponentes() {
        
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        TelaFuncionario.GerenciadorBotoesFunc btGerenciador = new TelaFuncionario.GerenciadorBotoesFunc();
        GridBagConstraints c = new GridBagConstraints();
        Dimension tamanhoBotoes = new Dimension(140, 25);
        
        this.titulo = new JLabel("Menu de Funcionarios");
        this.botaoAdicionar = new JButton("Adicionar");
        this.botaoDeletar = new JButton("Deletar");
        this.botaoAlterar = new JButton("Alterar");
        this.botaoListar = new JButton("Listar");
        this.botaoVoltar = new JButton("Voltar");
        
        this.botaoAdicionar.setPreferredSize(tamanhoBotoes);
        this.botaoDeletar.setPreferredSize(tamanhoBotoes);
        this.botaoAlterar.setPreferredSize(tamanhoBotoes);
        this.botaoListar.setPreferredSize(tamanhoBotoes);
        this.botaoVoltar.setPreferredSize(tamanhoBotoes);
        
        this.botaoAdicionar.addActionListener(btGerenciador);
        this.botaoDeletar.addActionListener(btGerenciador);
        this.botaoAlterar.addActionListener(btGerenciador);
        this.botaoListar.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        container.add(this.titulo, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.botaoAdicionar, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(this.botaoDeletar, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(this.botaoAlterar, c);
        
        c.gridx = 0;
        c.gridy = 4;
        container.add(this.botaoListar, c);        
        
        c.gridx = 0;
        c.gridy = 5;   
        container.add(this.botaoVoltar, c);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class GerenciadorBotoesFunc implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoAdicionar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFuncAdicionar().setVisible(true);
            }
            if (ae.getSource() == botaoDeletar) {
                
                CtrlTelas.getInstancia().mostrarTelaFuncExclusao().atualizarItensComboBox();
                CtrlTelas.getInstancia().mostrarTelaFuncExclusao().setVisible(true);
            }
            if (ae.getSource() == botaoAlterar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFuncAlteracao().atualizarItensComboBox();
                CtrlTelas.getInstancia().mostrarTelaFuncAlteracao().setVisible(true);
            }
            if (ae.getSource() == botaoListar) {
                
                CtrlTelas.getInstancia().mostrarTelaFuncListagem().preencherTabela();
                CtrlTelas.getInstancia().mostrarTelaFuncListagem().setVisible(true);
            }        
            if (ae.getSource() == botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaPrincipal().setVisible(true);
            }
        }
    }    
}
