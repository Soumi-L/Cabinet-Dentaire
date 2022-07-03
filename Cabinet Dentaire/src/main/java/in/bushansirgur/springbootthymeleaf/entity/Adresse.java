package in.bushansirgur.springbootthymeleaf.entity;
import lombok.*;
import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Adresse {
    @Column(name = "adresse", nullable = true, length = 255)
    private String adr;
    @Column(name = "codePostale", nullable = true, length = 45)
    private String codePostale;
    @Column(name = "ville", nullable = true, length = 45)
    private String ville;
    @Column(name = "pays", nullable = true, length = 45)
    private String pays;
}
