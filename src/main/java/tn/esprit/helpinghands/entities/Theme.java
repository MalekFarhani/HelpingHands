package tn.esprit.helpinghands.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theme_id;
    private eTheme theme_name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="theme")
    private List<Donation> Donation;


}
