package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.dto.FlorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Sucursal
 */

@Service
public interface FlorService {
    //region METHODS

    /**
     * Method to add a new Flor on DDBB. This method check if country's Flor is UE or not.
     * This method transform Flor to FlorDTO.
     *
     * @param florDTO Flor class to add on DDBB
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID != 0 => Flor was add successfully.
     */
    FlorDTO add(FlorDTO florDTO);

    /**
     * Method to delete a Flor from DDBB.
     *
     * @param idIn ID of Flor we want to delete.
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID = idIn => Flor delete with success.
     * pk_FlorID = 0 => ID doesn't exist on DDBB.
     */
    FlorDTO delete(int idIn);

    /**
     * Method to get all Flors what exist on DDBB.
     * This method transform Flor to FlorDTO.
     *
     * @return List of all Flors from DDBB.
     */
    List<FlorDTO> getAll();


    /**
     * Method to get the Flor with the ID like of the param.
     * This method transform Flor to FlorDTO.
     *
     * @param idIn ID of Flor to get.
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID = 0 => ID doesn't exist on DDBB.
     */
    FlorDTO getOne(int idIn);

    /**
     * If we want update a Flor's param, we must call this method.
     * This method transform FlorDTO to Flor.
     *
     * @param florIn Flor class with the new values.
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID = 0 => ID doesn't exist on DDBB.
     */
    FlorDTO update(FlorDTO florIn);

    //endregion METHODS


}
