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

public class TelaAcesso extends JFrame implements ITelas {
    
    private JButton botaoAcessar, botaoListar, botaoAlterar, botaoExcluir, botaoRelatorios, botaoVoltar;
    private JLabel titulo;

    public TelaAcesso() {
        this.inicializarComponentes();
    }
    
    @Override
    public void inicializarComponentes() {
        
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GerenciadorBotoesAcesso btGerenciador = new GerenciadorBotoesAcesso();
        GridBagConstraints c = new GridBagConstraints();
        Dimension tamanhoBotoes = new Dimension(140, 25);
        
        this.titulo = new JLabel("Menu de Acessos");
        this.botaoAcessar = new JButton("Acessar Local");
        this.botaoListar = new JButton("Listar");
        this.botaoAlterar = new JButton("Alterar");
        this.botaoExcluir = new JButton("Excluir");
        this.botaoRelatorios =  new JButton("Relatorios");
        this.botaoVoltar = new JButton("Voltar");
        
        this.botaoAcessar.setPreferredSize(tamanhoBotoes);
        this.botaoListar.setPreferredSize(tamanhoBotoes);
        this.botaoAlterar.setPreferredSize(tamanhoBotoes);
        this.botaoExcluir.setPreferredSize(tamanhoBotoes);
        this.botaoRelatorios.setPreferredSize(tamanhoBotoes);
        this.botaoVoltar.setPreferredSize(tamanhoBotoes);
        
        this.botaoAcessar.addActionListener(btGerenciador);
        this.botaoListar.addActionListener(btGerenciador);
        this.botaoAlterar.addActionListener(btGerenciador);
        this.botaoExcluir.addActionListener(btGerenciador);
        this.botaoRelatorios.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        container.add(this.titulo, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.botaoAcessar, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(this.botaoListar, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(this.botaoAlterar, c);
        
        c.gridx = 0;
        c.gridy = 4;   
        container.add(this.botaoExcluir, c);
        
        c.gridx = 0;
        c.gridy = 5;   
        container.add(this.botaoRelatorios, c);
        
        c.gridx = 0;
        c.gridy = 6;   
        container.add(this.botaoVoltar, c);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void abrirTela(){
        setVisible(true); 
    }
    
    @Override
    public void fecharTela(){
        setVisible(false);
    }
    
    private class GerenciadorBotoesAcesso implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoAcessar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcessoRealizar().abrirTela();
            }
            if (ae.getSource() == botaoListar) {
                CtrlTelas.getInstancia().mostrarTelaAcessoListagem().preencherTabela();
                CtrlTelas.getInstancia().mostrarTelaAcessoListagem().abrirTela();
            }
            if (ae.getSource() == botaoAlterar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcessoAlteracao().atualizarItensComboBox();
                CtrlTelas.getInstancia().mostrarTelaAcessoAlteracao().abrirTela();
            }
            if (ae.getSource() == botaoExcluir) {
                CtrlTelas.getInstancia().mostrarTelaAcessoExclusao().atualizarItensComboBox();
                CtrlTelas.getInstancia().mostrarTelaAcessoExclusao().abrirTela();
            }
            if (ae.getSource() == botaoRelatorios) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcessoRelatorios().abrirTela();
            }           
            if (ae.getSource() == botaoVoltar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaPrincipal().abrirTela();
            }
        }
    }
}
