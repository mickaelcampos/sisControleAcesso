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

public class TelaPortaAlteracao extends JFrame implements ITelas {
    private JLabel tituloAlteracao;
    private JButton botaoAlterar, botaoVoltar;
    private JComboBox selecaoPorta;
    
    public TelaPortaAlteracao() {
        super("Tela de Alteracao de Portas");
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
        this.selecaoPorta = new JComboBox();
        this.botaoAlterar = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoAlterar.setText("Alterar");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoAlterar.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloAlteracao.setText("Selecione a Porta que deseja Alterar");
        container.add(this.tituloAlteracao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoPorta, c);
        
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
        selecaoPorta.removeAllItems();
        for (Porta porta : PortaDAO.getInstancia().getList()) {
            selecaoPorta.addItem(porta.getCodigo());
        }
    }
             
    private class GerenciadorBotoesTelaAlteracao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoAlterar) {
                
                try {
                    
                    if (selecaoPorta.getSelectedItem() == null) {
                        throw new AcessoNaoExistenteException();
                    } 
                    
                    Porta portaSelecionado = PortaDAO.getInstancia().get(selecaoPorta.getSelectedItem().toString());
                    TelaPortaAlteracaoDados telaAlteracaoDados = new TelaPortaAlteracaoDados(portaSelecionado);
                    atualizarItensComboBox();
                    telaAlteracaoDados.setVisible(true);
                    
                } catch (AcessoNaoExistenteException e) {
                        System.out.println("exception aqui");
                        JOptionPane.showMessageDialog(null, "Nenhuma Porta alterada!", "Atencao!", JOptionPane.INFORMATION_MESSAGE);

                }
            } 
            
            if (ae.getSource() == botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaPorta().setVisible(true);
            }
        }
    }
    
    private class TelaPortaAlteracaoDados extends JFrame implements ITelas {

        private Porta portaAlterar;
        private JLabel tituloNome,tituloCodigo, tituloLocal, tituloNivelAcesso;
        private JTextField textoNome, textoCodigo, textoLocal, textoNivelAcesso;
        private JButton botaoSalvar, botaoVoltar;


        public TelaPortaAlteracaoDados(Porta portaSelecionado) {
            super("Tela de Alteracao dos Dados da Porta");
            this.portaAlterar = portaSelecionado;
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

            this.textoCodigo = new JTextField(portaAlterar.getCodigo());
            this.textoLocal = new JTextField(portaAlterar.getLocal());
            this.textoNivelAcesso = new JTextField(portaAlterar.getNivelAcesso().getNivel());

            this.botaoSalvar = new JButton();
            this.botaoSalvar.addActionListener(btGerenciador);

            this.botaoVoltar = new JButton();
            this.botaoVoltar.addActionListener(btGerenciador);

            c.insets = new Insets(10, 10, 10, 10);
            c.anchor = GridBagConstraints.WEST;

            c.gridx = 0;
            c.gridy = 0;
            this.tituloCodigo.setText("Codigo da Porta: ");
            container.add(this.tituloCodigo, c);

            c.gridx = 1;
            c.gridy = 0;
            this.textoCodigo.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoCodigo, c);


            c.gridx = 0;
            c.gridy = 1;
            this.tituloLocal.setText("Local da Porta: ");
            container.add(this.tituloLocal, c);

            c.gridx = 1;
            c.gridy = 1;
            this.textoLocal.setPreferredSize(tamanhoCampoTextos);
            container.add(this.textoLocal, c);

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
                    
                    CtrlPorta.getInstancia().alterarPortaByCodigo(portaAlterar.getCodigo(), 
                            textoLocal.getText(), Integer.parseInt(textoNivelAcesso.getText()));
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
