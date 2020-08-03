package com.javasampleapproach.jdbcpostgresql.model;

public class facturaDao {
    private int id_factura;
    private String fecha_factura;
    private double precio_factura;
    private String nombre_producto;
    private int cantidad_producto;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public double getPrecio_factura() {
        return precio_factura;
    }

    public void setPrecio_factura(double precio_factura) {
        this.precio_factura = precio_factura;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }
}
