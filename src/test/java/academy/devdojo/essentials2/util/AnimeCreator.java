package academy.devdojo.essentials2.util;

import academy.devdojo.essentials2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }
    public static Anime createValidAnime(){
        return Anime.builder()
                .id(1L)
                .name("Hajime no Ippo")
                .build();
    }
    public static Anime createValidUpadateAnime(){
        return Anime.builder()
                .id(1L)
                .name("Hajime no Ippo 2")
                .build();
    }
}
