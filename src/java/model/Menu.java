package model;


import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Menu {
    private int idmenu;
    private String menu;
    private String link;
    private String icone;
    private int exibir;

    public Menu() {
    }

    public Menu(int idmenu, String menu, String link, String icone, int exibir) {
        this.idmenu = idmenu;
        this.menu = menu;
        this.link = link;
        this.icone = icone;
        this.exibir = exibir;
    }

     public ArrayList<Menu> getLista() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.gravar(this);
    }
    
    public Menu getCarregaPorID(int idmenu) throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.getCarregaPorID(idmenu);
    }
    
    public boolean excluir() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.excluir(this);
    }
    
    
}
