package tn.esprit.helpinghands.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Donation")
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id_donation;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Date date_donation;

    private String cause;
    private String but;


    @ManyToOne
    Theme theme;

    //user
    //id_donateur qui fait la donation
    @ManyToOne
    User user;


    // @OneToMany(cascade = CascadeType.ALL)
    //private List<Product> product;

    //association
}
