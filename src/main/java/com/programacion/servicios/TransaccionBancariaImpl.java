package com.programacion.servicios;

import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.contenedor.anotaciones.MiDependencia;

@MiComponente(nombre = "transaccionBancaria")
public class TransaccionBancariaImpl implements TransaccionBancaria {

    @MiDependencia(nombre = "manejadorPersistencia")
    protected ManejadorPersistencia mp;

    @Override
    public void realizarTtransferencia(String cuenta1, String cuenta2, float valor) {

        CuentaBancaria c1 = mp.buscarCuenta(cuenta1);
        CuentaBancaria c2 = mp.buscarCuenta(cuenta2);

        c1.deposito(valor);
        c2.retiro(valor);

    }
}
