# Contenedor Avanzado en Java

Este repositorio contiene un proyecto desarrollado como parte de una clase universitaria, cuyo objetivo es explicar el funcionamiento de un contenedor avanzado en Java. El contenedor actúa como un sistema de inyección de dependencias personalizado y sirve como base para entender conceptos clave de programación modular y diseño avanzado en Java.

## Estructura del Proyecto

El proyecto sigue la siguiente estructura de paquetes y clases:

```
src
└── main
    └── java
        └── com.programacion
            └── contenedor
                ├── anotaciones
                │   ├── MiComponente
                │   └── MiDependencia
                ├── proxy
                │   ├── ComponenteProxyHandler
                │   ├── ContenedorAvanzada
                │   ├── ContenedorAvanzadaImpl
                │   └── ContenedorFactory
                └── servicios
                    ├── CuentaBancaria
                    ├── ManejadorPersistencia
                    ├── ManejadorPersistenciaImpl
                    ├── TransaccionBancaria
                    └── TransaccionBancariaImpl
```

### Descripción de los paquetes

- **`anotaciones`**: Contiene las anotaciones personalizadas utilizadas para marcar componentes e inyecciones de dependencias, como:
  - `MiComponente`: Anotación para marcar una clase como un componente administrado por el contenedor.
  - `MiDependencia`: Anotación para marcar dependencias inyectables.

- **`proxy`**: Implementa la lógica principal del contenedor avanzado, incluyendo:
  - `ComponenteProxyHandler`: Manejador de proxy dinámico para interceptar métodos y aplicar lógica adicional.
  - `ContenedorAvanzada`: Interfaz que define el contrato del contenedor.
  - `ContenedorAvanzadaImpl`: Implementación del contenedor avanzado.
  - `ContenedorFactory`: Fábrica para crear instancias del contenedor.

- **`servicios`**: Contiene clases de ejemplo que simulan un sistema bancario básico:
  - `CuentaBancaria`: Representa una cuenta bancaria.
  - `ManejadorPersistencia`: Interfaz para manejar persistencia.
  - `ManejadorPersistenciaImpl`: Implementación de la persistencia.
  - `TransaccionBancaria`: Interfaz para gestionar transacciones bancarias.
  - `TransaccionBancariaImpl`: Implementación de la lógica de transacciones bancarias.

## Requisitos

- **Java 17 o superior**
- **Gradle 7.0 o superior**

## Configuración

1. Clonar el repositorio:
   ```bash
   git clone <url_del_repositorio>
   cd avanzada01
   ```
2. Construir el proyecto con Gradle:
   ```bash
   gradle build
   ```
3. Ejecutar la clase principal:
   ```bash
   gradle run
   ```

## Uso

El proyecto demuestra cómo se puede utilizar un contenedor avanzado para inyectar dependencias y manejar la lógica de componentes. La clase principal `Principal` incluye ejemplos prácticos de su funcionamiento.

## Recursos

El archivo `log4j.properties` en la carpeta `resources` configura el sistema de logging para el proyecto.

## Contribuciones

Este proyecto es parte de un curso universitario y, por lo tanto, no está abierto para contribuciones externas.

## Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulte el archivo `LICENSE` para obtener más detalles.

---

**Autor:**
Clase de Programación Avanzada - Universidad

