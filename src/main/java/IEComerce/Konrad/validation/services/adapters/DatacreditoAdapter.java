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
        baseLocalDatacredito.put("1193515464", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("2020202020", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("3030303030", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("3670828672", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("7462202133", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("7879063937", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("6339101269", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("2199477928", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("8342521856", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("5567631024", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("1907339641", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("3482522914", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("4621953326", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("9357904285", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("3756812951", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("8486432031", CalificacionCrediticia.ALTA);
        baseLocalDatacredito.put("7328695552", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("6517467338", CalificacionCrediticia.ADVERTENCIA);
        baseLocalDatacredito.put("2712526094", CalificacionCrediticia.BAJA);
        baseLocalDatacredito.put("8926374915", CalificacionCrediticia.ALTA);
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
