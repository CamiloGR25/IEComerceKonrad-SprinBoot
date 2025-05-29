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
     * Cargar datos iniciales para prueba.
     */
    public void cargarDatosIniciales() {
        save(new SolicitudVendedor(null, "1010101010", "Torres", "Andrés", "andres@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(null, "2020202020", "López", "Camila", "camila@example.com", EstadoSolicitud.PENDIENTE, null));
        save(new SolicitudVendedor(null, "3030303030", "Martínez", "Julián", "julian@example.com", EstadoSolicitud.PENDIENTE, null));
    }
}
