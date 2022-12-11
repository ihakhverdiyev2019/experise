package az.elixir.experise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.AllCoursesView;
import az.elixir.experise.dto.CoursesView;
import az.elixir.experise.model.CoursesEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.CoursesRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class CoursesService {

  @Autowired private CoursesRepository repository;

  @Autowired private LanguageRepository languageRepository;

  public List<AllCoursesView> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<CoursesEntity> getAllDetails = repository.findAllByLangId(getLanguageByLangCode.getId());
    List<AllCoursesView> result = new ArrayList<>();
    for (CoursesEntity entity : getAllDetails) {
      AllCoursesView allCoursesView = new AllCoursesView();
      allCoursesView.mapper(entity);
      result.add(allCoursesView);
    }
    return result;
  }

  public CoursesView findById(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    CoursesEntity getAllDetails = repository.findByIdAndLangId(id, getLanguageByLangCode.getId());
    CoursesView coursesView = new CoursesView();
    coursesView.mapper(getAllDetails);
    return coursesView;
  }
}
