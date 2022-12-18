package az.elixir.experise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.BannerView;
import az.elixir.experise.model.BannerEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.BannerRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class BannerService {

  @Autowired private BannerRepository bannerRepository;

  @Autowired private LanguageRepository languageRepository;

  public BannerView findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    BannerEntity getAllDetails = bannerRepository.findByLangId(getLanguageByLangCode.getId());

    BannerView bannerView = new BannerView();
    bannerView.mapper(getAllDetails);

    return bannerView;
  }
}
