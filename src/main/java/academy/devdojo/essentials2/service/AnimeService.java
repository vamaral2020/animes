package academy.devdojo.essentials2.service;

import academy.devdojo.essentials2.domain.Anime;
import academy.devdojo.essentials2.exception.BadRequestException;
import academy.devdojo.essentials2.mapper.AnimeMapper;
import academy.devdojo.essentials2.repository.AnimeRepository;
import academy.devdojo.essentials2.requests.AnimePostRequestBody;
import academy.devdojo.essentials2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {

        return animeRepository.findAll();

    }

    public List<Anime> findByName(String name) {

        return animeRepository.findByName(name);

    }

    public Anime findByIdOrThrowRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }



    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTACE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTACE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
