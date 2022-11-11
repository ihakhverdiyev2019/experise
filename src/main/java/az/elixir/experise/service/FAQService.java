package az.elixir.experise.service;

import az.elixir.experise.model.FAQEntity;
import az.elixir.experise.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public List<FAQEntity> findAll() {
        return faqRepository.findAll();
    }

}
