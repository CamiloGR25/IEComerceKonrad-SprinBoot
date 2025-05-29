package IEComerce.Konrad.validation.facade;

import IEComerce.Konrad.validation.ports.ServicioExternoException;
import IEComerce.Konrad.validation.repository.SolicitudRepository;
import IEComerce.Konrad.validation.factory.ServicioExternoFactory;
import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.models.enums.EstadoJudicial;
import IEComerce.Konrad.validation.models.enums.TipoServicio;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.services.notificaciones.CorreoNotificacionService;
import org.springframework.stereotype.Service;

@Service
public class ValidacionFacade {

    private final ServicioExternoFactory factory;
    private final SolicitudRepository solicitudRepository;
    private final CorreoNotificacionService notificacionService;

    public ValidacionFacade(
            ServicioExternoFactory factory,
            SolicitudRepository solicitudRepository,
            CorreoNotificacionService notificacionService
    ) {
        this.factory = factory;
        this.solicitudRepository = solicitudRepository;
        this.notificacionService = notificacionService;
    }

    public void validarSolicitud(Long idSolicitud) {
        SolicitudVendedor solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));

        try {
            // DATACRÉDITO
            IServicioExterno<CalificacionCrediticia> datacredito =
                    (IServicioExterno<CalificacionCrediticia>) factory.crearServicio(TipoServicio.DATACREDITO);
            CalificacionCrediticia califDC = datacredito.consultar(solicitud.getNumeroIdentificacion());

            // CIFIN
            IServicioExterno<CalificacionCrediticia> cifin =
                    (IServicioExterno<CalificacionCrediticia>) factory.crearServicio(TipoServicio.CIFIN);
            CalificacionCrediticia califCifin = cifin.consultar(solicitud.getNumeroIdentificacion());

            // POLICÍA
            IServicioExterno<EstadoJudicial> policia =
                    (IServicioExterno<EstadoJudicial>) factory.crearServicio(TipoServicio.POLICIA);
            EstadoJudicial estadoJudicial = policia.consultar(solicitud.getNumeroIdentificacion());

            // Evaluar y actualizar solicitud
            solicitud.evaluar(califDC, califCifin, estadoJudicial);
            solicitudRepository.save(solicitud);

            // Notificar al solicitante
            notificacionService.notificarResultado(solicitud);

        } catch (ServicioExternoException e) {
            throw new RuntimeException("Error al consultar servicios externos: " + e.getMessage(), e);
        }
    }

}