package pl.connectis.cinemareservationsapp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class CinemaReservationsAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    // Test get for all client
    @ParameterizedTest
    @CsvFileSource(resources = "/allClientData.csv", delimiter = ';')
    void findAllClientIsOK(String expectedJson) throws Exception {
        mockMvc.perform(get("/client/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }

    // Test get for all employee
    @ParameterizedTest
    @CsvFileSource(resources = "/employeeData.csv", delimiter = ';')
    void findAllEmployeeIsOK(String expectedJson) throws Exception {
        mockMvc.perform(get("/employee/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }

    // Test get for all movie
    @ParameterizedTest
    @CsvFileSource(resources = "/movieData.csv", delimiter = ';')
    void findAllMovieIsOK(String expectedJson) throws Exception {
        mockMvc.perform(get("/movie/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }

    // Test get for single client
    @ParameterizedTest
    @CsvFileSource(resources = "/clientDataById.csv", delimiter = ';')
    void findSingleClientIsOK(int id, String expectedJson) throws Exception {
        mockMvc.perform(get("/client/?id={id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }

    // Test get for single employee
    @ParameterizedTest
    @CsvFileSource(resources = "/employeeDataById.csv", delimiter = ';')
    void findSingleEmployeeIsOK(int id, String expectedJson) throws Exception {
        mockMvc.perform(get("/employee/?id={id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }

    // Test get for single movie
    @ParameterizedTest
    @CsvFileSource(resources = "/movieDataById.csv", delimiter = ';')
    void findSingleMovieIsOK(int id, String expectedJson) throws Exception {
        mockMvc.perform(get("/movie/?id={id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(
                        status().is2xxSuccessful(),
                        content().json(expectedJson)
                ));
    }



}