package IEComerce.Konrad.validation.controller;


import IEComerce.Konrad.validation.facade.ValidacionFacade;
import IEComerce.Konrad.validation.models.SolicitudVendedor;
import IEComerce.Konrad.validation.models.enums.EstadoSolicitud;
import IEComerce.Konrad.validation.repository.SolicitudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudRestController {

    private final SolicitudRepository repository;
    private final ValidacionFacade facade;

    public SolicitudRestController(SolicitudRepository repository, ValidacionFacade facade) {
        this.repository = repository;
        this.facade = facade;
    }

    @GetMapping("/pendientes")
    public List<SolicitudVendedor> listarPendientes() {
        return repository.findByEstado(EstadoSolicitud.PENDIENTE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudVendedor> obtenerDetalle(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/validar")
    public ResponseEntity<String> validarSolicitud(@PathVariable Long id) {
        try {
            facade.validarSolicitud(id);
            return ResponseEntity.ok("Validación completada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/identificacion/{numero}")
    public ResponseEntity<SolicitudVendedor> buscarPorIdentificacion(@PathVariable String numero) {
        return repository.findAll().stream()
                .filter(s -> s.getNumeroIdentificacion().equals(numero))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<SolicitudVendedor> listarPorEstado(@PathVariable EstadoSolicitud estado) {
        return repository.findByEstado(estado);
    }


}
