package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.ports.IServicioExterno;

import java.util.Random;

public class DatacreditoAdapter implements IServicioExterno<CalificacionCrediticia> {

    @Override
    public CalificacionCrediticia consultar(String identificacion) {
        // Simulación de lógica de consulta REST a Datacrédito
        System.out.println("Consultando Datacrédito para cédula: " + identificacion);

        // Mock de la respuesta
        int valor = new Random().nextInt(3);
        return switch (valor) {
            case 0 -> CalificacionCrediticia.ALTA;
            case 1 -> CalificacionCrediticia.BAJA;
            default -> CalificacionCrediticia.ADVERTENCIA;
        };
    }
}
