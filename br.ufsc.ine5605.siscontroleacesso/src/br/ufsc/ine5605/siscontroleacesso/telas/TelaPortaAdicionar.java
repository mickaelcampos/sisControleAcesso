package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlPorta;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import br.ufsc.ine5605.siscontroleacesso.persistencia.PortaDAO;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class TelaPortaAdicionar extends JFrame implements ITelas {
    
    private Porta PortaAdicionar;
    private JLabel tituloCodigo,tituloLocal, tituloNivelAcesso;
    private JTextField textoCodigo, textoLocal, textoNivelAcesso;
    private JButton botaoSalvar, botaoVoltar;


    public TelaPortaAdicionar() {
        super("Tela de Adicao dos Dados da Porta");
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
        GerenciadorBotoesTelaAlteracaoDados btGerenciador = new GerenciadorBotoesTelaAlteracaoDados();
        Dimension tamanhoCampoTextos = new Dimension(180, 25);

        this.tituloCodigo = new JLabel();
        this.tituloLocal = new JLabel();
        this.tituloNivelAcesso = new JLabel();

        this.textoCodigo = new JTextField();
        this.textoLocal = new JTextField();
        this.textoNivelAcesso = new JTextField();

        this.botaoSalvar = new JButton();
        this.botaoSalvar.addActionListener(btGerenciador);

        this.botaoVoltar = new JButton();
        this.botaoVoltar.addActionListener(btGerenciador);

        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.tituloLocal.setText("Codigo da Porta: ");
        container.add(this.tituloLocal, c);

        c.gridx = 1;
        c.gridy = 0;
        this.textoLocal.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoLocal, c);


        c.gridx = 0;
        c.gridy = 1;
        this.tituloCodigo.setText("Local da Porta: ");
        container.add(this.tituloCodigo, c);

        c.gridx = 1;
        c.gridy = 1;
        this.textoCodigo.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoCodigo, c);

        
        c.gridx = 0;
        c.gridy = 2;
        this.tituloNivelAcesso.setText("Nivel de Acesso: ");
        container.add(this.tituloNivelAcesso, c);

        c.gridx = 1;
        c.gridy = 2;
        this.textoNivelAcesso.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoNivelAcesso, c);

        
        c.gridx = 0;
        c.gridy = 3;
        this.botaoSalvar.setText("Salvar");
        container.add(this.botaoSalvar, c); 
        
        c.gridx = 1;
        c.gridy = 3;
        this.botaoVoltar.setText("Voltar");
        container.add(this.botaoVoltar, c);          

        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private class GerenciadorBotoesTelaAlteracaoDados implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoSalvar) {

                CtrlPorta.getInstancia().addPorta(textoCodigo.getText(), textoLocal.getText(), 
                    Integer.parseInt(textoNivelAcesso.getText()));
                setVisible(false);
            }

            if (ae.getSource() ==  botaoVoltar) {
            setVisible(false);
            CtrlTelas.getInstancia().mostrarTelaPorta().setVisible(true);
            }
        }
    }  
}
