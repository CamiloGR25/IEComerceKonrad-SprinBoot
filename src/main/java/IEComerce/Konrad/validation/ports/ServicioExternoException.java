package IEComerce.Konrad.validation.ports;

public class ServicioExternoException extends Exception{

    public ServicioExternoException(String mensaje) {
        super(mensaje);
    }

    public ServicioExternoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
