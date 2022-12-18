package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.TestimonialsEntity;

@Repository
public interface TestimonialsRepository extends JpaRepository<TestimonialsEntity, Integer> {
  List<TestimonialsEntity> findAllByLangId(int langId);
}
