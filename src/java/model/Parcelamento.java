package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parcelamento {
    private int idparcelamento;
    private Tipo tipo;
    private double quantidade;
    private double total;
    private Orcamento orcamento;
    
    public Parcelamento(){}
    
    public Parcelamento (int idparcelamento, Tipo tipo, double quantidade, double total, Orcamento orcamento){
        this.idparcelamento = idparcelamento;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.total = total;
        this.orcamento = orcamento;
    }
     public ArrayList<Parcelamento> getLista() throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
       return DAO.getLista();
     }
     
     public ArrayList<Parcelamento> getListaParcelamentosPorOcamento(int idparcelamento) throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
       return DAO.getListaParcelamentosPorOcamento(idparcelamento);
   }
   
   public boolean gravar() throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
       return DAO.gravar(this);
   }
   public void regParcelamento() throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
         DAO.regParcelamento(this);
   }
   
   public Parcelamento getCarregaPorID(int idparcelamento) throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
       return DAO.getCarregaPorID(idparcelamento);
   }
   
   public boolean excluir() throws Exception{
       ParcelamentoDAO DAO = new ParcelamentoDAO();
       return DAO.excluir(this);
   }
}
