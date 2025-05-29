package IEComerce.Konrad;

import IEComerce.Konrad.validation.controller.ValidacionController;
import IEComerce.Konrad.validation.facade.ValidacionFacade;
import IEComerce.Konrad.validation.factory.ServicioExternoFactory;
import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;
import IEComerce.Konrad.validation.repository.SolicitudRepository;
import IEComerce.Konrad.validation.services.notificaciones.CorreoNotificacionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class KonradApplication{
	public static void main(String[] args) {
		SpringApplication.run(KonradApplication.class, args);
	}

	/**
	 * Inicializa datos y beans necesarios al arrancar.
	 */
	@Bean
	public CommandLineRunner init(SolicitudRepository solicitudRepository) {
		return args -> {
			solicitudRepository.cargarDatosIniciales();
			System.out.println("Sistema iniciado correctamente con datos en memoria.");
		};
	}

	/**
	 * Bean del repositorio en memoria.
	 */
	@Bean
	public SolicitudRepository solicitudRepository() {
		return new SolicitudRepository();
	}

	/**
	 * Bean de la fachada de validación.
	 */
	@Bean
	public ValidacionFacade validacionFacade(SolicitudRepository repo) {
		return new ValidacionFacade(
				new ServicioExternoFactory(),
				repo,
				new CorreoNotificacionService()
		);
	}

	/*
	public static void main(String[] args) {
		// Crear repositorio local en memoria
		SolicitudRepository solicitudRepository = new SolicitudRepository();
		solicitudRepository.cargarDatosIniciales();

		// Crear servicios
		ServicioExternoFactory factory = new ServicioExternoFactory();
		CorreoNotificacionService notificacionService = new CorreoNotificacionService();
		ValidacionFacade facade = new ValidacionFacade(factory, solicitudRepository, notificacionService);
		ValidacionController controller = new ValidacionController(solicitudRepository, facade);

		// Menú por consola
		Scanner scanner = new Scanner(System.in);
		System.out.println("=== Sistema de Validación de Solicitudes ===");

		while (true) {
			System.out.println("\nOpciones:");
			System.out.println("1. Listar solicitudes PENDIENTES");
			System.out.println("2. Ver detalle de solicitud");
			System.out.println("3. Validar solicitud");
			System.out.println("0. Salir");
			System.out.print("Seleccione una opción: ");

			int opcion = scanner.nextInt();

			switch (opcion) {
				case 1 -> {
					List<SolicitudVendedor> pendientes = controller.listarSolicitudesPorEstado(EstadoSolicitud.PENDIENTE);
					if (pendientes.isEmpty()) {
						System.out.println("No hay solicitudes pendientes.");
					} else {
						pendientes.forEach(s -> System.out.printf("ID: %d - %s %s - Estado: %s%n",
								s.getId(), s.getNombres(), s.getApellidos(), s.getEstado()));
					}
				}

				case 2 -> {
					System.out.print("Ingrese ID de la solicitud: ");
					Long id = scanner.nextLong();
					Optional<SolicitudVendedor> solicitud = controller.obtenerDetalleSolicitud(id);
					solicitud.ifPresentOrElse(
							s -> System.out.printf("Solicitud #%d - %s %s (%s)%nEstado: %s%nMotivo: %s%n",
									s.getId(), s.getNombres(), s.getApellidos(), s.getCorreoElectronico(),
									s.getEstado(), s.getMotivoResultado()),
							() -> System.out.println("Solicitud no encontrada.")
					);
				}

				case 3 -> {
					System.out.print("Ingrese ID de la solicitud a validar: ");
					Long id = scanner.nextLong();
					try {
						controller.ejecutarValidacion(id);
						System.out.println("Validación completada con éxito.");
					} catch (Exception e) {
						System.out.println("Error durante la validación: " + e.getMessage());
					}
				}

				case 0 -> {
					System.out.println("Saliendo del sistema.");
					return;
				}

				default -> System.out.println("Opción inválida.");
			}
		}
	}*/

}
