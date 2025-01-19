package com.programacion.servicios;

import com.programacion.contenedor.anotaciones.MiComponente;

@MiComponente(nombre = "manejadorPersistencia")
public class ManejadorPersistenciaImpl implements ManejadorPersistencia {

    @Override
    public CuentaBancaria buscarCuenta(String numero) {
        return new CuentaBancaria();
    }
}
