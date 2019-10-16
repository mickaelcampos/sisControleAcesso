package br.ufsc.ine5605.siscontroleacesso.persistencia;

import br.ufsc.ine5605.siscontroleacesso.entidades.Porta;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PortaDAO {
   
    private static PortaDAO instancia;
    private HashMap<String, Porta> cachePortas;
    private final String fileName = "portas.dat";
    
    private PortaDAO() {
        cachePortas = new HashMap<>();
        load();
    }
    
    public static PortaDAO getInstancia() {
        if (instancia == null) {
            instancia = new PortaDAO(); 
        }
        return instancia;
    }
    
    public Porta get(String codigo) {
        return cachePortas.get(codigo);
    }
    
    public void put(Porta porta) {
        cachePortas.put(porta.getCodigo(), porta);
        persist();
    }
    
    public void remove(Porta porta) {
        cachePortas.remove(porta.getCodigo());
        persist();
    }
    
    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cachePortas);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Persistencia de portas nao existente");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void load() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cachePortas = (HashMap<String, Porta>) oi.readObject();
            
            oi.close();
            fin.close();    
        } catch  (ClassNotFoundException ex) {
            System.out.println(ex);
            persist();
        } catch  (FileNotFoundException ex) {
            System.out.println("Persistencia de portas nao existente");
        } catch  (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<Porta> getList() {
        return new ArrayList<>(this.cachePortas.values());
    }
}
