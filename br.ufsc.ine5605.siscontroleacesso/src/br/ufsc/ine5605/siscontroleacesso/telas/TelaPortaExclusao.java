package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlPorta;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import br.ufsc.ine5605.siscontroleacesso.persistencia.PortaDAO;
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

public final class TelaPortaExclusao extends JFrame implements ITelas {
    
    private JLabel tituloExclusao;
    private JButton botaoExcluir, botaoVoltar;
    private JComboBox selecaoPorta;

    public TelaPortaExclusao() {
        super("Tela de Exclusao de Portas");
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
        this.selecaoPorta= new JComboBox();
        this.botaoExcluir = new JButton();
        this.botaoVoltar = new JButton();
        
        this.botaoExcluir.setText("Excluir");
        this.botaoVoltar.setText("Voltar");
        
        this.botaoExcluir.addActionListener(btGerenciador);
        this.botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloExclusao.setText("Selecione a porta que deseja excluir");
        container.add(this.tituloExclusao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(this.selecaoPorta, c);
        
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
        selecaoPorta.removeAllItems();
        for (Porta porta : PortaDAO.getInstancia().getList()) {
            selecaoPorta.addItem(porta.getCodigo());
        }
    }
            
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if (ae.getSource() == botaoExcluir) {
                
                try {
                    
                    if (selecaoPorta.getSelectedItem() == null) {
                        throw new PortaNaoExistenteException();
                    }
                    Porta portaSelecionada = PortaDAO.getInstancia().get(selecaoPorta.getSelectedItem().toString());
                    CtrlPorta.getInstancia().delPortaByCodigo(portaSelecionada.getCodigo());
                    atualizarItensComboBox();
                    JOptionPane.showMessageDialog(null, "Porta excluida", "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                
                } catch (PortaNaoExistenteException e) {
                    System.out.println("exception aqui");
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Atencao!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            

            if (ae.getSource() ==  botaoVoltar) {
                setVisible(false);
                CtrlTelas.getInstancia().mostrarTelaPorta().setVisible(true);
            }
        }
    }
}