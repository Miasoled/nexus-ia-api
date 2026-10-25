package ec.edu.espe.pa.nexusia.mapper;

import ec.edu.espe.pa.nexusia.dto.TareaRequestDTO;
import ec.edu.espe.pa.nexusia.dto.TareaResponseDTO;
import ec.edu.espe.pa.nexusia.entity.Tarea;
import org.springframework.stereotype.Component;

@Component
public class TareaMapper {

    public Tarea toEntity(TareaRequestDTO dto) {
        Tarea tarea = new Tarea();
        updateEntity(tarea, dto);
        return tarea;
    }

    public void updateEntity(Tarea tarea, TareaRequestDTO dto) {
        tarea.setCodigo(dto.codigo());
        tarea.setTitulo(dto.titulo());
        tarea.setDescripcion(dto.descripcion());
        tarea.setHorasEstimadas(dto.horasEstimadas());
        tarea.setPrioridad(dto.prioridad());
        tarea.setEstado(dto.estado());
        tarea.setFechaEntrega(dto.fechaEntrega());
        tarea.setCompletada(dto.completada());
    }

    public TareaResponseDTO toResponse(Tarea tarea) {
        return new TareaResponseDTO(
                tarea.getId(),
                tarea.getCodigo(),
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getHorasEstimadas(),
                tarea.getPrioridad(),
                tarea.getEstado(),
                tarea.getFechaEntrega(),
                tarea.getCompletada(),
                tarea.getCreadoEn(),
                tarea.getActualizadoEn()
        );
    }
}
