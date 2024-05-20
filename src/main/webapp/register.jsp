<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="views.RegisterView"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrese</title>
    <link rel="shortcut icon" href="../img/logo.jpg" type="image/gif" />
    <link rel="icon" href="assets/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style/style.css">
    <style>
        body {
            background: #461220;
            font-family: "poppins";
        }
        .transparent {
            background-color: rgba(255, 255, 255, 0.5) ; 
        }
    </style>
</head>
<body>
        <% String error = request.getParameter("error");
        out.print(new RegisterView(error)); %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>