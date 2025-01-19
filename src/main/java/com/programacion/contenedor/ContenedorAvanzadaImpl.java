package com.programacion.contenedor;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.contenedor.anotaciones.MiDependencia;
import com.programacion.contenedor.proxy.ComponenteProxyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class ContenedorAvanzadaImpl implements ContenedorAvanzada {

    private String pakageScan = "com.programacion";

    protected Map<String, Object> componentesRegistrados = new HashMap<>();

    private static Logger _logger = LoggerFactory.getLogger(ContenedorAvanzadaImpl.class);
    @Override
    public void inicializar() {

        _logger.info("Inicializando");

        try {
            //1.Buscar las clases en el programa
            ClassPath classPath = ClassPath.from(ContenedorAvanzadaImpl.class.getClassLoader());
            ImmutableSet<ClassPath.ClassInfo> clases = classPath.getTopLevelClassesRecursive(pakageScan);

            clases.stream()
                    .forEach(s-> {
                        String className = s.getName();
                        Class<?> clase = null;
                        //Imprime el nombre de cada clase
                        //System.out.println("***********" + className);

                        try{
                            clase= Class.forName(className);

                            //2. Identificar los que tienen una anotacion en particualar

                            MiComponente ann = clase.getAnnotation(MiComponente.class);

                            if(ann != null){
                                //la clase tiene la anotacion
                                _logger.info("registrando {}===>{}\n", className, ann.nombre());

                                //3. Registrarlas como componentes
                                crearComponente(ann.nombre(), clase);

                                //4. Procesar Dependencias
                                procesarDependencias();

                            }
                        }
                        catch (Exception e){
                            throw new RuntimeException(e.getMessage());
                        }

                    });

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void destruir() {
        _logger.info("Destruyendo");
    }

    //Metodo que permite casteo automatico de tipos de datos
    @Override
    public <T>T buscar(String nombre, Class<T> cls) {

        Object obj = componentesRegistrados.get(nombre);
        if (obj == null) {
            throw new RuntimeException(String.format("No se encontro el componente %s.", nombre));
        }
        return cls.cast(obj);
    }

    void crearComponente(String nombre, Class<?> cls) throws Exception {
        //Object obj = clase.getDeclaredConstructors()[0].newInstance();
        //componentesRegistrados.put(nombre, obj);

        Object target = cls.getDeclaredConstructors()[0].newInstance();

        ComponenteProxyHandler handler = new ComponenteProxyHandler(target);

        Object proxy = Proxy.newProxyInstance(ContenedorAvanzadaImpl.class.getClassLoader(), cls.getInterfaces(), handler);

        componentesRegistrados.put(nombre, proxy);

    }



    void procesarDependencias(){
        System.out.println("Procesando dependencias");
        //1. Listar componentes registrados
        for (String key : componentesRegistrados.keySet()) {
            System.out.printf("Procesando dependencias para %s\n", key);

            Object proxy = componentesRegistrados.get(key);
            ComponenteProxyHandler handler = (ComponenteProxyHandler) Proxy.getInvocationHandler(proxy);

            Object obj = handler.getTarget();

            //Listar variables de instancia
            Field[] fields = obj.getClass().getDeclaredFields(); ;

            Stream.of(fields)
                    .forEach(f -> {
                        System.out.println("*********" +f.getName());

                        MiDependencia ann = f.getAnnotation(MiDependencia.class);

                        if(ann != null){
                            System.out.printf("%s.%s======>%s\n", obj.getClass().getName(), f.getName(), ann.nombre());

                            Object dependenciaObj = componentesRegistrados.get(ann.nombre());

                            try{
                                f.setAccessible(true);
                                f.set(obj, dependenciaObj);
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    });
        }
    }
}
