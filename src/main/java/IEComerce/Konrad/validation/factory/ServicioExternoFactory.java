package IEComerce.Konrad.validation.factory;

import IEComerce.Konrad.validation.models.enums.TipoServicio;
import IEComerce.Konrad.validation.ports.IServicioExterno;
import IEComerce.Konrad.validation.services.adapters.CIFINAdapter;
import IEComerce.Konrad.validation.services.adapters.DatacreditoAdapter;
import IEComerce.Konrad.validation.services.adapters.PoliciaAdapter;

public class ServicioExternoFactory {

    private static volatile ServicioExternoFactory instance;

    private ServicioExternoFactory() {
        // Constructor privado (Singleton)
    }

    public static ServicioExternoFactory getInstance() {
        if (instance == null) {
            synchronized (ServicioExternoFactory.class) {
                if (instance == null) {
                    instance = new ServicioExternoFactory();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> IServicioExterno<T> crearServicio(TipoServicio tipoServicio) {
        return switch (tipoServicio) {
            case DATACREDITO -> (IServicioExterno<T>) new DatacreditoAdapter();
            case CIFIN       -> (IServicioExterno<T>) new CIFINAdapter();
            case POLICIA     -> (IServicioExterno<T>) new PoliciaAdapter();
        };
    }
}