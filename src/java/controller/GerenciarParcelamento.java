package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Orcamento;
import model.Parcelamento;
import model.Tipo;


public class GerenciarParcelamento extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idparcelamento = Integer.parseInt(request.getParameter("idparcelamento"));
        int idorcamento = Integer.parseInt(request.getParameter("idorcamento"));
        Parcelamento p = new Parcelamento();
        try{
            if(acao.equals("alterar")){
              if(GerenciarLogin.verificarPermissao(request, response)){    
                Parcelamento novo = new Parcelamento();
                novo = p.getCarregaPorID(idparcelamento);
                if(novo.getIdparcelamento()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_parcelamento.jsp=idorcamento="+idorcamento+";");
                    request.setAttribute("parcelamento", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "Parcelamento não encontrado";
                }
              }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
              if(GerenciarLogin.verificarPermissao(request, response)){  
                if(idparcelamento>0){
                    p.setIdparcelamento(idparcelamento);
                    Orcamento o = new Orcamento();
                    o.setIdorcamento(idorcamento);
                    p.setOrcamento(o);
                    if(p.excluir()){
                        mensagem ="Excluido com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir";
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
        out.println("location.href='form_parcelamento.jsp?idorcamento="+idorcamento+"';");
        out.println("</script>");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idparcelamento = request.getParameter("idparcelamento");
        String idtipo = request.getParameter("idtipo");
        String quantidade = request.getParameter("quantidade");
        String total = request.getParameter("total");
        String idorcamento = request.getParameter("idorcamento");
        String mensagem = "";
        
        try{
            Parcelamento p = new Parcelamento();
        
        if(!idparcelamento.isEmpty())
            p.setIdparcelamento(Integer.parseInt(idparcelamento));
            Tipo t = new Tipo();
            t.setIdtipo(Integer.parseInt(idtipo));
            p.setTipo(t);
            double quant = 0;
            if(!quantidade.isEmpty())
                quant = Double.parseDouble(quantidade.replace(".","").replace(",","."));
            p.setQuantidade(quant);
            
            double valortotal = 0;
            if (!total.isEmpty())
                valortotal = Double.parseDouble(total.replace(".","").replace(",","."));
            p.setTotal(valortotal);
            
           Orcamento o = new Orcamento();
           o.setIdorcamento(Integer.parseInt(idorcamento));
           p.setOrcamento(o);
            
            if(idtipo.isEmpty() || quantidade.equals("") || total.equals("") || idorcamento.isEmpty())
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                p.regParcelamento();
                    mensagem = "Gravado com sucesso";
               // else
                    //mensagem = "Erro ao gravar o parcelamento";
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao gravar no banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='form_parcelamento.jsp?idorcamento="+idorcamento+"';");
        out.println("</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
