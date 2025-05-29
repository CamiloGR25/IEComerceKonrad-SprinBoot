package IEComerce.Konrad.validation.controller;

import IEComerce.Konrad.validation.facade.ValidacionFacade;
import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;
import IEComerce.Konrad.validation.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ValidacionController {

    private final SolicitudRepository solicitudRepository;
    private final ValidacionFacade validacionFacade;

    /**
     * Lista todas las solicitudes en el estado indicado.
     */
    public List<SolicitudVendedor> listarSolicitudesPorEstado(EstadoSolicitud estado) {
        return solicitudRepository.findByEstado(estado);
    }

    /**
     * Retorna el detalle completo de una solicitud por ID.
     */
    public Optional<SolicitudVendedor> obtenerDetalleSolicitud(Long id) {
        return solicitudRepository.findById(id);
    }

    /**
     * Ejecuta el proceso de validación local sin llamadas a red.
     */
    public void ejecutarValidacion(Long id) {
        validacionFacade.validarSolicitud(id);
    }
}