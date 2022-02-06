package academy.devdojo.essentials2.mapper;

import academy.devdojo.essentials2.domain.Anime;
import academy.devdojo.essentials2.requests.AnimePostRequestBody;
import academy.devdojo.essentials2.requests.AnimePutRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-05T18:29:45-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class AnimeMapperImpl extends AnimeMapper {

    @Override
    public Anime toAnime(AnimePostRequestBody animePostRequestBody) {
        if ( animePostRequestBody == null ) {
            return null;
        }

        Anime anime = new Anime();

        return anime;
    }

    @Override
    public Anime toAnime(AnimePutRequestBody animePutRequestBody) {
        if ( animePutRequestBody == null ) {
            return null;
        }

        Anime anime = new Anime();

        return anime;
    }
}
