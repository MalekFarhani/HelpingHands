package tn.esprit.helpinghands.serviceImpl;



import tn.esprit.helpinghands.entities.Theme;

import java.util.List;

public interface IThemeService {

    Theme addTheme(Theme T);

    Theme updateTheme(Theme theme, Long id);

    List<Theme> getall();
    Object getById(Long id);
    void removeTheme(Long id);
}
