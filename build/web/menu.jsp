<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);
%>

 <div id="main-menu" class="main-menu collapse navbar-collapse">
    <ul class="nav navbar-nav">
        <c:if test="${ulogado!=null && ulogado.perfil !=null}">
            <c:forEach var="menu" items="${ulogado.perfil.menus}">
                <c:if test="${menu.exibir==1}">                   
                    <li class="active">
                        <a href="${menu.link}"><i class="menu-icon ${menu.icone}"></i>${menu.menu}</a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
    </ul>
</div><!-- /.navbar-collapse -->

<link rel="stylesheet" href="assets/css/style.css">
