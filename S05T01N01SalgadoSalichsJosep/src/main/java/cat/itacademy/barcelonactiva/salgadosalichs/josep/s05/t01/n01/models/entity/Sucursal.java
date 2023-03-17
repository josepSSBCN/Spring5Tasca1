package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "sucursal")
public class Sucursal {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private int pk_SucursalID;
    @Column(length = 50)
    @NotNull
    @Getter
    @Setter
    @NotEmpty(message = "Sucursal's name not be empty!")
    private String nomSucursal;
    @Column(length = 50)
    @NotNull
    @Getter
    @Setter
    @NotEmpty(message = "Sucursal's country, not be empty!")
    private String paisSucursal;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public Sucursal() {}

    public Sucursal(Sucursal sucursalIn) {
        this.pk_SucursalID = sucursalIn.pk_SucursalID;
        this.nomSucursal = sucursalIn.nomSucursal;
        this.paisSucursal = sucursalIn.paisSucursal;
    }


    //endregion CONSTRUCTOR


//    //region GETTERS & SETTERS
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

    //endregion GETTERS & SETTERS


    //region METHODS

    //endregion METHODS


    // OUT


}
