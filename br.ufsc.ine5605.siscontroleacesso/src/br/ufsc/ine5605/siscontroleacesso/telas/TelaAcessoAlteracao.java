package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlAcesso;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import br.ufsc.ine5605.siscontroleacesso.persistencia.AcessoDAO;
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

public class TelaAcessoAlteracao extends JFrame implements ITelas {
    private JLabel tituloAlteracao;
    private JButton botaoAlterar, botaoVoltar;
    private JComboBox selecaoAcesso;
    public TelaAcessoAlteracao() {
        super("Tela de Alteracao de Acessos");
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
        this.selecaoAcesso= new JComboBox();
        this.botaoAlterar = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoAlterar.setText("Alterar");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoAlterar.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloAlteracao.setText("Selecione o Acesso que deseja Alterar");
        container.add(this.tituloAlteracao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoAcesso, c);
        
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
        selecaoAcesso.removeAllItems();
        for (Acesso acesso : AcessoDAO.getInstancia().getList()) {
            selecaoAcesso.addItem(acesso.getID());
        }
    }
             
    private class GerenciadorBotoesTelaAlteracao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoAlterar) {
                
                try {
                    
                    if (selecaoAcesso.getSelectedItem() == null) {
                        throw new AcessoNaoExistenteException();
                    } 
                    
                    Acesso acessoSelecionado = AcessoDAO.getInstancia().get(selecaoAcesso.getSelectedItem().toString());
                    TelaAcessoAlteracaoDados telaAlteracaoDados = new TelaAcessoAlteracaoDados(acessoSelecionado);
                    atualizarItensComboBox();
                    telaAlteracaoDados.abrirTela();
                    
                } catch (AcessoNaoExistenteException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Atencao!", JOptionPane.INFORMATION_MESSAGE);

                }
            } 
            
            if (ae.getSource() == botaoVoltar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcesso().abrirTela();
            }
        }
    }
    
    private class TelaAcessoAlteracaoDados extends JFrame implements ITelas {

        private Acesso acessoAlterar;
        private JLabel tituloNome,tituloCodigo, tituloLocal, tituloTipoAcesso;
        private JTextField textoNome, textoCodigo, textoLocal;
        private JCheckBox selecaoTipoAcesso;
        private JButton botaoSalvar, botaoVoltar;


        public TelaAcessoAlteracaoDados(Acesso acessoSelecionado) {
            super("Tela de Alteracao dos Dados do Acesso");
            this.acessoAlterar = acessoSelecionado;
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
            this.tituloCodigo = new JLabel();
            this.tituloLocal = new JLabel();
            this.tituloTipoAcesso = new JLabel();

            this.textoNome = new JTextField(acessoAlterar.getFuncionario().getNome());
            this.textoCodigo = new JTextField(acessoAlterar.getPorta().getCodigo());
            this.textoLocal = new JTextField(acessoAlterar.getPorta().getLocal());

            this.selecaoTipoAcesso = new JCheckBox("Autorizado", true);

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
            this.tituloCodigo.setText("Codigo da Porta: ");
            container.add(this.tituloCodigo, c);

            c.gridx = 1;
            c.gridy = 1;
            this.textoCodigo.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoCodigo, c);

            c.gridx = 0;
            c.gridy = 2;
            this.tituloLocal.setText("Local da Porta: ");
            container.add(this.tituloLocal, c);

            c.gridx = 1;
            c.gridy = 2;
            this.textoLocal.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoLocal, c);

            c.gridx = 0;
            c.gridy = 3;
            this.tituloTipoAcesso.setText("Tipo de Acesso: ");
            container.add(this.tituloTipoAcesso, c);

            c.gridx = 1;
            c.gridy = 3;
            container.add(this.selecaoTipoAcesso, c);

            c.gridx = 0;
            c.gridy = 4;
            this.botaoSalvar.setText("Salvar");
            container.add(this.botaoSalvar, c); 

            c.gridx = 0;
            c.gridy = 5;
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
                    
                    CtrlAcesso.getInstancia().alterarAcessoByID(acessoAlterar.getID(), 
                            textoNome.getText(), textoCodigo.getText(), textoLocal.getText(), selecaoTipoAcesso.isSelected());
                    atualizarItensComboBox();
                    fecharTela();
                }
                
                if (ae.getSource() ==  botaoVoltar) {
                fecharTela();
                }
            }
        }    
    }   
}

