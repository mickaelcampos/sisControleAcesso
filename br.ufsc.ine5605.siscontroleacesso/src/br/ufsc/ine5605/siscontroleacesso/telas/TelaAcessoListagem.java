package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import br.ufsc.ine5605.siscontroleacesso.persistencia.AcessoDAO;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaAcessoListagem extends JFrame implements ITelas {
    
    public DefaultTableModel modelo;
    private JTable tabela;
    private JPanel painelTabela, painelBotao;
    private JButton botaoVoltar;
    private JScrollPane baseTabela;
    
    
    public TelaAcessoListagem() {
        super("Tela de Listagem de Acessos");
        this.modelo = new DefaultTableModel();
        this.criarTabela();
        this.inicializarComponentes();
        this.preencherTabela();
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
        GerenciadorBotoesTelaListar btGerenciador = new GerenciadorBotoesTelaListar();
        
        this.painelTabela = new JPanel();
        this.painelBotao =  new JPanel();
        this.baseTabela = new JScrollPane(tabela);
        this.botaoVoltar =  new JButton();
        this.botaoVoltar.addActionListener(btGerenciador);
        
        this.painelTabela.setLayout(new BorderLayout());
        this.painelTabela.add(BorderLayout.CENTER, baseTabela);
        
        this.painelBotao.setLayout(new GridBagLayout());
        
        c.gridx = 0;
        c.gridy = 0;
        this.botaoVoltar.setText("Voltar");
        this.painelBotao.add(this.botaoVoltar, c);
        
        getContentPane().add(painelTabela);
        getContentPane().add(painelBotao);

        this.add(BorderLayout.PAGE_START, painelTabela);
        this.add(BorderLayout.PAGE_END, painelBotao);
        
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void criarTabela() {
        
        this.tabela = new JTable(modelo);
        
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Cargo");
        modelo.addColumn("Nivel de Acesso");
        modelo.addColumn("Local");
        modelo.addColumn("Codigo do Local");
        modelo.addColumn("Nivel de Seguranca");
        modelo.addColumn("Hora/Data");
        modelo.addColumn("Tipo de Acesso");
        modelo.addColumn("ID Acesso");
    }
    
    public void preencherTabela() {
       modelo.setNumRows(0);
      
       for (Acesso acesso : AcessoDAO.getInstancia().getList()) {
           modelo.addRow(new Object[]{acesso.getFuncionario().getNome(), acesso.getFuncionario().getCPF(), 
               acesso.getFuncionario().getCargo(), acesso.getFuncionario().getNivelAcesso().getNivel(), 
               acesso.getPorta().getLocal(), acesso.getPorta().getCodigo(), acesso.getPorta().getNivelAcesso().getNivel(), acesso.getDataFormatada(), acesso.getTipoAcesso(), acesso.getID()
           });
        } 
    }

    private class GerenciadorBotoesTelaListar implements ActionListener{

        public GerenciadorBotoesTelaListar(){
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoVoltar) {
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcesso().abrirTela();
            }
        }
    }
}
