package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Parcela {
    
    private int idparcela;
    private double valor;
    private String status;
    private Parcelamento parcelamento;
    private Orcamento orcamento;
    
    public Parcela(){}
    
    public Parcela(int idparcela,  double valor, String status, Parcelamento parcelamento, Orcamento orcamento){
        this.idparcela = idparcela;
        this.valor = valor;
        this.status = status;
        this.parcelamento = parcelamento;
        this.orcamento = orcamento;
    }
    
    public ArrayList<Parcela> getLista(int idparcelamento, int idorcamento) throws Exception{
        ParcelaDAO DAO = new ParcelaDAO();
        return DAO.getLista(idparcelamento, idorcamento);
    }
    
    public Parcela getCarregaPorID(int idparcela, int idparcelamento, int idorcamento) throws Exception{
        ParcelaDAO DAO = new ParcelaDAO();
        return DAO.getCarregaPorID(idparcela, idparcelamento, idorcamento);
    }
 public boolean alterar() throws  Exception{
        ParcelaDAO DAO = new ParcelaDAO();
        return DAO.alterar(this);
    }
    
}

