package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.DegreesAndCredentialsEntity;

@Repository
public interface DegreesAndCredentialsRepository
    extends JpaRepository<DegreesAndCredentialsEntity, Integer> {
  DegreesAndCredentialsEntity findByIdAndLangId(int id, int langId);

  List<DegreesAndCredentialsEntity> findAllByCategoryAndLangId(String category, int langId);

  List<DegreesAndCredentialsEntity> findAllByLangId(int langId);

  List<DegreesAndCredentialsEntity> findAllByLangIdAndIsFooter(int langId, boolean isFooter);

  DegreesAndCredentialsEntity findByLangIdAndReferId(int langId, int referID);

  List<DegreesAndCredentialsEntity> findAllByReferId(int referId);
}
