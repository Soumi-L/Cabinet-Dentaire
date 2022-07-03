package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numPersonne", nullable = false)
    private Integer numPersonne;

    @Column(name = "nomPersonne", length = 30)
    private String nomPersonne;

    @Column(name = "prenomPersonne", length = 30)
    private String prenomPersonne;

    @Column(name = "telPersonne", length = 30)
    private String telPersonne;

    @Column(name = "emailPersonne", length = 30)
    private String emailPersonne;

    @Column(name = "passwordPersonne", length = 30)
    private String passwordPersonneUser;

    @Column(name = "cinPersonne", length = 30)
    private String cinPersonne;

    @Embedded
    private Adresse adresse;

    @Column(name = "sexPersonne")
    private String sexPersonne;

}
