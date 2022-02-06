package academy.devdojo.essentials2.service;

import academy.devdojo.essentials2.domain.Anime;
import academy.devdojo.essentials2.exception.BadRequestException;
import academy.devdojo.essentials2.repository.AnimeRepository;
import academy.devdojo.essentials2.requests.AnimePostRequestBody;
import academy.devdojo.essentials2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {

        return animeRepository.findAll(pageable);

    }

    public List<Anime> findByName(String name) {

        return animeRepository.findByName(name);

    }

    public Anime findByIdOrThrowRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }



    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = Anime.builder()
                .name(animePostRequestBody.getName())
                .build();
        return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);
    }
}
