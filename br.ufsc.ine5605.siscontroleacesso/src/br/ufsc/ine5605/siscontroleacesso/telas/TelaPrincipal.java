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

public class TelaPrincipal extends JFrame implements ITelas {

    private JLabel titulo;
    private JButton botaoAcesso, botaoPortas, botaoFunc, botaoSair;

    public TelaPrincipal() {

        super("Sistema de Controle de Acesso");
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

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesPrincipal btGerenciador = new GerenciadorBotoesPrincipal();
        Dimension tamanhoBotoes = new Dimension(125, 25);
        
        this.titulo = new JLabel();
        this.botaoAcesso = new JButton();
        this.botaoPortas = new JButton();
        this.botaoFunc = new JButton();
        this.botaoSair = new JButton();
        
        this.titulo.setText("Menu Principal");
        this.botaoAcesso.setText("Acessos");
        this.botaoPortas.setText("Portas");
        this.botaoFunc.setText("Funcionarios");
        this.botaoSair.setText("Sair");
        
        this.botaoAcesso.setPreferredSize(tamanhoBotoes);
        this.botaoPortas.setPreferredSize(tamanhoBotoes);
        this.botaoFunc.setPreferredSize(tamanhoBotoes);
        this.botaoSair.setPreferredSize(tamanhoBotoes);
        
        this.botaoAcesso.addActionListener(btGerenciador);
        this.botaoPortas.addActionListener(btGerenciador);
        this.botaoFunc.addActionListener(btGerenciador);
        this.botaoSair.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        container.add(titulo, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(botaoAcesso, c);
                
        c.gridx = 0;
        c.gridy = 2;
        container.add(botaoPortas, c);
                
        c.gridx = 0;
        c.gridy = 3;
        container.add(botaoFunc, c);
        
        c.gridx = 0;
        c.gridy = 4;
        container.add(botaoSair, c);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class GerenciadorBotoesPrincipal implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == botaoAcesso) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaAcesso().setVisible(true);
            }
            else if(ae.getSource() == botaoPortas){
                CtrlTelas.getInstancia().mostrarTelaPorta().setVisible(true);
            }
            else if(ae.getSource() == botaoFunc){
                CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }
            else if(ae.getSource() == botaoSair){
                System.exit(0);
            }
        }
    }
}
