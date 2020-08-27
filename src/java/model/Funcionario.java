package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Funcionario {
    
    private int idfuncionario;
    private String nome;
    private String telefone;
    private String cargo;
    private Usuario usuario;
    
    public Funcionario(){}
  
    public Funcionario(int idfuncionario, String nome, String telefone, String cargo, Usuario Usuario) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.telefone = telefone;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    
    public ArrayList<Funcionario> getLista() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.gravar(this);
    }
    
    public Funcionario getCarregaPorID(int idfuncionario) throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getCarregaPorID(idfuncionario);
    }
    
    public boolean excluir() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.excluir(this);
    }
}
