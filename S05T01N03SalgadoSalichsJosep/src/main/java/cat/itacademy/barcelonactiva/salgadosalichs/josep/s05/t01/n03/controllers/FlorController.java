package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.service.FlorService;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.utils.Utils;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/flor"})
public class FlorController {
    //region ATTRIBUTES
    @Autowired
    private FlorService florService;
    private org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();

    private ResponseEntity<?> responseEntity;


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    {
        httpHeaders.set("API version", "5.1.3_v1");
    }

    //endregion CONSTRUCTOR


    //region ENDPOINTS: MAIN
    @GetMapping(value = {""})
    public String home() {
        //region VARIABLES
        String text;

        //endregion VARIABLES


        //region ACTIONS
        text = "IT Academy backend Java itinerary<br/>" +
                "Josep Salgado Salichs<br/>" +
                "Exercise: S05T01N03E01<br/><br/>" +
                "END POINTS AVAILABLE:<br/>" +
                "ADD     => http://localhost:9002/flor/add<br/>" +
                "DELETE  => http://localhost:9002/flor/delete/{id}<br/>" +
                "GETALL  => http://localhost:9002/flor/getAll<br/>" +
                "GETONE  => http://localhost:9002/flor/getOne/{id}<br/>" +
                "UPDATE  => http://localhost:9002/flor/update<br/>";

        //endregion ACTIONS


        // OUT
        return text;

    }

    //endregion ENDPOINTS: MAIN


    //region ENDPOINTS
    @Operation(
            summary = "Add a new Flor.",
            description = "EndPoint for to add a new Flor to system.",
            tags = "CRUD: ADD",
            externalDocs = @ExternalDocumentation(description = "No have external Docs"),
            responses = {
                    @ApiResponse(
                            responseCode = "201-Created",
                            description = "Flor added successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400-Bad Request",
                            description = "The JSON sended hasn't all or part necessary info."
                    ),
                    @ApiResponse(
                            responseCode = "500-Internal Server Error",
                            description = "Some error occurred in the process to save a Flor."
                    )
            }
    )
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region actions
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.3.EP1_1");

            // Initial checks
            if ((florDTOIn == null) || (!Utils.checkString(florDTOIn.getNomFlor())) || (!Utils.checkString(florDTOIn.getPaisFlor()))) {
                // All or part of Flor's info is missing.
                responseEntity = new ResponseEntity<>(Utils.getFlorNoInfo(), httpHeaders, HttpStatus.BAD_REQUEST);
            } else {
                // Call service's method to save Flor.
                florDTOOut = florService.Add(florDTOIn);

                // Check results.
                if (florDTOOut == null) {
                    // Some error occurred.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "add", florDTOIn),
                            httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (florDTOOut.getPk_FlorID() != 0) {
                    // Added successful.
                    responseEntity = new ResponseEntity<>(florDTOOut, httpHeaders, HttpStatus.CREATED);
                } else if (florDTOOut.getPk_FlorID() == 0) {
                    // All or part info is missing.
                    responseEntity = new ResponseEntity<>(Utils.getFlorNoInfo(), httpHeaders, HttpStatus.BAD_REQUEST);
                } else {
                    // Other error type occurred.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), florDTOIn.getPk_FlorID()),
                            httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println("ERROR: endpoint 'add' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        // OUT
        return responseEntity;

    }

    @DeleteMapping(value = "delete/{id}")
    @Operation(
            summary = "Delete one Flor.", description = "EndPoint for to delete one flor.", tags = "CRUD: DELETE",
            externalDocs = @ExternalDocumentation(description = "No have external Docs"),
            responses = {
                    @ApiResponse(
                            responseCode = "200-OK",
                            description = "Flor deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400-Bad Request",
                            description = "2 reason: id=0 or id wasn't on system."
                    ),
                    @ApiResponse(
                            responseCode = "500-Internal Server Error",
                            description = "Some error occurred in the process to add a Flor on DDBB."
                    )
            }
    )
    public ResponseEntity<?> delete(@PathVariable("id") Integer idIn) {
        //region VARIABLES
        String result;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.3.EP2_1");

            // Initial checks
            if (idIn != 0) {
                // Call service's method to delete Flor.
                result = florService.Delete(idIn);

                // Check results.
                if (result == null) {
                    // Some error occurred.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "delete Flor with ID", idIn),
                            httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (result.equals("-1")) {
                    // This ID doesn't exist.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoExist(), idIn), httpHeaders, HttpStatus.BAD_REQUEST);
                } else if (result.equals("OK")) {
                    // Flor delete correctly.
                    responseEntity = new ResponseEntity<>(String.format("Fruita with ID's '%s', was deleted correctly!",
                            idIn), httpHeaders, HttpStatus.OK);
                }
            } else {
                // Id has to different of 0
                responseEntity = new ResponseEntity<>("The ID sended, has to different from 0.", httpHeaders,
                        HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println("ERROR: endpoint 'getAll' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    @GetMapping(value = "/getAll")
    @Operation(
            summary = "Get all Flors.", description = "EndPoint for to get all Flors what system have.", tags = "CRUD: GET",
            externalDocs = @ExternalDocumentation(description = "No have external Docs"),
            responses = {
                    @ApiResponse(
                            responseCode = "200-OK",
                            description = "Send a list with all Flor of DDBB."
                    ),
                    @ApiResponse(
                            responseCode = "400-Bad Request",
                            description = "Appear when The table is empty."
                    ),
                    @ApiResponse(
                            responseCode = "500-Internal Server Error", description = "Some error occurred in the process to add a Flor on DDBB."
                    )
            }
    )
    public ResponseEntity<?> getAll() {
        //region VARIABLES
        List<FlorDTO> florDTOList;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.3.EP3_1");

            // Call service's method to get all Flors.
            florDTOList = florService.GetAll();

            // Check result of find
            if (florDTOList == null) {
                // Some error occurred.
                responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "get all ", "Flors"),
                        httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (florDTOList.isEmpty()) {
                // There isn't Flors saved on the system.
                responseEntity = new ResponseEntity<>("There isn't Flors saved on the system.",
                        httpHeaders, HttpStatus.BAD_REQUEST);
            } else {
                // Return Flors list.
                responseEntity = new ResponseEntity<>(florDTOList, httpHeaders, HttpStatus.OK);
            }

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println("ERROR: endpoint 'getAll' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    @GetMapping("/getOne/{id}")
    @Operation(
            summary = "Get one Flor", description = "EndPoint to get Flor with ID the we pass by parameters.", tags = "CRUD: GET",
            externalDocs = @ExternalDocumentation(description = "No have external Docs"),
            responses = {
                    @ApiResponse(
                            responseCode = "200-OK",
                            description = "Send a list with all Flor of DDBB."
                    ),
                    @ApiResponse(
                            responseCode = "400-Bad Request",
                            description = "2 reason: id=0 or id wasn't on DDBB."
                    ),
                    @ApiResponse(
                            responseCode = "500-Internal Server Error",
                            description = "Some error occurred in the process to add a Flor on DDBB."
                    )
            }
    )
    public ResponseEntity<?> getOne(@PathVariable("id") Integer idIn) {
        //region VARIABLES
        FlorDTO florDTO;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "5.1.3.EP4_v1");

            // Initial checks
            if (idIn == 0) {
                responseEntity = new ResponseEntity<>("ID can't be 0.", httpHeaders, HttpStatus.BAD_REQUEST);
            } else {
                // Call service's method to get Flor.
                florDTO = florService.GetOne(idIn);

                // Check results.
                if (florDTO == null) {
                    // Some error occurred.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "get Flor with ID", idIn),
                            httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (florDTO.getPk_FlorID() == 0) {
                    // There isn't Flor with that ID on the system.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), idIn),
                            httpHeaders, HttpStatus.BAD_REQUEST);
                } else {
                    // Flor found.
                    responseEntity = new ResponseEntity<>(florDTO, httpHeaders, HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            // Some error not controlled occurred.
            System.out.println("ERROR: endpoint 'getOne' \n" + ex.getMessage());
            responseEntity = new ResponseEntity<>(Utils.getUnexpectedError(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    @PutMapping(value = "/update")
    @Operation(
            summary = "Update Flor",
            description = "EndPoint to update a Flor, that need new values of Flor (the ID can't modify).",
            tags = "CRUD: UPDATE",
            externalDocs = @ExternalDocumentation(description = "No have external Docs"),
            responses = {
                    @ApiResponse(
                            responseCode = "201-Created",
                            description = "Flor updated successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400-Bad Request",
                            description = "2 reason:\n1) The JSON sended hasn't all or part necessary info." +
                                    "\n2) Id of the flor isn't on the DDBB."
                    ),
                    @ApiResponse(
                            responseCode = "500-Internal Server Error",
                            description = "Some error occurred in the process to add a Flor on DDBB."
                    )
            }
    )
    public ResponseEntity<?> update(@RequestBody FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // Initializations
            httpHeaders.set("Endpoint version", "v5.1.3.EP4_1");

            // Initial checks
            if ((florDTOIn == null) || (florDTOIn.getPk_FlorID() == 0) || !Utils.checkString(florDTOIn.getNomFlor()) ||
                    !Utils.checkString(florDTOIn.getPaisFlor())) {
                // All or part of Flor's info is missing.
                responseEntity = new ResponseEntity<>(Utils.getFlorNoInfo(), httpHeaders, HttpStatus.BAD_REQUEST);
            } else {
                // Call service's method to Update Flor.
                florDTOOut = florService.Update(florDTOIn);

                // Check results
                if (florDTOOut == null) {
                    // Some error occurred.
                    responseEntity = new ResponseEntity<>(String.format(Utils.getSomeError(), "update", florDTOIn),
                            httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (florDTOOut.getPk_FlorID() == 0) {
                    // ID isn't on DDBB
                    responseEntity = new ResponseEntity<>(String.format(Utils.getIdNoDDBB(), florDTOIn.getPk_FlorID()),
                            httpHeaders, HttpStatus.BAD_REQUEST);
                } else {
                    // Some error occurred.
                    responseEntity = new ResponseEntity<>(florDTOOut, httpHeaders, HttpStatus.CREATED);
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

    //endregion ENDPOINTS

}
