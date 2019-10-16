package br.ufsc.ine5605.siscontroleacesso;


import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlAcesso;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlFuncionario;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlPorta;
import br.ufsc.ine5605.siscontroleacesso.controladores.CtrlPrincipal;
import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import br.ufsc.ine5605.siscontroleacesso.entidades.ICredenciavel;



public class SisControleAcesso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
              
//        CtrlFuncionario.getInstancia().addFuncionario("123456", "pedro", "professor", 123, 2);
//        CtrlFuncionario.getInstancia().addFuncionario("123457", "joao", "diretor", 456, 2);
//        CtrlFuncionario.getInstancia().addFuncionario("123458", "antonio", "seguranca", 789, 3);
//        CtrlFuncionario.getInstancia().addFuncionario("123459", "julio", "secretario", 741, 2);
//        CtrlFuncionario.getInstancia().addFuncionario("123460", "alberto", "aluno", 852, 1);
//        
//        CtrlPorta.getInstancia().addPorta("111", "direcao", 2);
//        CtrlPorta.getInstancia().addPorta("222", "servidor", 3);
//        CtrlPorta.getInstancia().addPorta("333", "sala de aula", 1);
//        CtrlPorta.getInstancia().addPorta("444", "laboratorio", 2);
//        CtrlPorta.getInstancia().addPorta("555", "refeitorio", 1);
//        
//        CtrlAcesso.getInstancia().solicitarAcesso("123456", "111");
//        CtrlAcesso.getInstancia().solicitarAcesso("123457", "222");
//        CtrlAcesso.getInstancia().solicitarAcesso("123458", "333");
//        CtrlAcesso.getInstancia().solicitarAcesso("123459", "444");
//        CtrlAcesso.getInstancia().solicitarAcesso("123460", "555");
//        System.out.println("Primeiro cadastro");
        
        // INICIA COM INTERFACE GRAFICA   
        CtrlPrincipal.getInstancia().getCtrlTelas().mostrarTelaPrincipal().abrirTela();
    }
}
