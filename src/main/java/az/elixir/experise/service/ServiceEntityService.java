package az.elixir.experise.service;

import az.elixir.experise.dto.ServiceView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ServiceEntity;
import az.elixir.experise.model.ServiceLanguageEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ServiceLanguageRepository;
import az.elixir.experise.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceLanguageRepository serviceLanguageRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public List<ServiceView> findServicesByLanguage(String langCode) {
        LanguageEntity getLanguageByLangCode = languageRepository.findByLangCodeAndIsEnable(langCode, true);
        List<ServiceLanguageEntity> getAllDetailsByLanguage = serviceLanguageRepository.findAllByLanguageId(getLanguageByLangCode.getId());
        List<ServiceView> result = new ArrayList<>();
        for (ServiceLanguageEntity serviceLanguage :
                getAllDetailsByLanguage) {
            ServiceEntity getServiceById = serviceRepository.findById(serviceLanguage.getServiceId()).get();
            ServiceView serviceView = new ServiceView();
            serviceView.setServiceName(serviceLanguage.getServiceName());
            serviceView.setServicePhoto(getServiceById.getServicePhoto());
            serviceView.setServiceDescription(serviceLanguage.getServiceDescription());
            result.add(serviceView);
        }

        return result;
    }


}
