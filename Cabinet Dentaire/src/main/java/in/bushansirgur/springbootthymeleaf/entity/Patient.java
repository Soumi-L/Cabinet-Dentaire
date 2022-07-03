package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends Personne{

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreation;

    @PrePersist
    @PreUpdate
    private void onCreate() {
    	dateCreation = new Date();
    }

    @OneToMany(
    		cascade = CascadeType.ALL,
    		orphanRemoval = false )
    @JoinColumn(name = "numPersonne")
    List<Rdv> rdvs = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "numPersonne")
    List<Antecedent> antecedents = new ArrayList<>();

    /*@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numPersonne")
    private SituationFinanciere Sf;*/
}
