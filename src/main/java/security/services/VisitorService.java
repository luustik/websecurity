package security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.models.Visitor;
import security.repositories.VisitorRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitorService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> findByRole(String role){
        return visitorRepository.findByRole(role);
    }
    public List<Visitor> findByVisitorname(String visitorname){
        return visitorRepository.findByVisitorname(visitorname);
    }

    public List<Visitor> findAll(){
        return visitorRepository.findAll();
    }

    @Transactional
    public void save(Visitor visitor){
        visitorRepository.save(visitor);
    }

    public Optional<Visitor> findById(long id){
        return visitorRepository.findById(id);
    }
}
