package IEComerce.Konrad.validation.ports;


public interface IServicioExterno<T> {
    T consultar(String numeroIdentificacion) throws ServicioExternoException;
}