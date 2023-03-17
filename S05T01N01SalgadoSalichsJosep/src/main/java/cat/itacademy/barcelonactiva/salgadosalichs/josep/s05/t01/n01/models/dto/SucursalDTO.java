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


    //region GETTERS & SETTERS
//    public int getPk_SucursalID() {
//        return pk_SucursalID;
//    }
//
//    public void setPk_SucursalID(int pk_SucursalID) {
//        this.pk_SucursalID = pk_SucursalID;
//    }
//
//    public String getNomSucursal() {
//        return nomSucursal;
//    }
//
//    public void setNomSucursal(String nomSucursal) {
//        this.nomSucursal = nomSucursal;
//    }
//
//    public String getPaisSucursal() {
//        return paisSucursal;
//    }
//
//    public void setPaisSucursal(String paisSucursal) {
//        this.paisSucursal = paisSucursal;
//    }
//
//    public String getTipusSucursal() {
//        return tipusSucursal;
//    }
//
//    public void setTipusSucursal(String tipusSucursal) {
//        this.tipusSucursal = tipusSucursal;
//    }

    //endregion GETTERS & SETTERS

}
