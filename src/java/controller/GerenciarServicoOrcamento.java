package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Orcamento;
import model.Servico;


public class GerenciarServicoOrcamento extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idorcamento = request.getParameter("idorcamento");
        String acao = request.getParameter("acao");
        
        try{
            if(acao.equals("gerenciar")){
              if(GerenciarLogin.verificarPermissao(request, response)){    
                Orcamento o = new Orcamento();
                o = o.getCarregaPorID(Integer.parseInt(idorcamento));
                if(o.getIdorcamento()>0){
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico_orcamento.jsp");
                    request.setAttribute("orcamentov", o);
                    disp.forward(request, response);
                }else{
                    mensagem = "Orçamento não encontrado";
                }
              }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("desvincular")){
             if(GerenciarLogin.verificarPermissao(request, response)){
                 String idservico = request.getParameter("idservico");
                if(idservico.equals("")||idservico.isEmpty() || idorcamento.equals("")||idorcamento.isEmpty())
                    mensagem = "Campos obrigatórios devem ser selecionados";
                else{
                    Orcamento o = new Orcamento();
                    if(o.desvincular(Integer.parseInt(idservico), Integer.parseInt(idorcamento))){
                        mensagem = "Desvinculado com sucesso";
                    }else{
                        mensagem = "Erro ao desvincular";
                    }
                }
                }else{
                    mensagem = "Acesso Negado";
                }
             }
        }catch(Exception e){
            out.print(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_servico_orcamento.do?acao=gerenciar&idorcamento="+idorcamento+"';");
        out.println("</script>");
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idorcamento = request.getParameter("idorcamento");
        String idservico = request.getParameter("idservico");
        try{
            if(idservico.equals("")|| idservico.isEmpty()||idorcamento.isEmpty() || idorcamento.equals(""))
                mensagem = "Campos obrigatorios devem ser selecionados";
            else{
                Orcamento o = new Orcamento();
                if(o.vincular(Integer.parseInt(idservico), Integer.parseInt(idorcamento))){
                    mensagem = "Vinculado com sucesso";
                }else{
                    mensagem = "Erro ao vincular";
                }
            }
        
        }catch(Exception e){
            out.print(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_servico_orcamento.do?acao=gerenciar&idorcamento="+idorcamento+"';");
        out.println("</script>");    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
