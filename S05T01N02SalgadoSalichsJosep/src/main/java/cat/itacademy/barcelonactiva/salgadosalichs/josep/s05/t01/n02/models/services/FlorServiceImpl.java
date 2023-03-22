package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.services;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.dto.FlorDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.entity.FlorEntity;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.repository.FlorRepositoryInt;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlorServiceImpl implements FlorService {
    //region ATTRIBUTES
    @Autowired
    private FlorRepositoryInt florRepositoryInt;

    //endregion ATTRIBUTES


    //region METHODS: OVERRIDE
    @Override
    public FlorDTO Add(FlorDTO florDTOIn) {
        //region VARIABLES
        FlorEntity florToSave, florSaved;
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Transform from FlorDTO to Flor
            florToSave = Utils.modelMapper.map(florDTOIn, FlorEntity.class);

            // Add to DDBB
            florSaved = florRepositoryInt.save(florToSave);

            // Transform from Flor to FlorDTO
            florDTOOut = Utils.modelMapper.map(florSaved, FlorDTO.class);

            // Check country type
            florDTOOut.setTipusFlor(Utils.checkTypeCountry(florDTOOut.getPaisFlor()));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTOOut = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;

    }

    @Override
    public FlorDTO Delete(int idIn) {
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Check if the ID exist
            if (florRepositoryInt.existsById(idIn)) {

                // Delete flor
                florRepositoryInt.deleteById(idIn);

                florDTOOut = new FlorDTO();
                florDTOOut.setPk_FlorID(idIn);

            } else {
                // ID doesn't exist.
                florDTOOut = new FlorDTO();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTOOut = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;
    }

    @Override
    public List<FlorDTO> GetAll() {
        //region VARIABLES
        String florType;
        List<FlorEntity> florList;
        List<FlorDTO> florDTOList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        //endregion VARIABLES


        //region ACTIONS
        // Get all Flors from DDBBD
        florList = florRepositoryInt.findAll();

        // Transform all elements from FlorEntity type to FlorDTO
        for (FlorEntity flor : florList) {
            // Calculate the 'tipusFlor' field.
            florType = Utils.checkTypeCountry(flor.getPaisFlor());

            // Transform from FlorDTO and add to list.
            florDTOList.add(new FlorDTO(modelMapper.map(flor, FlorDTO.class), florType));

        }

        //endregion ACTIONS


        // OUT
        return florDTOList;

    }

    @Override
    public FlorDTO GetOne(int idIn) {
        //region VARIABLES
        FlorEntity florSaved;
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Check if the ID exist
            if (florRepositoryInt.existsById(idIn)) {
                // Find in DDBB
                florSaved = florRepositoryInt.findById(idIn).get();

                // Transform from Flor to FlorDTO
                florDTOOut = Utils.modelMapper.map(florSaved, FlorDTO.class);

                // Check country type
                florDTOOut.setTipusFlor(Utils.checkTypeCountry(florDTOOut.getPaisFlor()));

            } else {
                // Doesn't exist ID on DDBB
                florDTOOut = new FlorDTO();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTOOut = null;

        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;

    }

    @Override
    public FlorDTO Update(FlorDTO florIn) {
        //region VARIABLES
        FlorEntity florToSave, florSaved;
        FlorDTO florDTOOut = null;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Check if the ID exist
            if (florRepositoryInt.existsById(florIn.getPk_FlorID())) {
                // Transform FlorDTO to Flor
                florToSave = Utils.modelMapper.map(florIn, FlorEntity.class);

                // Update to DDBB
                florSaved = florRepositoryInt.save(florToSave);

                // Transform Flor to FlorDTO
                florDTOOut = Utils.modelMapper.map(florSaved, FlorDTO.class);

                // Check country type
                florDTOOut.setTipusFlor(Utils.checkTypeCountry(florDTOOut.getPaisFlor()));

            } else {
                // Doesn't exist ID on DDBB
                florDTOOut = new FlorDTO();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;

    }


    //endregion METHODS: OVERRIDE

}
