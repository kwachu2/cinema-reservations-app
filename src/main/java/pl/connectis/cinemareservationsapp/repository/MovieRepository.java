package pl.connectis.cinemareservationsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.connectis.cinemareservationsapp.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findById(long id);

}
