package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Usuario {
   
    private int idusuario;
    private String nome;
    private String login;
    private String senha;
    private int status;
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(int idusuario, String nome, String login, String senha, int status, Perfil perfil) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.perfil = perfil;
    }

   
    public ArrayList<Usuario> getLista() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.gravar(this);
    }
    
    public Usuario getCarregaPorID(int idusuario) throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getCarregaPorID(idusuario);
    }
    
    public boolean excluir() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.excluir(this);
    }
     public Usuario getRecuperarUsuario(String login) throws Exception{
         UsuarioDAO DAO = new UsuarioDAO();
         return DAO.getRecuperarUsuario(login);
    }
    
}
