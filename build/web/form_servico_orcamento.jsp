<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SG Financeiro</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-icon.png">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet" href="vendors/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="vendors/themify-icons/css/themify-icons.css">
<link rel="stylesheet" href="vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">
<link rel="stylesheet" href="vendors/jqvmap/dist/jqvmap.min.css">


<link rel="stylesheet" href="assets/css/style.css">

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
<script type="text/javascript">
function confirmarExclusao(idservico, nome, idorcamento) {
if (confirm('Deseja realmente desvincular o orcamento ' + nome + '?')) {
    location.href = 'gerenciar_servico_orcamento.do?acao=desvincular&idorcamento='+idorcamento+'&idservico='+idservico;
}
}
</script>  
</head>

<body>
<aside id="left-panel" class="left-panel">
<nav class="navbar navbar-expand-sm navbar-default">

<div class="navbar-header">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
    </button>
    <a class="navbar-brand" href="index.jsp/"><img src="images/luzati_logo.png" alt="Logo"></a>
    <a class="navbar-brand hidden" href="index.jsp/"><img src="images/luzati_logo.png" alt="Logo"></a>
</div>

<br><br><%@include file="menu.jsp" %>
</nav>
</aside><!-- /#left-panel -->

<!-- Left Panel -->

<!-- Right Panel -->

<div id="right-panel" class="right-panel">

<!-- Header-->
<header id="header" class="header">

<div class="header-menu">

    <div class="col-sm-7">
        <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
        <p>Bem vindo, <c:if test="${ulogado!=null}">${ulogado.nome}</c:if>!</p>
        </div>

        <div class="col-sm-5">
            <div class="user-area dropdown float-right">
                <a class="nav-link" href="gerenciar_login.do"><i class="fa fa-power-off"></i> Logout</a>
            </div>

        </div>
    </div>

</header><!-- /header -->
<!-- Header-->

<div class="breadcrumbs">

</div>
<div class="content mt-3"><!--corpo das páginas-->
    <div class="animated fadeIn">
        <div class="row">

            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3><strong class="card-title">Gerenciar Serviço e Orçamento</strong></h3>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-bordered table-hover display" id="listaServico">
                        <form name="form_servico_orcamento.do" action="gerenciar_servico_orcamento.do" method="POST">
                            <div class="row">
                                <input type="hidden" name="idorcamento" id="idorcamento"
                                       value="${orcamentov.idorcamento}"/>
                                <div class="form-group col-sm-8"><br>
                                    <tr>
                                        <th>
                                            <label for="idservico" class="control-label">
                                                Serviços
                                            </label>
                                            <select name="idservico" id="idservico" required>
                                                <option value="">Selecione o Serviço</option>
                                                <c:forEach var="s" items="${orcamentov.naoServico}">
                                                    <option value="${s.idservico}">${s.descricao}</option>
                                                </c:forEach>
                                            </select>
                                            </div></diiv>
                                        </th>
                                        <th>
                                    <div class="row">
                                        <button class="btn btn-success">Vincular</button>
                                        <a href="listar_orcamento.jsp" class="btn btn-warning">Voltar</a>    
                                    </div>
                                    </th>
                                    </tr>
                                    </table>
                                    <table class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Descrição</th>
                                                <th>Tipo de serviço</th>
                                                <th>Desvincular</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>ID</th>
                                                <th>Descrição</th>
                                                <th>Tipo de serviço</th>
                                                <th>Desvincular</th>
                                            </tr>
                                        </tfoot> 
                                        <tbody>
                                            <c:forEach var="s" items="${orcamentov.servico}">
                                                <tr>
                                                    <td>${s.idservico}</td>
                                                    <td>${s.descricao}</td>
                                                    <td>${s.tipo_servico.idtipo_servico}</td>
                                                    <td>
                                                        <button class="btn btn-danger" 
                                                                onclick="confirmarExclusao(${s.idservico}, '${s.descricao}',${orcamentov.idorcamento})">
                                                            <i class="fa fa-trash-o"></i>
                                                        </button>    
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table> 
                                </div>
                            </div>
                        </form>
                </div>
            </div>
        </div><!-- .animated -->
    </div> <!-- .content -->
</div><!-- /#right-panel -->

<!-- Right Panel -->

<script src="vendors/jquery/dist/jquery.min.js"></script>
<script src="vendors/popper.js/dist/umd/popper.min.js"></script>
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="assets/js/main.js"></script>


<script src="vendors/jqvmap/dist/jquery.vmap.min.js"></script>
<script src="vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
<script src="vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>


</body>
<script type="text/javascript" src="datatables/jquery.js"></script>
<script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#listaOrcamento").dataTable({
            "bJQueryUI": true,
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Não foram encontrados resultados",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfopostFix": "",
                "sSearch": "Pesquisar:",
                "sURL": "",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Próximos",
                    "sLast": "Último"
                }
            }
        })
    })
</script>   
</html>
