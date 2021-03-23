package academy.devdojo.essentials2.mapper;

import academy.devdojo.essentials2.domain.Anime;
import academy.devdojo.essentials2.requests.AnimePostRequestBody;
import academy.devdojo.essentials2.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTACE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
