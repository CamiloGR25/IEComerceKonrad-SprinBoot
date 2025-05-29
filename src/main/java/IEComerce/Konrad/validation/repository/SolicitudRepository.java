package IEComerce.Konrad.validation.repository;

import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SolicitudRepository {

    private final Map<Long, SolicitudVendedor> baseDatos = new ConcurrentHashMap<>();
    private long secuenciaId = 1;

    /**
     * Guardar o actualizar una solicitud.
     */
    public SolicitudVendedor save(SolicitudVendedor solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(secuenciaId++);
        }
        baseDatos.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    /**
     * Buscar por ID.
     */
    public Optional<SolicitudVendedor> findById(Long id) {
        return Optional.ofNullable(baseDatos.get(id));
    }

    /**
     * Buscar por estado.
     */
    public List<SolicitudVendedor> findByEstado(EstadoSolicitud estado) {
        return baseDatos.values().stream()
                .filter(s -> s.getEstado() == estado)
                .collect(Collectors.toList());
    }

    /**
     * Buscar por Numero de idenificación.
     */
    public List<SolicitudVendedor> findAll() {
        return new ArrayList<>(baseDatos.values());
    }


    /**
     * Cargar datos iniciales para prueba.
     */
    public void cargarDatosIniciales() {
        save(new SolicitudVendedor(1L, "3670828672", "Pérez", "Isabella", "isabella.pérez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(2L, "7462202133", "López", "Daniela", "daniela.lópez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(3L, "7879063937", "Jiménez", "Sebastián", "sebastián.jiménez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(4L, "6339101269", "Rodríguez", "Julián", "julián.rodríguez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(5L, "2199477928", "López", "Mariana", "mariana.lópez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(6L, "8342521856", "Castro", "Camila", "camila.castro@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(7L, "5567631024", "Ríos", "Mateo", "mateo.ríos@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(8L, "1907339641", "Pérez", "Valeria", "valeria.pérez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(9L, "1193515464", "Torres", "Andrés", "andres@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(10L, "2020202020", "López", "Camila", "camila@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(11L, "3030303030", "Martínez", "Julián", "julian@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(12L, "3482522914", "Gómez", "Sebastián", "sebastián.gómez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(13L, "4621953326", "Rodríguez", "Daniela", "daniela.rodríguez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(14L, "9357904285", "Moreno", "Isabella", "isabella.moreno@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(15L, "3756812951", "Gómez", "Santiago", "santiago.gómez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(16L, "8486432031", "Jiménez", "Mariana", "mariana.jiménez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(17L, "7328695552", "Castro", "Mateo", "mateo.castro@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(18L, "6517467338", "Ríos", "Valeria", "valeria.ríos@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(19L, "2712526094", "Pérez", "Camila", "camila.pérez@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(20L, "8926374915", "Moreno", "Santiago", "santiago.moreno@example.com", EstadoSolicitud.PENDIENTE, null));
    }
}
