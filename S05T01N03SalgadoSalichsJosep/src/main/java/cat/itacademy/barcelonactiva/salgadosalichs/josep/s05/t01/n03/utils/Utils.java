package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.utils;

import lombok.Getter;
import lombok.Setter;
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
    @Getter
    private static String URLAPI512 = "http://localhost:9001/flor";

    @Getter
    private static List<String> countriesUE = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia",
            "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece",
            "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland",
            "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"));

    @Getter
    private static List<String> countriesNoUE = new ArrayList<>(Arrays.asList("USA", "Canada", "India", "Xina"
            , "Morocco", "Egypt", "Japan", "Australia", "Argentina", "Mexico"));

    //endregion ATTRIBUTES: PRIVATE


    //region ATTRIBUTES: TEXT
    @Getter
    @Setter
    private static String unexpectedError = "Some unexpected error occurred!\nPlease try again later.";
    @Getter
    @Setter
    private static String florNoInfo = "The Flor received, doesn't has all necessary information.";
    @Getter
    @Setter
    private static String idNoDDBB = "The Flor with id = %s, doesn't exist on the ddbb.";
    @Getter
    @Setter
    private static String idNoExist = "this id = '%s', doesn't exist in system.";
    @Getter
    @Setter
    private static String someError = "Some error occurred when to %s '%s' on the system.";

    //endregion ATTRIBUTES: TEXT


    //region METHODS: PUBLIC
    /**
     * Method to determinate if country is from UE or not.
     * It checks if countryName param, is blank, empty, null.
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
        if (checkString(countryName)) {
            ////*result = (countriesUE.contains(countryName)) ? TypeCountryENUM.UE.label : TypeCountryENUM.NoUE.label;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Method to check if the string have a values
     * @param stringIn String to check.
     * @return false = doesn't value; true = have a value.
     */
    public static boolean checkString(String stringIn){
        return ((stringIn != null) && (!stringIn.trim().isEmpty()));
    }

    //endregion METHODS: PUBLIC


}

