package ec.edu.espe.pa.nexusia.service;

import ec.edu.espe.pa.nexusia.dto.TareaRequestDTO;
import ec.edu.espe.pa.nexusia.dto.TareaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TareaService {

    Page<TareaResponseDTO> listar(Pageable pageable);

    TareaResponseDTO obtenerPorId(Long id);

    Page<TareaResponseDTO> buscarPorTitulo(
            String titulo,
            Pageable pageable
    );

    TareaResponseDTO crear(TareaRequestDTO dto);

    TareaResponseDTO actualizar(Long id, TareaRequestDTO dto);

    void eliminar(Long id);
}
