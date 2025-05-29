package IEComerce.Konrad.validation.services.adapters;

import IEComerce.Konrad.validation.models.enums.CalificacionCrediticia;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.ports.ServicioExternoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DatacreditoAdapter implements IServicioExterno<CalificacionCrediticia> {

    @Value("${servicios.externos.datacredito.url}")
    private String urlBase;

    @Override
    public CalificacionCrediticia consultar(String numeroIdentificacion) throws ServicioExternoException {
        try {
            String endpoint = urlBase + "/calificacion/" + numeroIdentificacion;
            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            con.setRequestProperty("Accept", "application/json");

            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new ServicioExternoException("Respuesta HTTP inválida: " + status);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();
            con.disconnect();

            // Se espera que la API devuelva un JSON simple como "ALTA", "BAJA", etc.
            String resultado = respuesta.toString().replace("\"", "").trim();
            return CalificacionCrediticia.valueOf(resultado.toUpperCase());

        } catch (Exception e) {
            throw new ServicioExternoException("Error al consultar Datacrédito", e);
        }
    }
}
