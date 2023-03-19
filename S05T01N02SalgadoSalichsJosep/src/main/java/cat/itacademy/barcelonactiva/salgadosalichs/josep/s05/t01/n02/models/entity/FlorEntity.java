package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FlorEntity")
public class FlorEntity {
    //region ATTRIBUTES

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Id
    @Setter
    private int pk_FlorID;

    @Getter
    @Setter
    private String nomFlor;

    @Getter
    @Setter
    private String paisFlor;

    //endregion ATTRIBUTES

}
