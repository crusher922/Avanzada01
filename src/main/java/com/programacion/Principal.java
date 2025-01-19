package com.programacion;

import com.programacion.contenedor.ContenedorAvanzada;
import com.programacion.contenedor.ContenedorFactory;
import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.servicios.CuentaBancaria;
import com.programacion.servicios.ManejadorPersistencia;
import com.programacion.servicios.TransaccionBancaria;


public class Principal {
    public static void main(String[] args) {
        ContenedorAvanzada container = ContenedorFactory.newInstance();

        container.inicializar();

        TransaccionBancaria tb = container.buscar("transaccionBancaria", TransaccionBancaria.class);
        tb.realizarTtransferencia("001","002", 10);

        ManejadorPersistencia mp = container.buscar("manejadorPersistencia", ManejadorPersistencia.class);

        System.out.println(tb + " Soy transaccionBancaria invocado desde Principal");
        System.out.println(mp);

        container.destruir();
    }
}
