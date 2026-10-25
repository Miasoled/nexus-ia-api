package ec.edu.espe.pa.nexusia.repository;

import ec.edu.espe.pa.nexusia.entity.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    Optional<Tarea> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    Page<Tarea> findByTituloContainingIgnoreCase(
            String titulo,
            Pageable pageable
    );
}
