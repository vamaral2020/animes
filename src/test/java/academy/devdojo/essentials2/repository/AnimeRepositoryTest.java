package academy.devdojo.essentials2.repository;

import academy.devdojo.essentials2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("save persistence anime when successfull")
    void save_PersistAnime_WhenSuccessfull(){
        Anime animeToBeSaved = createAnime();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("save update anime when successfull")
    void save_UpdateAnime_WhenSuccessfull(){

        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Overlord");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("delete remove anime when successfull")
    void delete_deleteAnime_WhenSuccessfull(){

        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        
        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }

    @Test
    @DisplayName("find by name returns list of anime when successfull")
    void findByName_returnsListOfAnime_WhenSuccessfull(){

        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> byName = this.animeRepository.findByName(name);

        Assertions.assertThat(byName)
                .isNotEmpty()
                .contains(animeSaved);

    }
    @Test
    @DisplayName("save throw ContraintViolationException anime when successfull")
    void save_throwContraintViolationException_WhenNameIsEmpyt(){
        Anime anime = new Anime();
//        Assertions.assertThatThrownBy(()->this.animeRepository.save(anime))
//                    .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot empty");


    }

    @Test
    @DisplayName("find by name returns empyt list when anime is not found")
    void findByName_returnsEmpytList_WhenAnimeIsNotFound(){

        List<Anime> byName = this.animeRepository.findByName("goku super");

        Assertions.assertThat(byName).isEmpty();
    }

    private Anime createAnime(){
        return Anime.builder().name("Hajime no Ippo").build();
    }
}