package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.repository.FlorRepositoryAPIImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlorServiceImpl implements FlorService{
    //region ATTRIBUTES
    @Autowired
    private FlorRepositoryAPIImp florRepositoryAPI;

    //endregion ATTRIBUTES


    //region METHODS: OVERRIDE
    @Override
    public FlorDTO Add(FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOout = null;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Call repository's method.
            florDTOout = florRepositoryAPI.Add(florDTOIn);

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println(ex.getMessage());
            florDTOout = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOout;
    }

    @Override
    public String Delete(int idIn) {
        //region VARIABLES
        String resul;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Call repository's method
            resul = florRepositoryAPI.Delete(idIn);

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println(ex.getMessage());
            resul = null;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    @Override
    public List<FlorDTO> GetAll() {
        //region VARIABLES
        List<FlorDTO> florDTOList;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Call repository's method
            florDTOList = florRepositoryAPI.GetAll();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTOList = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOList;

    }

    @Override
    public FlorDTO GetOne(int idIn) {
        //region VARIABLES
        FlorDTO florDTO;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Call repository's method
            florDTO = florRepositoryAPI.GetOne(idIn);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTO = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTO;
    }

    @Override
    public FlorDTO Update(FlorDTO florIn) {
        //region VARIABLES
        FlorDTO florDTOOut = null;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Call repository method
            florDTOOut = florRepositoryAPI.Update(florIn);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;

    }


    //endregion METHODS: OVERRIDE


}
