package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto;

public class SucursalDTO {
    //region ATTRIBUTES
    private int pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;
    private String tipusSucursal;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public SucursalDTO() {
    }

    public SucursalDTO(SucursalDTO sucursalDTO, String typeIn) {
        this.pk_SucursalID = sucursalDTO.pk_SucursalID;
        this.nomSucursal = sucursalDTO.nomSucursal;
        this.paisSucursal = sucursalDTO.paisSucursal;
        this.tipusSucursal = typeIn;

    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS
    public int getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(int pk_SucursalID) {
        this.pk_SucursalID = pk_SucursalID;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
    }

    public String getTipusSucursal() {
        return tipusSucursal;
    }

    public void setTipusSucursal(String tipusSucursal) {
        this.tipusSucursal = tipusSucursal;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    //endregion METHODS


    //region VARIABLES

    //endregion VARIABLES


    //region ACTIONS


    //endregion ACTIONS


    // OUT

}
