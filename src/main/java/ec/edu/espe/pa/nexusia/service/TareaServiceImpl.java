package ec.edu.espe.pa.nexusia.service;

import ec.edu.espe.pa.nexusia.dto.TareaRequestDTO;
import ec.edu.espe.pa.nexusia.dto.TareaResponseDTO;
import ec.edu.espe.pa.nexusia.entity.EstadoTarea;
import ec.edu.espe.pa.nexusia.entity.Tarea;
import ec.edu.espe.pa.nexusia.exception.RecursoDuplicadoException;
import ec.edu.espe.pa.nexusia.exception.RecursoNoEncontradoException;
import ec.edu.espe.pa.nexusia.exception.ReglaNegocioException;
import ec.edu.espe.pa.nexusia.mapper.TareaMapper;
import ec.edu.espe.pa.nexusia.repository.TareaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TareaServiceImpl implements TareaService {

    private final TareaRepository repository;
    private final TareaMapper mapper;

    public TareaServiceImpl(
            TareaRepository repository,
            TareaMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TareaResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public TareaResponseDTO obtenerPorId(Long id) {
        Tarea tarea = buscarEntidad(id);
        return mapper.toResponse(tarea);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TareaResponseDTO> buscarPorTitulo(
            String titulo,
            Pageable pageable
    ) {
        return repository.findByTituloContainingIgnoreCase(
                titulo, pageable
        ).map(mapper::toResponse);
    }

    @Override
    public TareaResponseDTO crear(TareaRequestDTO dto) {

        validarReglaNegocio(dto);

        if (repository.existsByCodigo(dto.codigo())) {
            throw new RecursoDuplicadoException(
                    "Ya existe una tarea con el código " + dto.codigo()
            );
        }

        Tarea tarea = mapper.toEntity(dto);
        Tarea guardada = repository.save(tarea);

        return mapper.toResponse(guardada);
    }

    @Override
    public TareaResponseDTO actualizar(
            Long id,
            TareaRequestDTO dto
    ) {
        Tarea tarea = buscarEntidad(id);

        validarReglaNegocio(dto);

        repository.findByCodigo(dto.codigo())
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new RecursoDuplicadoException(
                            "Ya existe una tarea con el código " + dto.codigo()
                    );
                });

        mapper.updateEntity(tarea, dto);

        Tarea actualizada = repository.save(tarea);
        return mapper.toResponse(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        Tarea tarea = buscarEntidad(id);
        repository.delete(tarea);
    }

    private Tarea buscarEntidad(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No existe una tarea con ID " + id
                        )
                );
    }

    private void validarReglaNegocio(TareaRequestDTO dto) {

        boolean estadoCompletado =
                dto.estado() == EstadoTarea.COMPLETADA;

        if (estadoCompletado != Boolean.TRUE.equals(dto.completada())) {
            throw new ReglaNegocioException(
                    "El estado COMPLETADA debe coincidir con el valor de completada"
            );
        }
    }
}
