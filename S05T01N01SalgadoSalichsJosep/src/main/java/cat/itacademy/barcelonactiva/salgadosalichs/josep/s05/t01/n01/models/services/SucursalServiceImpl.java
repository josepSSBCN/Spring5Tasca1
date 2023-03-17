package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Sucursal;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.repository.SucursalRepoInt;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {
    //region ATTRIBUTES
    @Autowired
    private SucursalRepoInt sucursalRepoInt;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    //endregion CONSTRUCTOR


    //region METHODS: OVERRIDE
    @Override
    public SucursalDTO add(SucursalDTO sucursalIn) {
        //region VARIABLES
        Sucursal sucursal = new Sucursal(), sucursal2 = new Sucursal();
        SucursalDTO sucursalOut = new SucursalDTO();

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Transform from SucursalDTO to Sucursal
            sucursal = new Sucursal(Utils.modelMapper.map(sucursalIn, Sucursal.class));

            // Add to DDBB
            sucursal2 = sucursalRepoInt.save(sucursal);

            // Transform from Sucursal to SucursalDTO
            sucursalOut = Utils.modelMapper.map(sucursal2, SucursalDTO.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //endregion ACTIONS


        // OUT
        return sucursalOut;

    }

    @Override
    public boolean delete(int idIn) {
        //region VARIABLES
        boolean resul = false;

        //endregion VARIABLES


        //region ACTIONS
        try {
            sucursalRepoInt.deleteById(idIn);

            resul = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    @Override
    public List<SucursalDTO> getAll() {
        //region VARIABLES
        String sucursalType;
        List<Sucursal> sucursalList = new ArrayList<>();
        List<SucursalDTO> sucursalDTOList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        //endregion VARIABLES


        //region ACTIONS
        // Get all Sucursals from DDBBD
        sucursalList = sucursalRepoInt.findAll();

        // Transform Sucursal type to SucursalDTO
        for (Sucursal suc : sucursalList) {
            // Calculate the 'tipusSucursal' field.
            sucursalType = Utils.checkTypeCountry(suc.getPaisSucursal());
            // Transform to SucursalDTO and add tolist.
            sucursalDTOList.add(new SucursalDTO(modelMapper.map(suc, SucursalDTO.class), sucursalType));

        }

        //endregion ACTIONS


        // OUT
        return sucursalDTOList;

    }

    @Override
    public SucursalDTO getOne(int idIn) {
        //region VARIABLES
        Sucursal sucursalSaved = new Sucursal();
        SucursalDTO sucursalOutDTO = new SucursalDTO();

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Find in DDBB
            sucursalSaved = sucursalRepoInt.findById(idIn).get();

            // Transform from Sucursal to SucursalDTO
            sucursalOutDTO = Utils.modelMapper.map(sucursalSaved, SucursalDTO.class);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //endregion ACTIONS


        // OUT
        return sucursalOutDTO;
    }

    @Override
    public SucursalDTO update(SucursalDTO sucursalIn) {
        //region VARIABLES
        SucursalDTO sucursalOutDTO = new SucursalDTO();
        Sucursal sucursalToSave = new Sucursal(), sucursalSaved = new Sucursal();

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Transform SucursalDTO to Sucursal
            sucursalToSave = Utils.modelMapper.map(sucursalIn, Sucursal.class);

            // Update to DDBB
            sucursalSaved = sucursalRepoInt.save(sucursalToSave);

            // Transform Sucursal to SucursalDTO
            sucursalOutDTO = Utils.modelMapper.map(sucursalSaved, SucursalDTO.class);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            sucursalOutDTO = null;
        }

        //endregion ACTIONS


        // OUT
        System.out.println("Sucursal updated satisfactory!");
        return sucursalOutDTO;
    }

    //endregion METHODS: OVERRIDE


}
