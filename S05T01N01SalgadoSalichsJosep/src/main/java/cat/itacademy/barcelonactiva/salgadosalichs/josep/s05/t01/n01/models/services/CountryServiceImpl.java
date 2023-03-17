package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Country;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.repository.CountryRepoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    //region ATTRIBUTES
    @Autowired
    private CountryRepoInt countryRepoInt;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    //endregion CONSTRUCTOR


    //region METHODS: OVERRIDE

    @Override
    public boolean addOne(int idIn) {
        return false;
    }

    @Override
    public boolean exist(String nameIn) {
        return false;
    }

    @Override
    public List<Country> getAll() {
        return null;
    }

    @Override
    public List<Country> getNoUE() {
        return null;
    }

    @Override
    public List<Country> getUE() {
        return null;
    }

    //endregion METHODS: OVERRIDE


}
