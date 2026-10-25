
package ec.edu.espe.pa.nexusia.dto;

import ec.edu.espe.pa.nexusia.entity.EstadoTarea;
import ec.edu.espe.pa.nexusia.entity.Prioridad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TareaResponseDTO(
        Long id,
        String codigo,
        String titulo,
        String descripcion,
        BigDecimal horasEstimadas,
        Prioridad prioridad,
        EstadoTarea estado,
        LocalDate fechaEntrega,
        Boolean completada,
        LocalDateTime creadoEn,
        LocalDateTime actualizadoEn
) {
}
