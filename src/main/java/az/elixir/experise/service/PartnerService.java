package az.elixir.experise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.PartnerView;
import az.elixir.experise.model.PartnerEntity;
import az.elixir.experise.repository.PartnerRepository;

@Service
public class PartnerService {

  @Autowired private PartnerRepository partnerRepository;

  public List<PartnerView> findAll() {
    List<PartnerEntity> getAllDetails = partnerRepository.findAll();
    List<PartnerView> result = new ArrayList<>();
    for (PartnerEntity partnerEntity : getAllDetails) {
      PartnerView partnerView = new PartnerView();
      partnerView.mapper(partnerEntity);
      result.add(partnerView);
    }

    return result;
  }
}
