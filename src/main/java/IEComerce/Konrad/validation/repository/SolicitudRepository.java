package IEComerce.Konrad.validation.repository;

import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudVendedor, Long> {

    /**
     * Consulta todas las solicitudes que están en un estado específico (por ejemplo: PENDIENTE).
     */
    List<SolicitudVendedor> findByEstado(EstadoSolicitud estado);

    /**
     * Búsqueda por número de identificación y estado.
     */
    List<SolicitudVendedor> findByNumeroIdentificacionAndEstado(String numeroIdentificacion, EstadoSolicitud estado);

    /**
     * Filtro por rango de fechas (requiere un campo de fecha en SolicitudVendedor si se necesita)
     */
    // List<SolicitudVendedor> findByFechaSolicitudBetween(LocalDate inicio, LocalDate fin);
}