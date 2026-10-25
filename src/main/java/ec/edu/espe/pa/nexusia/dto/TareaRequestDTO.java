package ec.edu.espe.pa.nexusia.dto;

import ec.edu.espe.pa.nexusia.entity.EstadoTarea;
import ec.edu.espe.pa.nexusia.entity.Prioridad;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TareaRequestDTO(

        @NotBlank(message = "El código es obligatorio")
        @Size(max = 30, message = "El código no puede superar 30 caracteres")
        String codigo,

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede superar 150 caracteres")
        String titulo,

        @Size(max = 1000, message = "La descripción no puede superar 1000 caracteres")
        String descripcion,

        @NotNull(message = "Las horas estimadas son obligatorias")
        @DecimalMin(value = "0.01", message = "Las horas deben ser mayores a cero")
        @Digits(integer = 6, fraction = 2,
                message = "Las horas deben tener máximo 6 enteros y 2 decimales")
        BigDecimal horasEstimadas,

        @NotNull(message = "La prioridad es obligatoria")
        Prioridad prioridad,

        @NotNull(message = "El estado es obligatorio")
        EstadoTarea estado,

        @NotNull(message = "La fecha de entrega es obligatoria")
        @FutureOrPresent(message = "La fecha de entrega no puede ser anterior a hoy")
        LocalDate fechaEntrega,

        @NotNull(message = "Debe indicar si la tarea está completada")
        Boolean completada
) {
}
