package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.services.SucursalService;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping({"/sucursal", "/"})
public class SucursalController {
    //region ATTRIBUTES
    @Autowired
    private SucursalService sucursalService;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    //endregion CONSTRUCTOR


    //region METHODS: CHANGEPAGES
    @GetMapping({"/home", "/index", "/"})
    public String indexForm() {
        return "home";
    }

    @GetMapping({"/maps"})
    public String mapForm() {
        return "maps";
    }

    @GetMapping({"/created"})
    public String createdForm(Model model) {
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

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") int idIn, Model model ){
        //region VARIABLES
        List<String> countriesList = new ArrayList<>();
        SucursalDTO sucursalDTO = new SucursalDTO();

        //endregion VARIABLES


        //region ACTIONS
        // Get all countries
        countriesList.addAll(Utils.getCountriesUE());
        countriesList.addAll(Utils.getCountriesNoUE());
        Collections.sort(countriesList);

        // Find Sucursal to modify
        sucursalDTO = sucursalService.getOne(idIn);

        // Add attributes
        model.addAttribute("title", "EDIT SUCURSAL");
        model.addAttribute("countries", countriesList);
        model.addAttribute("sucursal", sucursalDTO);

        //endregion ACTIONS


        // OUT
        return "/views/frmAdd";

    }

    //endregion METHODS: CHANGEPAGES


    //region METHODS: CRUD
    @PostMapping("/add")
    public String add(@ModelAttribute SucursalDTO sucursalDTO){
        //region ACTIONS
        sucursalService.add(sucursalDTO);

        //endregion ACTIONS


        // OUT
        System.out.println("Sucursal saved!");
        return "redirect:/sucursal/getAll";

    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        //region VARIABLES
        List<SucursalDTO> sucursalDTOList;

        //endregion VARIABLES


        //region ACTIONS
        // Get all Sucursals
        sucursalDTOList = sucursalService.getAll();

        // Add attributes
        model.addAttribute("title","Sucursals List from java");
        model.addAttribute("sucursals", sucursalDTOList);

        //endregion ACTIONS


        // OUT
        return "/views/list";

    }

    @GetMapping("/getOne")
    public String getOne(Model model){
        return "";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idIn){
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
