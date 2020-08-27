package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Andreza Honório
 */@Getter
 @Setter
public class Tipo {
    private int idtipo;
    private String tipo;

    public Tipo(int idtipo, String tipo) {
        this.idtipo = idtipo;
        this.tipo = tipo;
    }

    public Tipo() {
    }
    
    public ArrayList<Tipo> getLista() throws Exception{
       TipoDAO DAO = new TipoDAO();
       return DAO.getLista();
   }
    
}

