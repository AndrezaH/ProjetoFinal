package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioDAO extends DataBaseDAO {


    public FuncionarioDAO() throws Exception {
    }

    public ArrayList<Funcionario> getLista() throws Exception {
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Funcionario f = new Funcionario();
            f.setIdfuncionario(rs.getInt("idfuncionario"));
            f.setNome(rs.getString("nome"));
            f.setTelefone(rs.getString("telefone"));
            f.setCargo(rs.getString("cargo"));
            Usuario u = new Usuario();
            f.setUsuario(u.getCarregaPorID(rs.getInt("idusuario")));
            lista.add(f);
        }
        this.desconectar();
        return lista;
    }

    public boolean gravar(Funcionario f) {
        try {
            this.conectar();
            String sql;
            if (f.getIdfuncionario() == 0) {
                sql = "INSERT INTO funcionario (nome, telefone, cargo, idusuario) "
                        + " VALUES (?,?,?,?)";
            } else {
                sql = "UPDATE funcionario SET nome=?, telefone=?, cargo=?, idusuario=? "
                        + "WHERE idfuncionario=?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getNome());
            pstm.setString(2, f.getTelefone());
            pstm.setString(3, f.getCargo());
            pstm.setInt(4, f.getUsuario().getIdusuario());
            if(f.getIdfuncionario()>0)
                pstm.setInt(5, f.getIdfuncionario());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Funcionario getCarregaPorID(int idfuncionario) throws Exception {
        Funcionario f = new Funcionario();
        String sql = "SELECT f.*, u.idusuario FROM funcionario f"
                + " INNER JOIN usuario u ON "
                + " u.idusuario = f.idfuncionario WHERE f.idfuncionario=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idfuncionario);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            f.setIdfuncionario(rs.getInt("idfuncionario"));
            f.setNome(rs.getString("nome"));
            f.setTelefone(rs.getString("telefone"));
            f.setCargo(rs.getString("cargo"));
            Usuario u = new Usuario();
            u.setIdusuario(rs.getInt("idusuario"));
            f.setUsuario(u);
        }
        this.desconectar();
        return f;

    }

    public boolean excluir(Funcionario f) {
        try {
            this.conectar();

            String sql = "DELETE FROM funcionario WHERE idfuncionario=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, f.getIdfuncionario());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
