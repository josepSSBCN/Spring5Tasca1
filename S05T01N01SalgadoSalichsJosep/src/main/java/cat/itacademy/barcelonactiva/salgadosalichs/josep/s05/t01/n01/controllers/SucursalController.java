package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services.SucursalService;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping({"/sucursal", "/"})
public class SucursalController {
    //region ATTRIBUTES
    @Autowired
    private SucursalService sucursalService;

    //endregion ATTRIBUTES


    //region METHODS: CHANGEPAGES
    @GetMapping(value = {"/home", "/"})
    @Operation(summary = "Go to 'home' page.", description = "EndPoint to go 'home' page.", tags = "CHANGE PAGE")
    public String indexForm() {
        return "/home";
    }

    @GetMapping(value = "/maps")
    @Operation(summary = "Go to 'maps' page.", description = "EndPoint to go 'maps' page.", tags = "CHANGE PAGE")
    public String mapForm() {
        return "maps";
    }

    @GetMapping(value = "/addFrom")
    @Operation(summary = "Go to 'add' page.", description = "EndPoint to go 'add' page.", tags = "CHANGE PAGE")
    public String addForm(Model model) {
        //region VARIABLES
        List<String> countriesList = new ArrayList<>();
        SucursalDTO sucursalDTO = new SucursalDTO();

        //endregion VARIABLES


        //region ACTIONS
        // Get all countries
        countriesList.addAll(Utils.getCountriesUE());
        countriesList.addAll(Utils.getCountriesNoUE());
        Collections.sort(countriesList);

        // Add attributes
        model.addAttribute("title", "ADD A NEW SUCURSAL");
        model.addAttribute("countries", countriesList);
        model.addAttribute("sucursal", sucursalDTO);

        //endregion ACTIONS

        // OUT
        return "/views/frmAdd";

    }

    @GetMapping(value = "/update/{id}")
    @Operation(summary = "Go to 'update' page.", description = "EndPoint to go 'add' page.", tags = "CHANGE PAGE")
    public String updateForm(@PathVariable("id") int idIn, Model model) {
        //region VARIABLES
        List<String> countriesList = new ArrayList<>();
        SucursalDTO sucursalDTO;

        //endregion VARIABLES


        //region ACTIONS
        // Get all countries
        countriesList.addAll(Utils.getCountriesUE());
        countriesList.addAll(Utils.getCountriesNoUE());
        Collections.sort(countriesList);

        // Find Sucursal to modify
        sucursalDTO = sucursalService.getOne(idIn);

        // Add attributes
        model.addAttribute("title", "UPDATE SUCURSAL");
        model.addAttribute("countries", countriesList);
        model.addAttribute("sucursal", sucursalDTO);

        //endregion ACTIONS


        // OUT
        return "/views/frmAdd";

    }

    //endregion METHODS: CHANGEPAGES


    //region METHODS: CRUD
    @PostMapping(value = "/add")
    @Operation(summary = "Add a new Sucursal.", description = "EndPoint for to add a new Sucursal to system.", tags = "CRUD: ADD")
    public String add(@Valid @ModelAttribute SucursalDTO sucursalDTO, BindingResult result, Model model) {
        //region VARIABLES
        String returnValue, messageValue;
        List<String> countriesList;

        //endregion VARIABLES


        //region actions
        // Check if there is a some error
        if (sucursalDTO.getNomSucursal().isEmpty()) { //todo result.hasErrors() Why result has a 0 errors when 'nomSucursal' is empty?
            // Get all countries
            countriesList = new ArrayList<>();
            countriesList.addAll(Utils.getCountriesUE());
            countriesList.addAll(Utils.getCountriesNoUE());
            Collections.sort(countriesList);

            // Add attributes
            model.addAttribute("title", "NEW SUCURSAL");
            model.addAttribute("countries", countriesList);
            model.addAttribute("sucursal", sucursalDTO);

            // Out values
            messageValue = "Some error occurred!";
            returnValue = "redirect:/sucursal/addFrom";
        } else {
            // Add the new sucursal to DDBB
            sucursalService.add(sucursalDTO);

            // Out values
            messageValue = "Sucursal saved!";
            returnValue = "redirect:/sucursal/getAll";
        }

        //endregion actions


        // OUT
        System.out.println(messageValue);
        return returnValue;

    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get all Sucursals.", description = "EndPoint for to get all sucursals what system have.", tags = "CRUD: GET")
    public String getAll(Model model) {
        //region VARIABLES
        List<SucursalDTO> sucursalDTOList;

        //endregion VARIABLES


        //region ACTIONS
        // Get all Sucursals
        sucursalDTOList = sucursalService.getAll();

        // Add attributes
        model.addAttribute("title", "Sucursal's List");
        model.addAttribute("sucursals", sucursalDTOList);

        //endregion ACTIONS


        // OUT
        return "/views/list";

    }

    @GetMapping(value = "/getOne")
    @Operation(summary = "Get one Sucursal.", description = "EndPoint for to get only one sucursal.", tags = "CRUD: GET")
    public String getOne(Model model) {
        return "";
    }


    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete one Sucursal.", description = "EndPoint for to delete one sucursal.", tags = "CRUD: DELETE")
    //todo @DeleteMapping(value = "/delete/{id}") Why when I put this annotation does not work?
    public String delete(@PathVariable("id") Integer idIn) {
        //region VARIABLES

        //endregion VARIABLES


        //region ACTIONS
        sucursalService.delete(idIn);

        //endregion ACTIONS


        // OUT
        System.out.println("Sucursal deleted with exit!");
        return "redirect:/sucursal/getAll";

    }

    //endregion METHODS: CRUD

}
