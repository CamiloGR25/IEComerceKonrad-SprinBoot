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
        baseLocalCifin.put("1193515464", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("2020202020", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("3030303030", CalificacionCrediticia.BAJA);
        baseLocalCifin.put("3670828672", CalificacionCrediticia.BAJA);
        baseLocalCifin.put("7462202133", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("7879063937", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("6339101269", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("2199477928", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("8342521856", CalificacionCrediticia.BAJA);
        baseLocalCifin.put("5567631024", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("1907339641", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("3482522914", CalificacionCrediticia.BAJA);
        baseLocalCifin.put("4621953326", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("9357904285", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("3756812951", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("8486432031", CalificacionCrediticia.BAJA);
        baseLocalCifin.put("7328695552", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("6517467338", CalificacionCrediticia.ALTA);
        baseLocalCifin.put("2712526094", CalificacionCrediticia.ADVERTENCIA);
        baseLocalCifin.put("8926374915", CalificacionCrediticia.ALTA);
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
