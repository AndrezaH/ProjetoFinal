package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Servico;
import model.Tipo_Servico;

public class GerenciarServico extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idservico = Integer.parseInt(request.getParameter("idservico"));
        Servico s = new Servico();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Servico novo = new Servico();
                novo = s.getCarregaPorID(idservico);
                if(novo.getIdservico()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_servico.jsp");
                    request.setAttribute("servico", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "Servico não encontrado";
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                if(idservico>0){
                    s.setIdservico(idservico);
                    if(s.excluir()){
                        mensagem ="Serviço excluido com sucesso!";
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
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idservico = request.getParameter("idservico");
        String descricao = request.getParameter("descricao");
        String idtipo_servico = request.getParameter("idtipo_servico");
        String mensagem = "";
      
        Servico s = new Servico();
        if(!idservico.isEmpty())
            s.setIdservico(Integer.parseInt(idservico));
            s.setDescricao(descricao);
            Tipo_Servico ts = new Tipo_Servico();
            ts.setIdtipo_servico(Integer.parseInt(idtipo_servico));
            s.setTipo_servico(ts);
        try{
            if(descricao.equals("")|| idtipo_servico.isEmpty())
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                if(s.gravar())
                    mensagem = "Gravado com sucesso";
                else
                    mensagem = "Erro ao gravar o servico";
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao gravar no banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");
            
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
