package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlFuncionario;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.persistencia.FuncionarioDAO;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public final class TelaFuncExclusao extends JFrame implements ITelas {
    
    private JLabel tituloExclusao;
    private JButton botaoExcluir, botaoVoltar;
    private JComboBox selecaoFuncionario;

    public TelaFuncExclusao() {
        super("Tela de Exclusao de Funcionarios");
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
        GerenciadorBotoes btGerenciador = new GerenciadorBotoes();
        
        this.tituloExclusao = new JLabel();
        this.selecaoFuncionario= new JComboBox();
        this.botaoExcluir = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoExcluir.setText("Excluir");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoExcluir.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloExclusao.setText("Selecione o funcionario que deseja excluir");
        container.add(this.tituloExclusao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoFuncionario, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(this.botaoExcluir, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(this.botaoVoltar, c);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void atualizarItensComboBox(){
        selecaoFuncionario.removeAllItems();
        for (Funcionario funcionario : FuncionarioDAO.getInstancia().getList()) {
            selecaoFuncionario.addItem(funcionario.getCPF());
        }
    }
            
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if (ae.getSource() == botaoExcluir) {
                
                try {
                    
                    if (selecaoFuncionario.getSelectedItem() == null) {
                        throw new FuncionarioNaoExistenteException();
                    }
                    Funcionario funcSelecionado = FuncionarioDAO.getInstancia().getFuncionario(selecaoFuncionario.getSelectedItem().toString());
                    CtrlFuncionario.getInstancia().delFuncionarioByCPF(funcSelecionado.getCPF());
                    atualizarItensComboBox();
                    JOptionPane.showMessageDialog(null, "Funcionario excluido", "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                
                } catch (FuncionarioNaoExistenteException e) {
                    System.out.println("exception aqui");
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            

            if (ae.getSource() ==  botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaFunc().setVisible(true);
            }
        }
    }
}