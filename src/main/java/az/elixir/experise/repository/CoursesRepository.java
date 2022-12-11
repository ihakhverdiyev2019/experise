package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.CoursesEntity;

@Repository
public interface CoursesRepository extends JpaRepository<CoursesEntity, Integer> {
  CoursesEntity findByIdAndLangId(int id, int langId);

  List<CoursesEntity> findAllByLangId(int langId);
}
