package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto;

import lombok.Getter;
import lombok.Setter;

public class FlorDTO {
    //region ATTRIBUTES
    @Getter
    @Setter
    private int pk_FlorID;

    @Getter
    @Setter
    private String nomFlor;

    @Getter
    @Setter
    private String paisFlor;

    @Getter
    @Setter
    private String tipusFlor;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public FlorDTO() {
        nomFlor = null;
    }

    public FlorDTO(FlorDTO florDTO, String typeIn) {
        this.pk_FlorID = florDTO.pk_FlorID;
        this.nomFlor = florDTO.nomFlor;
        this.paisFlor = florDTO.paisFlor;
        this.tipusFlor = typeIn;

    }
    //endregion CONSTRUCTOR


    //region METHODS

    //endregion METHODS


}
