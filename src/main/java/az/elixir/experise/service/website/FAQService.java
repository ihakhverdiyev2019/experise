package az.elixir.experise.service.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.model.FAQEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.FAQRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class FAQService {

  @Autowired private FAQRepository faqRepository;
  @Autowired private LanguageRepository languageRepository;

  public List<FAQEntity> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    return faqRepository.findAllByLangId(getLanguageByLangCode.getId());
  }
}
