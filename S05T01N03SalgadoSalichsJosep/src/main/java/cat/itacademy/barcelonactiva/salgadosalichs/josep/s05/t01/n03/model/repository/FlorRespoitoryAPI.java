package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.repository;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto.FlorDTO;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.util.List;

@Repository
public interface FlorRespoitoryAPI {
    //region METHODS
    /**
     * Method to add a new Flor on DDBB throught API S05T01N02.
     *
     * @param florDTO Flor class to add on DDBB
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID = 0 => All or part of the info Flor, isn't correct.
     * pk_FlorID != 0 => Flor was add successfully.
     */
    FlorDTO Add(FlorDTO florDTO) throws URISyntaxException;

    String Delete(int idIn);

    List<FlorDTO> GetAll();

    FlorDTO GetOne(int idIn);

    /**
     * If we want update a Flor's param, we must call this method.
     * This method transform FlorDTO to Flor.
     *
     * @param florIn Flor class with the new values.
     * @return return values...
     * null => some problem occurred;
     * pk_FlorID = 0 => ID doesn't exist on DDBB.
     */
    FlorDTO Update(FlorDTO florIn);

    //endregion METHODS
}
