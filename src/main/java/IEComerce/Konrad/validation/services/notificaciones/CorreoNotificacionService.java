package IEComerce.Konrad.validation.services.notificaciones;

import IEComerce.Konrad.validation.models.SolicitudVendedor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CorreoNotificacionService {

    public void notificarResultado(SolicitudVendedor solicitud) {
        String mensaje = String.format("""
            ----------------------------------------
            [NOTIFICACIÓN SIMULADA]
            Para: %s
            Nombre: %s %s
            Estado: %s
            Motivo: %s
            ----------------------------------------
            """,
                solicitud.getCorreoElectronico(),
                solicitud.getNombres(),
                solicitud.getApellidos(),
                solicitud.getEstado().name(),
                solicitud.getMotivoResultado()
        );

        // Simula envío del correo mostrando el contenido
        log.info(mensaje);
    }
}