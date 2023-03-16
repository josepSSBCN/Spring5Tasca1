package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SucursalService {
    //region METHODS

    /**
     * Method to add a new Sucursal on DDBB. This method check if country is UE or not.
     * This method transform Sucursal to SucursalDTO.
     *
     * @param sucursalIn Sucursal class to add on DDBB
     * @return Complet Sucursal class.
     */
    SucursalDTO add(SucursalDTO sucursalIn);

    /**
     * Method to delete a Sucursal from DDBB.
     *
     * @param idIn id of Sucursal we want to delete.
     * @return True => all good; False => Some error occurred.
     */
    boolean delete(int idIn);

    /**
     * method to get all Sucursal what exist on DDBB.
     * This method transform Sucursal to SucursalDTO.
     *
     * @return List of all Sucursales from DDBB.
     */
    List<SucursalDTO> getAll();

    /**
     * Method to get the sucursal with the ID like of the param.
     * This method transform Sucursal to SucursalDTO.
     *
     * @param idIn ID of Sucursal to get.
     * @return Sucursal class.
     */
    SucursalDTO getOne(int idIn);

    /**
     * If we want update a Sucursal's param, we must call this method.
     * This method transform SucursalDTO to Sucursal.
     *
     * @param sucursalIn
     * @return
     */
    SucursalDTO update(SucursalDTO sucursalIn);

    //endregion METHODS


}
