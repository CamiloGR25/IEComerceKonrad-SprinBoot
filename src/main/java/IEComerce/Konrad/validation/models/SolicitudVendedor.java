package IEComerce.Konrad.validation.models;

import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.models.enums.EstadoJudicial;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudVendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroIdentificacion;

    private String nombres;

    private String apellidos;

    private String correoElectronico;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private String motivoResultado; // Justificación para el correo

    public void evaluar(CalificacionCrediticia datacredito, CalificacionCrediticia cifin, EstadoJudicial judicial) {
        if (datacredito == CalificacionCrediticia.BAJA || cifin == CalificacionCrediticia.BAJA || judicial == EstadoJudicial.REQUERIDO) {
            this.estado = EstadoSolicitud.RECHAZADA;
            this.motivoResultado = "Rechazada por historial negativo o antecedentes judiciales.";
        } else if (datacredito == CalificacionCrediticia.ADVERTENCIA || cifin == CalificacionCrediticia.ADVERTENCIA) {
            this.estado = EstadoSolicitud.DEVUELTA;
            this.motivoResultado = "Advertencia en calificación crediticia. Debe ponerse al día.";
        } else {
            this.estado = EstadoSolicitud.APROBADA;
            this.motivoResultado = "Solicitud aprobada. Cumple con todos los requisitos.";
        }
    }
}