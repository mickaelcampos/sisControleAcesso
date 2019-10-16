package br.ufsc.ine5605.siscontroleacesso.telas;

import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlAcesso;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlTelas;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import br.ufsc.ine5605.siscontroleacesso.persistencia.FuncionarioDAO;
import br.ufsc.ine5605.siscontroleacesso.persistencia.PortaDAO;
import java.awt.Container;
import java.awt.Dimension;
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

public class TelaAcessoRealizar extends JFrame implements ITelas {
    
    private JLabel tituloFuncionario, tituloPorta;
    private JComboBox selecaoFuncionario, selecaoPorta;
    private JButton botaoRealizarAcesso, botaoVoltar;
    
    
    public TelaAcessoRealizar() {
        super("Tela de Realizar Acessos");
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
        GerenciadorBotoesPrincipal btGerenciador = new GerenciadorBotoesPrincipal();
        Dimension tamanhoComboBox = new Dimension(125, 25);
        
        this.tituloFuncionario = new JLabel();
        this.tituloPorta = new JLabel();
        
        this.selecaoFuncionario = new JComboBox();
        this.selecaoPorta =  new JComboBox();
        
        this.botaoRealizarAcesso =  new JButton();
        this.botaoVoltar =  new JButton();
        
        botaoRealizarAcesso.setText("Acessar");
        botaoVoltar.setText("Voltar");
        
        botaoRealizarAcesso.addActionListener(btGerenciador);
        botaoVoltar.addActionListener(btGerenciador);
        
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        
        c.gridx = 0;
        c.gridy = 0;
        this.tituloFuncionario.setText("Selecione o Funcionario para Acessar: ");
        container.add(this.tituloFuncionario, c);
        
        c.gridx = 1;
        c.gridy = 0;
        this.selecaoFuncionario.setPreferredSize(tamanhoComboBox);
        container.add(this.selecaoFuncionario, c);
        
        c.gridx = 0;
        c.gridy = 2;
        this.tituloPorta.setText("Selecione a Porta para Acessar: ");
        container.add(this.tituloPorta, c);
        
        c.gridx = 1;
        c.gridy = 2;
        selecaoPorta.setPreferredSize(tamanhoComboBox);
        container.add(this.selecaoPorta, c);
        
        c.gridx = 1;
        c.gridy = 4;
        container.add(this.botaoRealizarAcesso, c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
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
        
        selecaoPorta.removeAllItems();
        for (Porta porta : PortaDAO.getInstancia().getList()) {
            selecaoPorta.addItem(porta.getCodigo());
        }
    }
    
    private class GerenciadorBotoesPrincipal implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoRealizarAcesso) {
                
                try {
                    
                    if(selecaoFuncionario.getSelectedItem() ==  null) {
                        throw new FuncionarioNaoExistenteException();
                    }
                    
                    if (selecaoPorta.getSelectedItem() == null) {
                        throw new PortaNaoExistenteException();
                    }
                    
                    Funcionario funcionarioSelecionado = FuncionarioDAO.getInstancia().getFuncionario(selecaoFuncionario.getSelectedItem().toString());
                    Porta portaSelecionada = PortaDAO.getInstancia().get(selecaoPorta.getSelectedItem().toString());
                    
                    if (CtrlAcesso.getInstancia().solicitarAcesso(funcionarioSelecionado.getCPF(), portaSelecionada.getCodigo())) {
                    JOptionPane.showMessageDialog(null, "Autorizado", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Negado", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
                    }
                
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção!", JOptionPane.INFORMATION_MESSAGE);
                }    
            }
     
            if(ae.getSource() == botaoVoltar){
                fecharTela();
                CtrlTelas.getInstancia().mostrarTelaAcesso().abrirTela();
            }
        }
    }
}
