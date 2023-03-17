package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Country;

import java.util.List;

/**
 *
 */
public interface CountryService {
    //region METHODS

    boolean addOne(int idIn);

    /**
     * Method to check if country exist on DDBB.
     *
     * @param nameIn
     * @return
     */
    boolean exist(String nameIn);

    /**
     * Method to get all countries there is on DDBB.
     *
     * @return Countries list.
     */
    List<Country> getAll();

    /**
     * Method to get all counties what isn't of UE.
     *
     * @return Countries list.
     */
    List<Country> getNoUE();

    /**
     * Method to get all counties what is of UE.
     *
     * @return Countries list.
     */
    List<Country> getUE();

    //endregion METHODS

}
