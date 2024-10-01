<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <title>SGCM - Especialidades</title>
    <link rel="icon" href="imagens/logo_azul.png">
    <link rel="stylesheet" href="./css/estilo.css">
    
</head>
<body>
    <header>
        <div id="logo_header">
            <img src="imagens/logo_branco.png" alt="Logo SGCM">
            <p>SGCM - Sistema de Gerenciamento de Consultas Médicas</p>
        </div>
    </header>

    <form action="loginservlet" method="post" >
        <label for="nome">Nome</label>
        <input type="text" name="nome" id="nome" value="nome">
        <label for="senha">Senha</label>
        <input type="password" name="senha" id="senha" value="senha">
        <input class="botao_verde" type="submit" name="submit" value="Entrar">
    </form>

    <footer>
        <p>Telefone para contato: <span><a href="tel:+556832233030">+556832233030</a></span> | Email: <a
            href="mailto:suporte.sgcm@ufac.br">suporte.sgcm@ufac.br</a></p>
    </footer>

</body>
</html>