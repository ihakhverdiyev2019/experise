package az.elixir.experise.service.website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.ServiceView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ServiceEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ServiceRepository;

@Service
public class ServiceEntityService {

  @Autowired private ServiceRepository serviceRepository;

  @Autowired private LanguageRepository languageRepository;

  public List<ServiceView> findServicesByLanguage(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<ServiceEntity> getAllDetailsByLanguage =
        serviceRepository.findAllByLangId(getLanguageByLangCode.getId());
    List<ServiceView> result = new ArrayList<>();
    for (ServiceEntity serviceEntity : getAllDetailsByLanguage) {
      ServiceView serviceView = new ServiceView();
      serviceView.mapper(serviceEntity);
      result.add(serviceView);
    }

    return result;
  }
}
