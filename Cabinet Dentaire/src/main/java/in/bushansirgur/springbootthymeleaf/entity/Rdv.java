package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rdvs")
public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numRdv", nullable = false)
    private Integer numRdv;

    @Column(name = "motifRdv", length = 30)
    private String motifRdv;

    @Column(name = "dateRdv")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRdv;

    @Column(name = "status", length = 30)
    private String status;


    @Column(name = "heureRdv")
    private LocalTime heureRdv;

}
