package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cliente {
    
    private int idcliente;
    private String nome_empresa;
    private String nome_ceo;
    private String telefone;
    private String endereco;
    private String descricao;
    private String area_atuacao;
    
    public Cliente(){}
    
    public Cliente (int idcliente, String nome_empresa,String nome_ceo, String telefone, String endereco, String descricao, String area_atuacao){
        this.idcliente = idcliente;
        this.nome_empresa = nome_empresa;
        this.nome_ceo = nome_ceo;
        this.telefone = telefone;
        this.endereco = endereco;
        this.descricao = descricao;
        this.area_atuacao = area_atuacao;
    }    
    public ArrayList<Cliente> getLista() throws Exception{
        ClienteDAO DAO = new ClienteDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws  Exception{
        ClienteDAO DAO = new ClienteDAO();
        return DAO.gravar(this);
    }
    
    public Cliente getCarregaPorID( int idciente) throws Exception{
        ClienteDAO DAO = new ClienteDAO();
        return DAO.getCarregaPorID(idciente);
    }
    
    public boolean excluir() throws Exception{
        ClienteDAO DAO = new ClienteDAO();
        return DAO.excluir(this);
    }
}
