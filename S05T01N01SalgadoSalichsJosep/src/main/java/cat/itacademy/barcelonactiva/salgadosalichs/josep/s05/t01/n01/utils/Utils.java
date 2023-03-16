package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.utils;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.domain.TypeCountryENUM;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Method with more common methods.
 */
public class Utils {
    //region ATTRIBUTES: PUBLIC
    public static ModelMapper modelMapper = new ModelMapper();

    //endregion ATTRIBUTES: PUBLIC

    //region ATTRIBUTES: PRIVATE
    private static List<String> countriesUE = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia",
            "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece",
            "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland",
            "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"));

    private static List<String> countriesNoUE = new ArrayList<>(Arrays.asList("EEUU", "Canada", "India", "Xina"
            , "Morocco", "Egypte", "Japan", "Australia", "Argentina", "Mexico"));

    //endregion ATTRIBUTES: PRIVATE


    //region GETTERS & SETTERS
    public static List<String> getCountriesUE() {
        return countriesUE;
    }

    public static void setCountriesUE(List<String> countriesUE) {
        Utils.countriesUE = countriesUE;
    }

    public static List<String> getCountriesNoUE() {
        return countriesNoUE;
    }

    public static void setCountriesNoUE(List<String> countriesNoUE) {
        Utils.countriesNoUE = countriesNoUE;
    }

//endregion GETTERS & SETTERS


    //region METHODS

    /**
     * Method to determinate if country is from UE or not.
     * It check if countryName param, is blanck, empty, null.
     *
     * @param countryName Name of country to check.
     * @return Return String of: "UE" if country is from UE and "No UE" if not is from UE.
     * NOTE! If countryName isn't correct, return -1.
     */
    public static String checkTypeCountry(@NotNull String countryName) {
        //region VARIABLES
        String result = "-1";

        //endregion VARIABLES


        //region ACTIONS
        if (!countryName.trim().isEmpty()){
            result = (countriesUE.contains(countryName)) ? TypeCountryENUM.UE.label : TypeCountryENUM.NoUE.label;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS


}

