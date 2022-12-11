package az.elixir.experise.service;

import az.elixir.experise.dto.AboutView;
import az.elixir.experise.dto.CoursesView;
import az.elixir.experise.model.AboutEntity;
import az.elixir.experise.model.CoursesEntity;
import az.elixir.experise.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutService {

    @Autowired
    private AboutRepository aboutRepository;

    public AboutView findAbout() {
        AboutEntity getAllDetails = aboutRepository.findById(1).get();
        AboutView aboutView = new AboutView();
        aboutView.mapper(getAllDetails);
        return aboutView;
    }
}
