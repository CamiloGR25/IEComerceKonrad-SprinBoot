package IEComerce.Konrad.validation.factory;

import IEComerce.Konrad.validation.models.enums.TipoServicio;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.services.adapters.CifinAdapter;
import IEComerce.Konrad.validation.services.adapters.DatacreditoAdapter;
import IEComerce.Konrad.validation.services.adapters.PoliciaAdapter;
import org.springframework.stereotype.Component;

@Component // Spring lo maneja como Singleton por defecto
public class ServicioExternoFactory {

    /**
     * Metodo factory para obtener una implementación concreta del servicio externo.
     *
     * @param tipo de servicio a consultar
     * @return instancia del adaptador correspondiente
     */
    public IServicioExterno<?> crearServicio(TipoServicio tipo) {
        return switch (tipo) {
            case DATACREDITO -> new DatacreditoAdapter();
            case CIFIN -> new CifinAdapter();
            case POLICIA -> new PoliciaAdapter();
        };
    }
}