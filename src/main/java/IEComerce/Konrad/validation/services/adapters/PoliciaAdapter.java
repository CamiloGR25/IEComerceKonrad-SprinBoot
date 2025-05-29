package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.EstadoJudicial;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.ports.ServicioExternoException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PoliciaAdapter implements IServicioExterno<EstadoJudicial> {

    // Simulación de base local con resultados judiciales
    private static final Map<String, EstadoJudicial> baseLocalPolicia = new ConcurrentHashMap<>();

    static {
        baseLocalPolicia.put("1010101010", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("2020202020", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("3030303030", EstadoJudicial.REQUERIDO);
    }

    @Override
    public EstadoJudicial consultar(String numeroIdentificacion) throws ServicioExternoException {
        EstadoJudicial resultado = baseLocalPolicia.get(numeroIdentificacion);
        if (resultado == null) {
            throw new ServicioExternoException("No se encontraron antecedentes judiciales para: " + numeroIdentificacion);
        }
        return resultado;
    }
}