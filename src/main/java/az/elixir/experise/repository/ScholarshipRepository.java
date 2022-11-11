package az.elixir.experise.repository;

import az.elixir.experise.model.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRepository extends JpaRepository<ScholarshipEntity,Integer> {
}
