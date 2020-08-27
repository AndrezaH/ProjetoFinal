package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class UsuarioDAO extends DataBaseDAO{
    
    public UsuarioDAO() throws Exception{}
    
    
    public ArrayList<Usuario> getLista() throws Exception{
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Usuario u = new Usuario();
            u.setIdusuario(rs.getInt("idusuario"));
            u.setNome(rs.getString("nome"));
            u.setLogin(rs.getString("login"));
            u.setSenha(rs.getString("senha"));
            u.setStatus(rs.getInt("status"));
            Perfil p = new Perfil();
            u.setPerfil(p.getCarregaPorID(rs.getInt("idperfil")));
            lista.add(u);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Usuario u){
        try{
            this.conectar();
            String sql;
            if(u.getIdusuario()==0)
                    sql="INSERT INTO usuario (nome, login, senha, status, idperfil) "
                    + " VALUES (?,?,?,?,?)";
            else
                  sql = "UPDATE usuario SET nome=?, login=?, senha=?, status=?, idperfil=? "
                          + "WHERE idusuario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, u.getNome());
            pstm.setString(2, u.getLogin());
            pstm.setString(3, u.getSenha());
            pstm.setInt(4, u.getStatus());
            pstm.setInt(5,u.getPerfil().getIdperfil());
            if(u.getIdusuario()>0)
                pstm.setInt(6,u.getIdusuario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Usuario getCarregaPorID(int idusuario) throws Exception{
        Usuario u = new Usuario();
        String sql = "SELECT u.*, p.idperfil FROM usuario u"
                + " INNER JOIN perfil p ON "
                + " p.idperfil = u.idperfil WHERE u.idusuario=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idusuario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            u.setIdusuario(rs.getInt("u.idusuario"));
            u.setNome(rs.getString("u.nome"));
            u.setLogin(rs.getString("u.login"));
            u.setSenha(rs.getString("u.senha"));
            u.setStatus(rs.getInt("u.status"));
            Perfil p = new Perfil();
            p.setIdperfil(rs.getInt("u.idperfil"));
            u.setPerfil(p);
        }
        this.desconectar();
        return u;
    
    }
    public boolean excluir(Usuario u){
        try{
            this.conectar();
            
            String sql = "UPDATE usuario SET status = 2 WHERE idusuario=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,u.getIdusuario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Usuario getRecuperarUsuario(String login){
        Usuario u = new Usuario();
        String sql = "SELECT u.*, p.idperfil FROM usuario u"
                + " INNER JOIN perfil p ON "
                + " p.idperfil = u.idperfil WHERE u.login=? AND u.status=1 ";
        try{
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                u.setIdusuario(rs.getInt("u.idusuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));
                Perfil p = new Perfil();
                u.setPerfil(p.getCarregaPorID(rs.getInt("u.idperfil")));
            }
            this.desconectar();
            return u;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
