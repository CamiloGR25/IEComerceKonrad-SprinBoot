package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.ports.ServicioExternoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CifinAdapter implements IServicioExterno<CalificacionCrediticia> {

    // Simula la base de datos local cargada desde archivo plano
    private static final Map<String, CalificacionCrediticia> baseLocalCifin = new ConcurrentHashMap<>();

    static {
        // Datos simulados
        baseLocalCifin.put("1010101010", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("2020202020", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("3030303030", CalificacionCrediticia.BAJA);
    }

    @Override
    public CalificacionCrediticia consultar(String numeroIdentificacion) throws ServicioExternoException {
        CalificacionCrediticia resultado = baseLocalCifin.get(numeroIdentificacion);
        if (resultado == null) {
            throw new ServicioExternoException("No se encontró información crediticia CIFIN para: " + numeroIdentificacion);
        }
        return resultado;
    }
}
