package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.dto.FlorDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.services.FlorService;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/flor", "/", ""})
public class FlorController {
    //region ATTRIBUTES
    @Autowired
    private FlorService florService;
    private org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();

    private ResponseEntity<?> responseEntity;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    {
        httpHeaders.set("API version", "v5.1.2_1");
    }

    //endregion CONSTRUCTOR


    //region ENDPOINTS: MAIN
    @GetMapping(value = "")
    public String home() {
        //region VARIABLES
        String text;

        //endregion VARIABLES


        //region ACTIONS
        text = "IT Academy backend Java itinerary<br/>" +
                "Josep Salgado Salichs<br/>" +
                "Exercise: S05T01N02E01<br/><br/>" +
                "END POINTS AVAILABLE:<br/>" +
                "ADD     => http://localhost:9001/flor/add<br/>" +
                "DELETE  => http://localhost:9001/flor/delete/{id}<br/>" +
                "GETALL  => http://localhost:9001/flor/getAll<br/>" +
                "GETONE  => http://localhost:9001/flor/getOne/{id}<br/>" +
                "UPDATE  => http://localhost:9001/flor/update<br/>";

        //endregion ACTIONS


        // OUT
        return text;

    }

    //endregion ENDPOINTS: MAIN


    //region ENDPOINTS: CRUD

    @Operation(summary = "Add a new Flor.", description = "EndPoint for to add a new Flor to system.", tags = "CRUD: ADD")
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region actions
        try{
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.2.EP1_1");

            // Initial checks
            if ((florDTOIn == null) || !Utils.checkString(florDTOIn.getNomFlor()) ||
                    !Utils.checkString(florDTOIn.getPaisFlor())){
                responseEntity = new ResponseEntity<>(Utils.getFlorNoInfo(), httpHeaders, HttpStatus.NOT_ACCEPTABLE);
            }else{
                // Save Flor
                florDTOOut = florService.add(florDTOIn);

                //Check results
                if (florDTOOut == null){
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "add", florDTOIn),
                            httpHeaders, HttpStatus.CONFLICT);
                }  else if (florDTOOut.getPk_FlorID() != 0) {
                    responseEntity = new ResponseEntity<>(florDTOOut, httpHeaders, HttpStatus.OK);
                } else {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), florDTOIn.getPk_FlorID()),
                            httpHeaders, HttpStatus.CONFLICT);
                }
            }

        }catch (Exception ex){
            System.out.println("ERROR: endpoint 'add' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        // OUT
        return responseEntity;

    }

    @DeleteMapping(value = "delete/{id}")
    @Operation(summary = "Delete one Flor.", description = "EndPoint for to delete one flor.", tags = "CRUD: DELETE")
    public ResponseEntity<?> delete(@PathVariable("id") Integer idIn) {
        //region VARIABLES
        FlorDTO florDTO;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.2.EP2_1");

            // Initial checks
            if (idIn != 0) {
                // Delete Flor
                florDTO = florService.delete(idIn);

                if (florDTO == null) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "get Flor with ID", idIn),
                            httpHeaders, HttpStatus.CONFLICT);
                } else if (florDTO.getPk_FlorID() == 0) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), idIn), httpHeaders, HttpStatus.CONFLICT);
                } else if (florDTO.getPk_FlorID() == idIn) {
                    responseEntity = new ResponseEntity<>(String.format("Fruita with ID's '%s', was deleted correctly!",
                            idIn), httpHeaders, HttpStatus.OK);
                }

            } else {
                responseEntity = new ResponseEntity<>("ID = 0, not is acceptable", httpHeaders, HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception ex) {
            System.out.println("ERROR: endpoint 'getAll' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        florService.delete(idIn);

        //endregion ACTIONS


        // OUT
        System.out.println("Flor deleted with exit!");
        return responseEntity;

    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get all Flors.", description = "EndPoint for to get all Flors what system have.", tags = "CRUD: GET")
    public ResponseEntity<?> getAll() {
        //region VARIABLES
        List<FlorDTO> florDTOList;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.2.EP3_1");

            // Find all Flors on table
            florDTOList = new ArrayList<>(florService.getAll());

            // Check result of find
            if (florDTOList.isEmpty()) {
                responseEntity = new ResponseEntity<>("The table is empty.", httpHeaders, HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity<>(florDTOList, httpHeaders, HttpStatus.OK);
            }

        } catch (Exception ex) {
            System.out.println("ERROR: endpoint 'getAll' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    @GetMapping(value = "/getOne/{id}")
    @Operation(summary = "Get one Flor", description = "EndPoint to get Flor with ID the we pass by parameters.", tags = "CRUD: GET")
    public ResponseEntity<?> getOne(@PathVariable("id") Integer idIn) {
        //region VARIABLES
        FlorDTO florDTO;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.2.EP4_1");

            // Initial checks
            if (idIn == 0) {
                responseEntity = new ResponseEntity<>("ID can't be 0.", httpHeaders, HttpStatus.NOT_ACCEPTABLE);
            } else {
                // Looking for the Flor.
                florDTO = florService.getOne(idIn);

                // Check results of search
                if (florDTO == null) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "get Flor with ID", idIn),
                            httpHeaders, HttpStatus.CONFLICT);
                } else if (florDTO.getPk_FlorID() == 0) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), idIn), httpHeaders, HttpStatus.CONFLICT);
                } else {
                    responseEntity = new ResponseEntity<>(florDTO, httpHeaders, HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR: endpoint 'getOne' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    @PutMapping(value = "/update")
    @Operation(summary = "Update Flor", description = "EndPoint to update a Flor, that need new values of Flor " +
            "(the ID can't modify).", tags = "CRUD: UPDATE")
    public ResponseEntity<?> update(@RequestBody FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.2.EP4_1");

            // Initial checks
            if ((florDTOIn == null) || (florDTOIn.getPk_FlorID() == 0) || !Utils.checkString(florDTOIn.getNomFlor()) ||
                    !Utils.checkString(florDTOIn.getPaisFlor())) {
                responseEntity = new ResponseEntity<>(Utils.getFlorNoInfo(), httpHeaders, HttpStatus.NOT_ACCEPTABLE);
            } else {
                // Update Flor.
                florDTOOut = florService.update(florDTOIn);

                // Check results
                if (florDTOOut == null) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "update", florDTOIn),
                            httpHeaders, HttpStatus.CONFLICT);
                } else if (florDTOOut.getPk_FlorID() == 0) {
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), florDTOIn.getPk_FlorID()),
                            httpHeaders, HttpStatus.CONFLICT);
                } else {
                    responseEntity = new ResponseEntity<>(florDTOOut, httpHeaders, HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR: endpoint 'update' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    //endregion ENDPOINTS: CRUD


}
