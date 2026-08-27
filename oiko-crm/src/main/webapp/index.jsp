<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.snpp.oikocrm.model.Cliente" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>oiko-crm - Gestión de Clientes</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body data-bs-theme="dark" class="bg-black text-light">

<div class="container py-5">
    <!-- Título Principal -->
    <div class="row mb-4">
        <div class="col">
            <h1 class="fw-bold text-info">oiko-crm</h1>
            <p class="text-muted">Sistema de Gestión de Clientes</p>
        </div>
    </div>

    <!-- Mensaje de Error -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger shadow-sm" role="alert">
            <strong>Error:</strong> <%= error %>
        </div>
    <%
        }
    %>

    <% Cliente clienteEditar = (Cliente) request.getAttribute("clienteEditar"); %>

    <!-- Formulario -->
    <div class="card bg-dark border-secondary shadow-lg mb-4">
        <div class="card-header bg-secondary bg-opacity-25 border-secondary">
            <h3 class="card-title h5 mb-0 text-warning">
                <%= clienteEditar != null ? "✏️ Editar Cliente" : "➕ Nuevo Cliente" %>
            </h3>
        </div>
        <div class="card-body">
            <form action="ClienteServlet" method="post">
                <% if (clienteEditar != null) { %>
                    <input type="hidden" name="idCliente" value="<%= clienteEditar.getIdCliente() %>">
                <% } %>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nombre:</label>
                        <input type="text" class="form-control bg-dark text-light border-secondary" name="nombre" value="<%= clienteEditar != null ? clienteEditar.getNombre() : "" %>" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Email:</label>
                        <input type="email" class="form-control bg-dark text-light border-secondary" name="email" value="<%= clienteEditar != null ? clienteEditar.getEmail() : "" %>" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Teléfono:</label>
                        <input type="text" class="form-control bg-dark text-light border-secondary" name="telefono" value="<%= clienteEditar != null ? clienteEditar.getTelefono() : "" %>">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label class="form-label">Empresa:</label>
                        <input type="text" class="form-control bg-dark text-light border-secondary" name="empresa" value="<%= clienteEditar != null ? clienteEditar.getEmpresa() : "" %>">
                    </div>
                     
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Dirección:</label>
                        <input type="text" class="form-control bg-dark text-light border-secondary" name="direccion" value="<%= clienteEditar != null ? clienteEditar.getDireccion() : "" %>">
                    </div>
                </div>

                <div class="mt-2">
                    <button type="submit" class="btn btn-success px-4">Guardar</button>
                    <% if (clienteEditar != null) { %>
                        <a href="ClienteServlet" class="btn btn-outline-secondary ms-2">Cancelar</a>
                    <% } %>
                </div>
            </form>
        </div>
    </div>

    <!-- Buscador y Orden -->
    <div class="card bg-dark border-secondary shadow-lg mb-4">
        <div class="card-body">
            <form action="ClienteServlet" method="get" class="row g-3 align-items-center">
                <div class="col-auto">
                    <label class="col-form-label text-muted">Buscar:</label>
                </div>
                <div class="col-auto">
                    <input type="text" class="form-control bg-dark text-light border-secondary" name="buscar" placeholder="Nombre del cliente...">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-outline-info">🔍 Buscar</button>
                </div>
                
                <div class="col-auto ms-auto">
                    <label class="col-form-label text-muted">Ordenar:</label>
                </div>
                <div class="col-auto">
                    <select name="orden" class="form-select bg-dark text-light border-secondary" onchange="this.form.submit()">
                        <option value="fecha">Recientes</option>
                        <option value="nombre">Alfabético</option>
                    </select>
                </div>
            </form>
        </div>
    </div>

    <!-- Tabla -->
    <div class="card bg-dark border-secondary shadow-lg">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover mb-0 align-middle">
                    <thead class="table-secondary text-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Empresa</th>
                            <th>Dirección</th>
                            <th>Registro</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
                            if (listaClientes != null && !listaClientes.isEmpty()) {
                                for (Cliente c : listaClientes) {
                        %>
                            <tr>
                                <td><%= c.getIdCliente() %></td>
                                <td class="fw-bold"><%= c.getNombre() %></td>
                                <td><%= c.getEmail() %></td>
                                <td><%= c.getTelefono() %></td>
                                <td><%= c.getEmpresa() %></td>
                                <td><%= c.getDireccion() %></td>
                                <td><%= c.getFechaRegistro() %></td>
                                <td class="text-center">
                                    <a href="ClienteServlet?accion=editar&id=<%= c.getIdCliente() %>" class="btn btn-sm btn-warning fw-bold me-1">Editar</a>
                                    <a href="ClienteServlet?accion=eliminar&id=<%= c.getIdCliente() %>" class="btn btn-sm btn-danger fw-bold" onclick="return confirm('¿Estás seguro de eliminar este cliente?');">Eliminar</a>
                                </td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="8" class="text-center py-4 text-muted">No se encontraron clientes registrados.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

</body>
</html>
<!--  Es la interfaz gráfica que ve el usuario en el navegador, esta se encarga de mostrar los datos recibidos desde el servidor -->
<!--  y enviar las peticiones mediante formularios y enlaces -->
