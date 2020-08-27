package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Orcamento;
import model.Parcela;
import model.Parcelamento;


public class GerenciarParcela extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idparcela = Integer.parseInt(request.getParameter("idparcela"));
        int idparcelamento = Integer.parseInt(request.getParameter("idparcelamento"));
        int idorcamento = Integer.parseInt(request.getParameter("idorcamento"));
        Parcela p = new Parcela();
        try{
            
            
                       if(acao.equals("alterar")){
              if(GerenciarLogin.verificarPermissao(request, response)){  
                if(idparcela>0){
                    p.setIdparcela(idparcela);
                    Parcelamento pa = new Parcelamento();
                    pa.setIdparcelamento(idparcelamento);
                    p.setParcelamento(pa);
                    Orcamento o = new Orcamento();
                    o.setIdorcamento(idorcamento);
                    p.setOrcamento(o);
                    if(p.alterar()){
                        mensagem ="Atualização do status da parcela foi feita com sucesso!";
                    }else{
                        mensagem = "Erro ao atualizar";
                    }
                }
              }else{
                  mensagem = "Acesso Negado";
              }
            }
        }catch(Exception e){
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_parcela.jsp?idparcelamento="+idparcelamento+"&idorcamento="+idorcamento+"';");
        out.println("</script>");
   }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
           
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
