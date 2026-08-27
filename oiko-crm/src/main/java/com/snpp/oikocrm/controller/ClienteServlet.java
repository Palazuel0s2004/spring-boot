package com.snpp.oikocrm.controller;

import com.snpp.oikocrm.dao.ClienteDAO;
import com.snpp.oikocrm.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private final ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String buscar = request.getParameter("buscar");
        String orden = request.getParameter("orden");

        try {
            if (accion == null) {
                accion = "listar";
            }

            switch (accion) {
                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Cliente clienteEditar = dao.buscarPorId(idEditar);
                    request.setAttribute("clienteEditar", clienteEditar);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    break;

                default:
                    break;
            }

            List<Cliente> listaClientes;
            if (buscar != null && !buscar.trim().isEmpty()) {
                listaClientes = dao.buscarPorNombre(buscar);
            } else {
                listaClientes = dao.listar(orden);
            }

            request.setAttribute("listaClientes", listaClientes);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idCliente");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String empresa = request.getParameter("empresa");
        String direccion = request.getParameter("direccion");

        int idCliente = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        // Validación de email vacío o duplicado
        try {
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("error", "El correo electrónico no puede estar vacío.");
            } else if (dao.emailExiste(email, idCliente)) {
                request.setAttribute("error", "El correo electrónico ya está registrado por otro cliente.");
            }

            if (request.getAttribute("error") != null) {
                // Si hay error, recuperamos los datos para mantener el formulario o la edición
                if (idCliente > 0) {
                    request.setAttribute("clienteEditar", dao.buscarPorId(idCliente));
                }
                request.setAttribute("listaClientes", dao.listar(null));
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            Cliente cliente = new Cliente(nombre, email, telefono, empresa, direccion);

            if (idCliente > 0) {
                cliente.setIdCliente(idCliente);
                dao.actualizar(cliente);
            } else {
                dao.insertar(cliente);
            }
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }

        response.sendRedirect("ClienteServlet");
    }
}

// Actúa como el intermediario inteligente; recibe las acciones del usuario, invoca los métodos del DAO para procesar la lógica de
// negocio y decide qué vista o redirección mostrar a continuación.