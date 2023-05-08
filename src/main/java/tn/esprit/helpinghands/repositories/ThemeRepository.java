package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.helpinghands.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
}
