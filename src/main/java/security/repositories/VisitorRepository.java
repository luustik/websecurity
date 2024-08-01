package security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import security.models.Visitor;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByRole(String role);
    List<Visitor> findByVisitorname(String visitorname);
}
