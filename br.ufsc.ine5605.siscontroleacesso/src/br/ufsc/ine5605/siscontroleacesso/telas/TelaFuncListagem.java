package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.persistencia.FuncionarioDAO;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class TelaFuncListagem extends JFrame implements ITelas {

    public DefaultTableModel modelo;
    private JTable tabela;
    private JPanel painelTabela, painelBotao;
    private JButton botaoVoltar;
    private JScrollPane baseTabela;
    
    public TelaFuncListagem() {
        super("Tela de Listagem de Funcionarios");
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
    
    public void inicializarComponentes() {
        
        GridBagConstraints c = new GridBagConstraints();
        TelaFuncListagem.GerenciadorBotoesTelaListar btGerenciador = new TelaFuncListagem.GerenciadorBotoesTelaListar();
        
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
        modelo.addColumn("Matricula");        
        modelo.addColumn("Nivel de Acesso");  
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(5);  
    }
    
    public void preencherTabela() {
       modelo.setNumRows(0);
      
       for (Funcionario funcionario : FuncionarioDAO.getInstancia().getList()) {
           modelo.addRow(new Object[]{funcionario.getNome(), funcionario.getCPF(), 
               funcionario.getCargo(), funcionario.getMatricula(), funcionario.getNivelAcesso().getNivel(), 
           });
       }
    }

    private class GerenciadorBotoesTelaListar implements ActionListener{

        public GerenciadorBotoesTelaListar() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }
        }
    }
    
}
