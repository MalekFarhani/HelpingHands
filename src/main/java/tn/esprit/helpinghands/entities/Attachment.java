package tn.esprit.helpinghands.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String contentType;
    long size;
    @Lob
    byte[] data;
    @JsonIgnore
    @OneToOne(mappedBy = "attachment", cascade = CascadeType.ALL)
    Reclamation reclamation;

}
