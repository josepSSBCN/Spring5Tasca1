package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto.FlorDTO;

import java.util.List;

public interface FlorService {
    //region METHOTDS
    /**
     * Method to create a new Flor.
     * @param florDTO Flor structure with info of new Flor.
     * @return Flor structure with new Flor, incorporate ID's number.
     */
    FlorDTO Add(FlorDTO florDTO);

    /**
     * Method to delete the Flor with ID equal to the passed param.
     *
     * @param idIn ID to flower we want deleted.
     * @return
     */
    String Delete(int idIn);

    List<FlorDTO> GetAll();

    FlorDTO GetOne(int idIn);

    FlorDTO Update(FlorDTO florIn);


    //endregion METHOTDS

}
