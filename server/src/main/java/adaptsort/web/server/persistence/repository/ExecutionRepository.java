package adaptsort.web.server.persistence.repository;

import adaptsort.web.server.persistence.entity.Execution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    Page<Execution> findAll(Pageable pageable);
}
