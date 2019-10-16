package br.ufsc.ine5605.siscontroleacesso.persistencia;

import br.ufsc.ine5605.siscontroleacesso.entidades.Acesso;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class AcessoDAO {
    
    private static AcessoDAO instancia;
    private HashMap<String, Acesso> cacheAcessos;
    private final String fileName = "acessos.dat";
    
    private AcessoDAO() {
        cacheAcessos = new HashMap<>();
        load();
    }
    
    public static AcessoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AcessoDAO(); 
        }
        return instancia;
    }
    
    public Acesso get(String idAcesso) {
        return cacheAcessos.get(idAcesso);
    }
    
    public void put(Acesso acesso) {
        cacheAcessos.put(acesso.getID(), acesso);
        persist();
    }
    
    public void remove(Acesso acesso) {
        cacheAcessos.remove(acesso.getID());
        persist();
    }
    
    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheAcessos);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Persistencia de acessos nao existente");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void load() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheAcessos = (HashMap<String, Acesso>) oi.readObject();
            
            oi.close();
            fin.close();    
        } catch  (ClassNotFoundException ex) {
            System.out.println(ex);
            persist();
        } catch  (FileNotFoundException ex) {
            System.out.println("Persistencia de acessos nao existente");
        } catch  (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<Acesso> getList() {
        return new ArrayList<>(this.cacheAcessos.values());
    }
    
}
