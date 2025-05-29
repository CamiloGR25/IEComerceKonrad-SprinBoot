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
        baseLocalPolicia.put("1193515464", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("2020202020", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("3030303030", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("3670828672", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("7462202133", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("7879063937", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("6339101269", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("2199477928", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("8342521856", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("5567631024", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("1907339641", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("3482522914", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("4621953326", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("9357904285", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("3756812951", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("8486432031", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("7328695552", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("6517467338", EstadoJudicial.NO_REQUERIDO);
        baseLocalPolicia.put("2712526094", EstadoJudicial.REQUERIDO);
        baseLocalPolicia.put("8926374915", EstadoJudicial.NO_REQUERIDO);
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