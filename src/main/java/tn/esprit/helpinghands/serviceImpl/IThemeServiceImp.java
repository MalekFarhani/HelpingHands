package tn.esprit.helpinghands.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.Donation;
import tn.esprit.helpinghands.entities.Theme;
import tn.esprit.helpinghands.entities.eTheme;
import tn.esprit.helpinghands.repositories.ThemeRepository;

import java.util.List;

@Service
public class IThemeServiceImp implements IThemeService {
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public Theme addTheme(Theme T) {
        themeRepository.save(T);
        return T;}
    @Override
    public Theme updateTheme(Theme theme, Long id){
        return theme;}
    @Override
    public List<Theme> getall(){
        return  themeRepository.findAll();
    }
    @Override
    public Theme getById(Long id){
        return themeRepository.findById(id).orElse(null);
    }
    @Override
    public void removeTheme(Long id){
        themeRepository.deleteById(id);
    }

}
