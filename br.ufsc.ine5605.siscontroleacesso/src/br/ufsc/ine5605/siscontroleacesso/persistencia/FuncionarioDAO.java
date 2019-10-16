package br.ufsc.ine5605.siscontroleacesso.persistencia;

import br.ufsc.ine5605.siscontroleacesso.entidades.Funcionario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FuncionarioDAO {
    private static FuncionarioDAO instancia;
    private HashMap<String, Funcionario> cacheFuncionarios;
    private final String fileName = "funcionarios.dat";
    
    private FuncionarioDAO() {
        cacheFuncionarios = new HashMap<>();
        load();
    }
    
    public static FuncionarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new FuncionarioDAO(); 
        }
        return instancia;
    }
    
    public Funcionario getFuncionario(String cpf) {
        return cacheFuncionarios.get(cpf);
    }
    
    public void put(Funcionario funcionario) {
        cacheFuncionarios.put(funcionario.getCPF(), funcionario);
        persist();
    }
    
    public void remove(Funcionario funcionario) {
        cacheFuncionarios.remove(funcionario.getCPF());
        persist();
    }
    
    public ArrayList<Funcionario> getList() {
        return new ArrayList<>(this.cacheFuncionarios.values());
    }
    
    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheFuncionarios);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Persistencia de funcionarios nao existente");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void load() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheFuncionarios = (HashMap<String, Funcionario>) oi.readObject();
            
            oi.close();
            fin.close();    
        } catch  (ClassNotFoundException ex) {
            System.out.println(ex);
            persist();
        } catch  (FileNotFoundException ex) {
            System.out.println("Persistencia de funcionarios nao existente");
        } catch  (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
}
