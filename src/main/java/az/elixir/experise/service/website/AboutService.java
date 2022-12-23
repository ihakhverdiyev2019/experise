package az.elixir.experise.service.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AboutView;
import az.elixir.experise.model.AboutEntity;
import az.elixir.experise.repository.AboutRepository;

@Service
public class AboutService {

  @Autowired private AboutRepository aboutRepository;

  public AboutView findAbout() {
    AboutEntity getAllDetails = aboutRepository.findById(1).get();
    AboutView aboutView = new AboutView();
    aboutView.mapper(getAllDetails);
    return aboutView;
  }
}
