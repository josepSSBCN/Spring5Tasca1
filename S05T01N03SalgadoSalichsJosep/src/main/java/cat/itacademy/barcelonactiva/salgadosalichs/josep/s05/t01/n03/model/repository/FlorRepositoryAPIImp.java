package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.repository;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n03.utils.Utils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FlorRepositoryAPIImp implements FlorRespoitoryAPI {
    //region ATTRIBUTES
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private ResponseEntity<FlorDTO> responseEntity;
    private HttpEntity<FlorDTO> httpEntity;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    //endregion CONSTRUCTOR

    //region METHODS: OVERRIDE
    @Override
    public FlorDTO Add(FlorDTO florDTOIn){
        //region VARIABLES
        FlorDTO florDTOOut;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // INITIALIZATIONS
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpEntity = new HttpEntity<>(florDTOIn, httpHeaders);

            // CALL API EXERCISE S05T01N02
            responseEntity = restTemplate.exchange(Utils.getURLAPI512() + "/add", HttpMethod.POST, httpEntity, FlorDTO.class);

            // CHECKS RESULTS
            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                // Saved with exit.
                florDTOOut = responseEntity.getBody();
            } else if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
                // All or part info sended is missing.
                florDTOOut = new FlorDTO();
            } else {
                // Other problem occurred.
                florDTOOut = null;
            }

        } catch (HttpServerErrorException.InternalServerError ex) {
            // Some error not controlled occurred.
            System.out.println(ex.getMessage());
            florDTOOut = null;
        } catch (Exception ex) {
            // Another error type appears.
            System.out.println(ex.getMessage());
            florDTOOut = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOOut;

    }

    @Override
    public String Delete(int idIn) {
        //region VARIABLES
        String resul;
        ResponseEntity<Void> responseEntityLcl;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // INITIALIZATIONS
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> httpEntity2 = new HttpEntity<>(httpHeaders);

            // CALL API EXERCISE S05T01N02
            ////*responseEntityLcl = restTemplate.getForEntity(Utils.getURLAPI512() + "/delete/" + idIn, String.class);
            ////* responseEntityLcl = restTemplate.getForObject(Utils.getURLAPI512(), httpEntity2, )
            responseEntityLcl = restTemplate.exchange(Utils.getURLAPI512() + "/delete/{id}",
                    HttpMethod.DELETE, httpEntity2, Void.class, idIn);

            // CHECKS RESULTS
            if (responseEntityLcl.getStatusCode() == HttpStatus.OK) {
                resul = "OK";
            } else if (responseEntityLcl.getStatusCode() == HttpStatus.NO_CONTENT) {
                // ID isn't on DDBB.
                resul = "-1";
            } else {
                // Another error appears.
                resul = null;
            }

        } catch (HttpServerErrorException.InternalServerError ex) {
            System.out.println(ex.getMessage());
            resul = null;
        } catch (Exception ex) {
            // Another error type appears.
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
        ResponseEntity<List<FlorDTO>> responseEntity;
        //
        Class<List<FlorDTO>> cls = (Class<List<FlorDTO>>) (Object) List.class;
        //endregion VARIABLES


        //region ACTIONS
        try {
            // CALL API EXERCISE S05T01N02
            responseEntity = restTemplate.getForEntity(Utils.getURLAPI512() + "/getAll", cls);

            // CHECKS RESULTS
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // Return Flors list.
                florDTOList = new ArrayList<>();
                florDTOList.addAll(responseEntity.getBody());
            } else if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
                // There isn't Flors saved on the system.
                florDTOList = new ArrayList<>();
            } else {
                // Other error type occurred.
                florDTOList = null;
            }

        } catch (Exception ex) {
            // Some error not controlled occurred.
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
        ResponseEntity<FlorDTO> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CALL API EXERCISE S05T01N02
            responseEntity = restTemplate.getForEntity(Utils.getURLAPI512() + "/getOne/" + idIn, FlorDTO.class);

            // CHECKS RESULTS
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // Return flor.
                florDTO = responseEntity.getBody();
            } else if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
                // There isn't Flor with that ID on the system.
                florDTO = new FlorDTO();
            } else {
                // Other error type occurred.
                florDTO = null;
            }

        } catch (HttpServerErrorException.InternalServerError ex) {
            // Some error not controlled occurred.
            System.out.println(ex.getMessage());
            florDTO = null;
        } catch (Exception ex) {
            // Another error type appears.
            System.out.println(ex.getMessage());
            florDTO = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTO;
    }

    @Override
    public FlorDTO Update(FlorDTO florDTOIn) {
        //region VARIABLES
        FlorDTO florDTOout;
        ////*ResponseEntity<FlorDTO> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // INITIALIZATIONS
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpEntity = new HttpEntity<>(florDTOIn, httpHeaders);

            // CALL API EXERCISES S05T01N02
            ////*responseEntity = restTemplate.getForEntity(Utils.getURLAPI512() + "/update", FlorDTO.class, florDTOIn);
            responseEntity = restTemplate.exchange(Utils.getURLAPI512() + "/update",
                    HttpMethod.PUT, httpEntity, FlorDTO.class);

            // CHECKS RESULTS
            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                florDTOout = responseEntity.getBody();
            } else if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
                // ID isn't on DDBB
                florDTOout = new FlorDTO();
            } else {
                florDTOout = null;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            florDTOout = null;
        }

        //endregion ACTIONS


        // OUT
        return florDTOout;
    }

    //endregion METHODS: OVERRIDE

}
