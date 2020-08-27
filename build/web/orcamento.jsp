<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon.ico">
        
       
        <title>Proposta Orçamentária</title>
    </head>
    <body>
        <table align="center" border="1px black" width="1000px">
                <tr>
                    <input type="hidden"  name="idorcamento" id="idorcamento" value="${param.idorcamento}"/>
                    <input type="hidden"  name="idcliente" id="idcliente" value="${param.idcliente}"/>
                     
                    <th ><h3>Proposta Orçamentária</h3></th><th><img src="images/luzati_logo.png" alt="Logo"></th><td>Identificador:</td>
                </tr>
            <jsp:useBean class="model.Cliente" id="gerar"/>
            <c:forEach var="gerar" items="${gerar.lista}">
                <tr><th colspan="8">Informações do cliente</th></tr>
                <tr>
                    <td colspan="2">Empresa: ${gerar.nome_empresa}</td>
                    <td colspan="3">CEO: ${gerar.nome_ceo}</td>
                    <td colspan="2">Localização: ${gerar.endereco}</td>
                </tr>
                <tr>
                    <td colspan="3">Área de atuação: ${gerar.area_atuacao}</td>
                    <td>Descrição: ${gerar.descricao}</td>
                </tr>
            </c:forEach>
                <tr>
                    <th colspan="8">Informações da Proposta Orçamentária</th>
                </tr>
                <tr>
                    <td colspan="2">Orçamento elaborado em: ${data_orcamento}</td>
                    <td celspan="2">Vencimento: ${data_vencimento}</td>
                </tr>
                <tr>
                    <td colspan="2">Serviço(s) contratado(s):</td>
                </tr>
                <tr>
                    <td>Valor total do orçamento:</td>
                </tr>
                <tr>
                    <td>Parcelamento:</td>
                     <td>Valor:</td>
                </tr>
                <tr>
                    <td>Entrada</td>
                    <td>Valor:</td>
                </tr>
        </table>
    </body>
</html>
