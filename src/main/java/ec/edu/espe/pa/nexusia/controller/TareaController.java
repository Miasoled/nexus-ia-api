
package ec.edu.espe.pa.nexusia.controller;

import ec.edu.espe.pa.nexusia.dto.TareaRequestDTO;
import ec.edu.espe.pa.nexusia.dto.TareaResponseDTO;
import ec.edu.espe.pa.nexusia.service.TareaService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TareaResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public TareaResponseDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/buscar")
    public Page<TareaResponseDTO> buscarPorTitulo(
            @RequestParam String titulo,
            Pageable pageable
    ) {
        return service.buscarPorTitulo(titulo, pageable);
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crear(
            @Valid @RequestBody TareaRequestDTO dto
    ) {
        TareaResponseDTO tarea = service.crear(dto);

        URI location = URI.create("/api/tareas/" + tarea.id());

        return ResponseEntity
                .created(location)
                .body(tarea);
    }

    @PutMapping("/{id}")
    public TareaResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TareaRequestDTO dto
    ) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
