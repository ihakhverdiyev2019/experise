package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.ScholarshipEntity;

@Repository
public interface ScholarshipRepository extends JpaRepository<ScholarshipEntity, Integer> {
  ScholarshipEntity findByIdAndLangId(int id, int langId);

  List<ScholarshipEntity> findAllByLangId(int langId);

  ScholarshipEntity findByLangIdAndReferId(int langId, int referID);

  List<ScholarshipEntity> findAllByReferId(int referId);
}
