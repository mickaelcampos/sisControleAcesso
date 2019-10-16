package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlAcesso;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import br.ufsc.ine5605.siscontroleacesso.persistencia.AcessoDAO;
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

public final class TelaAcessoExclusao extends JFrame implements ITelas {
    
    private JLabel tituloExclusao;
    private JButton botaoExcluir, botaoVoltar;
    private JComboBox selecaoAcesso;

    public TelaAcessoExclusao() {
        super("Tela de Exclusao de Acessos");
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
        this.selecaoAcesso= new JComboBox();
        this.botaoExcluir = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoExcluir.setText("Excluir");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoExcluir.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloExclusao.setText("Selecione o registro de acesso que deseja excluir");
        container.add(this.tituloExclusao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoAcesso, c);
        
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
        selecaoAcesso.removeAllItems();
        for (Acesso acesso : AcessoDAO.getInstancia().getList()) {
            selecaoAcesso.addItem(acesso.getID());
        }
    }
            
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if (ae.getSource() == botaoExcluir) {

                try {
                    
                    if (selecaoAcesso.getSelectedItem() == null) {
                        throw new AcessoNaoExistenteException();
                    }
                    Acesso acessoSelecionado = AcessoDAO.getInstancia().get(selecaoAcesso.getSelectedItem().toString());
                    CtrlAcesso.getInstancia().delAcesso(acessoSelecionado.getID());
                    atualizarItensComboBox();
                    JOptionPane.showMessageDialog(null, "Registro de acesso excluido", "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                
                } catch (AcessoNaoExistenteException e) {
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
