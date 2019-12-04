package pl.asenet.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.asenet.movie.domain.dao.MovieRepository;
import pl.asenet.movie.domain.model.Movie;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Tomasz Szymeczek
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void givenExistingId_whenFindById_thenReturnMovie() throws Exception {
        when(movieRepository.findById("123")).thenReturn(Optional.of(getMovie("123", "Lord of the rings")));

        mockMvc.perform(get("/v1/movie/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.title", is("Lord of the rings")))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.imageUrl", is("url")));

        verify(movieRepository, times(1)).findById("123");
    }

    @Test
    public void givenNotExistingId_whenFindById_thenReturnNotFound() throws Exception {
        mockMvc.perform(get("/v1/movie/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenMovie_whenAdd_thenReturnNewMovie() throws Exception {
        Movie newMovie = getMovie("10", "Spring Boot");
        when(movieRepository.save(any(Movie.class))).thenReturn(newMovie);

        mockMvc.perform(post("/v1/movie")
                .content(OBJECT_MAPPER.writeValueAsString(newMovie))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("10")))
                .andExpect(jsonPath("$.title", is("Spring Boot")))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.imageUrl", is("url")));
    }

    @Test
    public void whenFindAll_thenReturnMovieList() throws Exception {
        when(movieRepository.findAll()).thenReturn(getMovieList());

        mockMvc.perform(get("/v1/movie/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void whenFindAll_thenReturnEmptyList() throws Exception {
        when(movieRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/v1/movie/"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenMovie_whenUpdate_thenReturnUpdatedMovie() throws Exception {
        Movie updated = getMovie("1", "Harry Potter");
        when(movieRepository.save(any(Movie.class))).thenReturn(updated);

        Movie toUpdate = getMovie("1", "Indiana Jones");
        mockMvc.perform(patch("/v1/movie/1")
                .content(OBJECT_MAPPER.writeValueAsString(toUpdate))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.title", is("Harry Potter")))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.imageUrl", is("url")));
    }

    @Test
    public void givenMovie_whenDelete_thenReturnId() throws Exception {
        doNothing().when(movieRepository).deleteById("1");

        mockMvc.perform(delete("/v1/movie/1"))
                .andExpect(status().isGone())
                .andExpect(content().string("1"));
    }

    private List<Movie> getMovieList() {
        return List.of(
                getMovie("1", "Lord of the rings"),
                getMovie("2", "Hobbit"),
                getMovie("3", "Pan Samochodzik")
        );
    }

    private Movie getMovie(String id, String title) {
        return Movie.builder()
                .id(id)
                .title(title)
                .description("desc")
                .imageUrl("url")
                .build();
    }

}
