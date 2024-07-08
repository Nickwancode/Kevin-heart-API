package com.example.KevinHeart.jokes.controller;

import com.example.KevinHeart.Joke;
import com.example.KevinHeart.jokes.service.JokeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JokeController {
    @Autowired
    private JokeService jokeService;

    @GetMapping("/joke")
    public ResponseEntity<Joke> getRandomJoke(@RequestParam(required = false, defaultValue = "0") int limit) {
        if (limit > 0) {
            return ResponseEntity.ok(jokeService.getRandomJoke(limit));
        } else {
            return ResponseEntity.ok(jokeService.getRandomJoke());
        }
    }

    @GetMapping("/jokes")
    public ResponseEntity<List<Joke>> retrieveAllJokes() {
        try {
            List<Joke> allJokes = jokeService.getAllJokes();
            if (allJokes == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(allJokes);
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., log the error and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        Optional<Joke> joke = jokeService.getJokeById(id);
        return joke.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/joke")
    public ResponseEntity<Joke> createJoke(@Valid @RequestBody Joke joke) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jokeService.saveJoke(joke));
    }

    @PutMapping("/joke/{id}")
    public ResponseEntity<Joke> updateJoke(@PathVariable Long id, @Valid @RequestBody Joke jokeDetails) {
        Optional<Joke> jokeOptional = jokeService.getJokeById(id);
        if (jokeOptional.isPresent()) {
            Joke joke = jokeOptional.get();
            joke.setJoke(jokeDetails.getJoke());
            return ResponseEntity.ok(jokeService.saveJoke(joke));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/joke/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable Long id) {
        Optional<Joke> jokeOptional = jokeService.getJokeById(id);
        if (jokeOptional.isPresent()) {
            jokeService.deleteJokeById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}