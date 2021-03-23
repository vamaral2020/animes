package academy.devdojo.essentials2.controller;

import java.time.LocalDateTime;
import java.util.List;

import academy.devdojo.essentials2.requests.AnimePostRequestBody;
import academy.devdojo.essentials2.requests.AnimePutRequestBody;
import academy.devdojo.essentials2.service.AnimeService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import academy.devdojo.essentials2.domain.Anime;
import academy.devdojo.essentials2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.Valid;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
@Builder
public class AnimeController {
	
	private final DateUtil dateUtil;
	private final AnimeService animeService;

	@GetMapping
	public ResponseEntity<List<Anime>> list(){

		log.info(dateUtil.formaLocalDatimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok(animeService.listAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable long id){
		return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id));
	}
    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam(required = false) String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
		return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);

	}
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable long id){
		animeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
		animeService.replace(animePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
