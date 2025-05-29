package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.ports.ServicioExternoException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DatacreditoAdapter implements IServicioExterno<CalificacionCrediticia> {

    // Simulación de base de datos embebida
    private static final Map<String, CalificacionCrediticia> baseLocalDatacredito = new ConcurrentHashMap<>();

    static {
        baseLocalDatacredito.put("1010101010", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("2020202020", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("3030303030", CalificacionCrediticia.BAJA);
    }

    @Override
    public CalificacionCrediticia consultar(String numeroIdentificacion) throws ServicioExternoException {
        CalificacionCrediticia resultado = baseLocalDatacredito.get(numeroIdentificacion);
        if (resultado == null) {
            throw new ServicioExternoException("No se encontró información en Datacrédito para: " + numeroIdentificacion);
        }
        return resultado;
    }
}
