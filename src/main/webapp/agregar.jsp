<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Datos Bancarios</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="icon" href="assets/img/logo.jpg" type="image/x-icon">
    <style>
        body {
            background: #461220;
            font-family: "poppins";
        }
        .container {
            max-width: 500px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                Agregar Datos Bancarios
            </div>
            <div class="card-body">
                <form action="agregarDatos.jsp" method="post">
                    <div class="form-group">
                        <label for="nombreTitular">Número de Cuenta:</label>
                        <input type="text" class="form-control" id="nombreTitular" name="nombreTitular" required>
                    </div>
                    <div class="form-group">
                        <label for="numeroCuenta">Nombre del Titular:</label>
                        <input type="text" class="form-control" id="numeroCuenta" name="numeroCuenta" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Saldo:</label>
                        <input type="text" class="form-control" id="saldo" name="saldo" required>
                    </div>
                    <div class="form-group">
                        <label for="tipoTarjeta">Tipo de Tarjeta:</label>
                        <select class="form-control" id="tipoTarjeta" name="tipoTarjeta" required>
                            <option value="Credito">Crédito</option>
                            <option value="Debito">Débito</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="home.jsp" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>
    </div>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
