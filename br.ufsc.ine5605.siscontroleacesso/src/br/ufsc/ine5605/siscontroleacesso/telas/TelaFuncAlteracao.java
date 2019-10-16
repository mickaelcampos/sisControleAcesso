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

public class TelaFuncAlteracao extends JFrame implements ITelas {
    private JLabel tituloAlteracao;
    private JButton botaoAlterar, botaoVoltar;
    private JComboBox selecaoFuncionario;
    
    public TelaFuncAlteracao() {
        super("Tela de Alteracao de Funcionarios");
        this.inicializarComponentes();
        this.atualizarItensComboBox();
        
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
        GerenciadorBotoesTelaAlteracao btGerenciador = new GerenciadorBotoesTelaAlteracao();
        
        this.tituloAlteracao = new JLabel();
        this.selecaoFuncionario = new JComboBox();
        this.botaoAlterar = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoAlterar.setText("Alterar");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoAlterar.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloAlteracao.setText("Selecione o Funcionario que deseja Alterar");
        container.add(this.tituloAlteracao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoFuncionario, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(this.botaoAlterar, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(this.botaoVoltar, c);
            
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void atualizarItensComboBox() {
        selecaoFuncionario.removeAllItems();
        for (Funcionario funcionario : FuncionarioDAO.getInstancia().getList()) {
            selecaoFuncionario.addItem(funcionario.getCPF());
        }
    }
             
    private class GerenciadorBotoesTelaAlteracao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoAlterar) {
                
                try {
                    
                    if (selecaoFuncionario.getSelectedItem() == null) {
                        throw new AcessoNaoExistenteException();
                    } 
                    
                    Funcionario funcionarioSelecionado = FuncionarioDAO.getInstancia().getFuncionario(selecaoFuncionario.getSelectedItem().toString());
                    TelaFuncAlteracaoDados telaAlteracaoDados = new TelaFuncAlteracaoDados(funcionarioSelecionado);
                    atualizarItensComboBox();
                    //setVisible(false);
                    telaAlteracaoDados.setVisible(true);
                    
                } catch (AcessoNaoExistenteException e) {
                        System.out.println("exception aqui");
                        JOptionPane.showMessageDialog(null, "Nenhum funcionario alterado!", "Atencao!", JOptionPane.INFORMATION_MESSAGE);

                }
            } 
            
            if (ae.getSource() == botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }
        }
    }
    
    private class TelaFuncAlteracaoDados extends JFrame implements ITelas {

        private Funcionario funcionarioAlterar;
        private JLabel tituloNome, tituloCargo, tituloNivelAcesso;
        private JTextField textoNome, textoCargo, textoNivelAcesso;
        private JButton botaoSalvar, botaoVoltar;


        public TelaFuncAlteracaoDados(Funcionario funcionarioSelecionado) {
            super("Tela de Alteracao dos Dados do Funcionario");
            this.funcionarioAlterar = funcionarioSelecionado;
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

            this.tituloNome = new JLabel();
            this.tituloCargo = new JLabel();
            this.tituloNivelAcesso = new JLabel();

            this.textoNome = new JTextField(funcionarioAlterar.getNome());
            this.textoCargo = new JTextField(funcionarioAlterar.getCargo());
            this.textoNivelAcesso = new JTextField(funcionarioAlterar.getNivelAcesso().getNivel());

            this.botaoSalvar = new JButton();
            this.botaoSalvar.addActionListener(btGerenciador);

            this.botaoVoltar = new JButton();
            this.botaoVoltar.addActionListener(btGerenciador);

            c.insets = new Insets(10, 10, 10, 10);
            c.anchor = GridBagConstraints.WEST;

            c.gridx = 0;
            c.gridy = 0;
            this.tituloNome.setText("Nome do Funcionario: ");
            container.add(this.tituloNome, c);

            c.gridx = 1;
            c.gridy = 0;
            this.textoNome.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoNome, c);


            c.gridx = 0;
            c.gridy = 1;
            this.tituloCargo.setText("Cargo do Funcionario: ");
            container.add(this.tituloCargo, c);
            c.gridx = 1;
            c.gridy = 1;
            this.textoCargo.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoCargo, c);
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
                    
                    CtrlFuncionario.getInstancia().alterarFuncionarioByCPF(funcionarioAlterar.getCPF(), 
                            textoNome.getText(), textoCargo.getText(), funcionarioAlterar.getMatricula(), Integer.parseInt(textoNivelAcesso.getText()));
                    atualizarItensComboBox();
                    setVisible(false);
                }
                
                if (ae.getSource() ==  botaoVoltar) {
                setVisible(false);
                }
            }
        }    
    }   
}
