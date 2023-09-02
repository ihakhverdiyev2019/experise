package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.BlogCategoryEntity;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategoryEntity, Integer> {
  List<BlogCategoryEntity> findAllByLangId(int langId);

  BlogCategoryEntity findByIdAndLangId(int id, int langId);

  BlogCategoryEntity findByLangIdAndReferId(int langId, int referID);

  List<BlogCategoryEntity> findAllByReferId(int referId);
}
