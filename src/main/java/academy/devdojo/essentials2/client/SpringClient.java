package academy.devdojo.essentials2.client;

import academy.devdojo.essentials2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);

        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/2", Anime.class);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        //@formatter:off
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        //@formatter:on
        log.info(exchange.getBody());

//        Anime kingidon = Anime.builder().name("kingidon").build();
//        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingidon, Anime.class);
//        log.info(animeSaved);

        Anime samuraiChamplo = Anime.builder().name("Samurai Champlo").build();
        ResponseEntity<Anime> samuraiChamploSaved = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChamplo),
                Anime.class);

        log.info("saved {} ",samuraiChamploSaved);


    }
}
