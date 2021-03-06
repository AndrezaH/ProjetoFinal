package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;


public class GerenciarMenuPerfil extends HttpServlet {

    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idperfil = request.getParameter("idperfil");
        String acao = request.getParameter("acao");
        
        try{
            
            if(acao.equals("gerenciar")){
              if(GerenciarLogin.verificarPermissao(request, response)){    
                Perfil p = new Perfil();
                p = p.getCarregaPorID(Integer.parseInt(idperfil));
                if(p.getIdperfil()>0){
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu_perfil.jsp");
                    request.setAttribute("perfilv", p);
                    disp.forward(request, response);
                }else{
                    mensagem = "Perfil não encontrado";
                }
              }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("desvincular")){
                if(GerenciarLogin.verificarPermissao(request, response)){  
                String idmenu = request.getParameter("idmenu");
                if(idmenu.equals("")||idmenu.isEmpty() || idperfil.equals("")||idperfil.isEmpty())
                    mensagem = "Campos obrigatórios devem ser selecionados";
                else{
                    Perfil p = new Perfil();
                    if(p.desvincular(Integer.parseInt(idmenu), Integer.parseInt(idperfil))){
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
        out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idperfil="+idperfil+"';");
        out.println("</script>");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idmenu = request.getParameter("idmenu");
        String idperfil = request.getParameter("idperfil");
        try{
            if(idmenu.isEmpty() || idmenu.equals("")|| idperfil.equals("")|| idperfil.isEmpty())
                mensagem = "Campos obrigatorios devem ser selecionados";
            else{
                Perfil p = new Perfil();
                if(p.vincular(Integer.parseInt(idmenu), Integer.parseInt(idperfil))){
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
        out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idperfil="+idperfil+"';");
        out.println("</script>");
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
