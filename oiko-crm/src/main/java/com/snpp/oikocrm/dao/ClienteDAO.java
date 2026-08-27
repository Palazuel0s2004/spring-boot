package com.snpp.oikocrm.dao;

import com.snpp.oikocrm.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/oikocrm?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "12345678";

    private Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver de MySQL no encontrado.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    public List<Cliente> listar(String orden) throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        // Permite ordenar por nombre alfabéticamente o por ID (fecha de registro descendente)
        String sql = "SELECT * FROM clientes ORDER BY " + ("nombre".equals(orden) ? "nombre ASC" : "id_cliente DESC");

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("empresa"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro") != null ? rs.getDate("fecha_registro").toLocalDate() : null
                );
                lista.add(c);
            }
        }
        return lista;
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("empresa"),
                            rs.getString("direccion"),
                            rs.getDate("fecha_registro").toLocalDate()
                    );
                }
            }
        }
        return null;
    }

    public List<Cliente> buscarPorNombre(String nombre) throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ? ORDER BY id_cliente DESC";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("empresa"),
                            rs.getString("direccion"),
                            rs.getDate("fecha_registro").toLocalDate()
                    ));
                }
            }
        }
        return lista;
    }
    
    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, email, telefono, empresa, direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmpresa());
            ps.setString(5, c.getDireccion());
            ps.executeUpdate();
        }
    }
    
    public boolean emailExiste(String email, int idExcluir) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE email = ? AND id_cliente != ?";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setInt(2, idExcluir); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }   

    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ?, empresa = ?, direccion = ? " +
                     "WHERE id_cliente = ?";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmpresa());
            ps.setString(5, c.getDireccion());
            ps.setInt(6, c.getIdCliente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

// El ClienteDAO se encarga exclusivamente de conectar con la base de datos y ejecutar las consultas SQL para guardar, buscar,
// actualizar o eliminar los clientes