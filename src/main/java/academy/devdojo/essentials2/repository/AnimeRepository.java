package academy.devdojo.essentials2.repository;

import academy.devdojo.essentials2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {


    List<Anime> findByName(String name);

}
