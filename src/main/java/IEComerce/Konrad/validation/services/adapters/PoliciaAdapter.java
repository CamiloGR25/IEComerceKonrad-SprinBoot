package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.EstadoJudicial;
import IEComerce.Konrad.validation.ports.IServicioExterno;

import java.util.Random;

public class PoliciaAdapter implements IServicioExterno<EstadoJudicial> {

    @Override
    public EstadoJudicial consultar(String identificacion) {
        // Simulación de consulta al sitio web de la Policía Nacional
        System.out.println("Consultando antecedentes judiciales en Policía para: " + identificacion);

        // Mock de respuesta
        boolean tieneOrden = new Random().nextBoolean();
        return tieneOrden ? EstadoJudicial.REQUERIDO : EstadoJudicial.NO_REQUERIDO;
    }
}