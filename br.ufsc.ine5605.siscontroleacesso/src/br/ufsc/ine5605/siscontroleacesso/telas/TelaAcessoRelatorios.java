package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlAcesso;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaAcessoRelatorios extends JFrame implements ITelas {

    private JLabel tituloCampoPesquisa;
    private JTextField textoPesquisa;
    private JButton botaoPesquisar, botaoVoltar;
    private JPanel painelPesquisa;
    
    private JPanel painelTabela;
    public DefaultTableModel modelo;
    private JTable tabela; //
    private JScrollPane baseTabela;
    
    public TelaAcessoRelatorios() {
        super("Tela de Relatorios");
        this.modelo = new DefaultTableModel();
        this.tabela =  new JTable(modelo);
        this.criarTabela();
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
        
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesTelaRelatorio btGerenciador = new GerenciadorBotoesTelaRelatorio();
        Dimension tamanhoCampoTextos = new Dimension(180, 25);
        this.painelPesquisa = new JPanel(new GridBagLayout());
        
        
        this.tituloCampoPesquisa = new JLabel();
        this.textoPesquisa = new JTextField();
        this.botaoPesquisar =  new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoPesquisar.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
       
        c.gridx = 0;
        c.gridy = 0;
        this.tituloCampoPesquisa.setText("CPF do Funcionario: ");
        this.painelPesquisa.add(this.tituloCampoPesquisa, c);

        c.gridx = 1;
        c.gridy = 0;
        this.textoPesquisa.setPreferredSize(tamanhoCampoTextos);
        this.painelPesquisa.add(this.textoPesquisa, c);
        
        c.gridx = 0;
        c.gridy = 1;
        this.botaoPesquisar.setText("Pesquisar");
        this.painelPesquisa.add(this.botaoPesquisar, c);
        
        c.gridx = 1;
        c.gridy = 1;
        this.botaoVoltar.setText("Voltar");
        this.painelPesquisa.add(this.botaoVoltar, c);
        
        this.painelTabela = new JPanel();
        this.baseTabela = new JScrollPane(tabela);
        
        painelTabela.setLayout(new BorderLayout());
        painelTabela.add(BorderLayout.CENTER, baseTabela);
        
        this.add(BorderLayout.PAGE_START, painelPesquisa);
        this.add(BorderLayout.PAGE_END, painelTabela);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void criarTabela() {
        
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Local");
        modelo.addColumn("Codigo do Local");
        modelo.addColumn("Hora/Data");
        modelo.addColumn("Autorizado/Negado");
        modelo.addColumn("ID Acesso");
    }
    
    public void preencherTabela(DefaultTableModel modelo, String cpfDaPesquisa) {
       modelo.setNumRows(0);
       for (Acesso acesso : CtrlAcesso.getInstancia().listarAcessosByCPF(cpfDaPesquisa)) {
           modelo.addRow(new Object[]{acesso.getFuncionario().getNome(), acesso.getFuncionario().getCPF(), 
               acesso.getPorta().getLocal(), acesso.getPorta().getCodigo(), acesso.getDataFormatada(), acesso.getTipoAcesso(), acesso.getID()
           });
       }
    }
    
    private class GerenciadorBotoesTelaRelatorio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() ==  botaoPesquisar) {
                try {
                    if(textoPesquisa.getText().isEmpty()) {
                        
                        throw new FuncionarioNaoExistenteException();
                    }
                    preencherTabela(modelo, textoPesquisa.getText()); 
                } catch (FuncionarioNaoExistenteException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            if (ae.getSource() ==  botaoVoltar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcesso().abrirTela();
            }
        }
    }
}
