package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class SucursalDTO {
    //region ATTRIBUTES
    @Getter
    @Setter
    private int pk_SucursalID;

    @Getter
    @NotEmpty
    @Setter
    private String nomSucursal;

    @Getter
    @NotEmpty
    @Setter
    private String paisSucursal;
    @Getter
    @Setter
    private String tipusSucursal;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public SucursalDTO() {
        nomSucursal = null;
    }

    public SucursalDTO(SucursalDTO sucursalDTO, String typeIn) {
        this.pk_SucursalID = sucursalDTO.pk_SucursalID;
        this.nomSucursal = sucursalDTO.nomSucursal;
        this.paisSucursal = sucursalDTO.paisSucursal;
        this.tipusSucursal = typeIn;

    }

    //endregion CONSTRUCTOR


}
