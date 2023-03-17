package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * Entity to define country structure and DDBB's table.
 */
@Entity
@Table(name = "countries")
public class Country {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column(length = 50)
    @NotNull
    @Getter
    @Setter
    private String name;

    /**
     * false = no UE country; true = UE country.
     */
    @Getter
    @Setter
    private boolean ue;

    //endregion ATTRIBUTES


    //region METHODS

    //endregion METHODS

}
