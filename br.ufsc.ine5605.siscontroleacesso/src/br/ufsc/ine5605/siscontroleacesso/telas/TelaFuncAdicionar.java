package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlFuncionario;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.persistencia.FuncionarioDAO;
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

public class TelaFuncAdicionar extends JFrame implements ITelas {
    
    private Funcionario funcionarioAdicionar;
    private JLabel tituloCPF, tituloNome, tituloCargo, tituloNivelAcesso, tituloMatricula;
    private JTextField textoCPF, textoNome, textoCargo, textoNivelAcesso, textoMatricula;
    private JButton botaoSalvar, botaoVoltar;


    public TelaFuncAdicionar() {
        super("Tela de Adicao dos Dados do Funcionario");
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

        this.tituloCPF = new JLabel();
        this.tituloNome = new JLabel();
        this.tituloCargo = new JLabel();
        this.tituloMatricula = new JLabel();
        this.tituloNivelAcesso = new JLabel();

        this.textoCPF = new JTextField();
        this.textoNome = new JTextField();
        this.textoCargo = new JTextField();
        this.textoMatricula = new JTextField();
        this.textoNivelAcesso = new JTextField();

        this.botaoSalvar = new JButton();
        this.botaoSalvar.addActionListener(btGerenciador);

        this.botaoVoltar = new JButton();
        this.botaoVoltar.addActionListener(btGerenciador);

        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.tituloCPF.setText("CPF do Funcionario: ");
        container.add(this.tituloCPF, c);
        c.gridx = 1;
        c.gridy = 0;
        this.textoCPF.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoCPF, c);


        c.gridx = 0;
        c.gridy = 1;
        this.tituloNome.setText("Nome do Funcionario: ");
        container.add(this.tituloNome, c);


        c.gridx = 1;
        c.gridy = 1;
        this.textoNome.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoNome, c);

        
        c.gridx = 0;
        c.gridy = 2;
        this.tituloCargo.setText("Cargo do Funcionario: ");
        container.add(this.tituloCargo, c);

        c.gridx = 1;
        c.gridy = 2;
        this.textoCargo.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoCargo, c);

        c.gridx = 0;
        c.gridy = 3;
        this.tituloMatricula.setText("Matricula do Funcionario: ");
        container.add(this.tituloMatricula, c);

        c.gridx = 1;
        c.gridy = 3;
        this.textoMatricula.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoMatricula, c);            

        c.gridx = 0;
        c.gridy = 4;
        this.tituloNivelAcesso.setText("Nivel do Funcionario: ");
        container.add(this.tituloNivelAcesso, c);

        c.gridx = 1;
        c.gridy = 4;
        this.textoNivelAcesso.setPreferredSize(tamanhoCampoTextos);
        container.add(this.textoNivelAcesso, c);

        c.gridx = 0;
        c.gridy = 5;
        this.botaoSalvar.setText("Salvar");
        container.add(this.botaoSalvar, c); 

        c.gridx = 0;
        c.gridy = 6;
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

                CtrlFuncionario.getInstancia().addFuncionario(textoCPF.getText(), textoNome.getText(), 
                    textoCargo.getText(), Integer.parseInt(textoMatricula.getText()), Integer.parseInt(textoNivelAcesso.getText()));
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }

            if (ae.getSource() ==  botaoVoltar) {
            setVisible(false);
            CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }
        }
    }  
}
