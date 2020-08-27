<!doctype html>
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
    
    <script type="text/javascript" src="mascara/jquery-1.2.6.pack.js"></script>
    <script type="text/javascript" src="mascara/jquery.maskedinput-1.1.4.pack.js"/></script>
    <script type="text/javascript">
       $(document).ready(function () {
        $("#telefone").mask("(99) 9999-9999");
       });
    </script>
	
</head>

<body>


    <!-- Left Panel -->

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
        <div class="content mt-3">
            <div class="animated fadeIn">
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h3><strong class="card-title">Cadastrar um novo cliente</strong></h3>
                            </div>
                            <div class="card-body">
                               <form name="form_cliente.jsp" action="gerenciar_cliente.do" method="POST">
                                   <div class="row">
                                       <div class="form-group col-sm-8">
                                           <input type="hidden" name="idcliente" id="idcliente"
                                                  class="form-control" value="${cliente.idcliente}"/>
                                           <label for="nome_empresa" class="control-label">
                                               Nome da Empresa
                                           </label>
                                           <input type="text" name="nome_empresa" id="nome_empresa"
                                                  class="form-control" required=""
                                                  value="${cliente.nome_empresa}"/>
                                       </div>
                                   </div>
                                       <div class="row">
                                       <div class="form-group col-sm-8">
                                           <label for="nome_ceo" class="control-label">
                                                Nome do Cliente
                                           </label>
                                           <input type="text" name="nome_ceo" id="nome_ceo"
                                                  class="form-control" required=""
                                                  value="${cliente.nome_ceo}"/>
                                       </div>
                                   </div>
                                   <div class="row">
                                       <div class="form-group col-sm-8">
                                           <label for="telefone" class="control-label">
                                               Telefone
                                           </label>
                                           <input type="text" name="telefone" id="telefone"
                                                  class="form-control" required=""
                                                  value="${cliente.telefone}"/>
                                       </div>
                                   </div>
                                   <div class="row">
                                       <div class="form-group col-sm-8">
                                           <label for="endereco" class="control-label">
                                               Endereço
                                           </label>
                                           <input type="text" name="endereco" id="endereco"
                                                  class="form-control" value="${cliente.endereco}"/>
                                       </div>
                                   </div>
                                   <div class="row">
                                       <div class="form-group col-sm-8">
                                           <label for="descricao" class="control-label">
                                               Descrição da Empresa
                                           </label>
                                           <input type="text" name="descricao" id="descricao"
                                                  class="form-control" value="${cliente.descricao}"/>
                                       </div>
                                   </div>
                                       <div class="row">
                                       <div class="form-group col-sm-8">
                                           <label for="area_atuacao" class="control-label">
                                               Área de atuação
                                           </label>
                                           <input type="text" name="area_atuacao" id="area_atuacao"
                                                  class="form-control" value="${cliente.area_atuacao}"/>
                                       </div>
                                   </div>
                                   <div class="row">
                                       <button class="btn btn-success">Gravar</button>
                                       <a href="listar_cliente.jsp" class="btn btn-warning">
                                           Voltar
                                       </a>    
                                   </div>
                               </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- .animated -->
        </div><!-- .content -->
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

</html>
