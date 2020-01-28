package pl.connectis.cinemareservationsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.connectis.cinemareservationsapp.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findById(long id);
}
