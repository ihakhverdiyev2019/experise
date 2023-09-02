package az.elixir.experise.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.admin.ListOfLanguage;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class LanguageService {

  @Autowired private LanguageRepository languageRepository;

  public List<ListOfLanguage> findAllLanguage() {
    List<ListOfLanguage> result = new ArrayList<>();
    List<LanguageEntity> getAll = languageRepository.findAllByIsEnable(true);
    for (LanguageEntity languageEntity : getAll) {
      ListOfLanguage listOfLanguage = new ListOfLanguage();
      listOfLanguage.setLangCode(languageEntity.getLangCode());
      listOfLanguage.setLang(languageEntity.getLangText());
      result.add(listOfLanguage);
    }
    return result;
  }
}
