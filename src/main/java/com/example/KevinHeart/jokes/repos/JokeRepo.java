package com.example.KevinHeart.jokes.repos;

import com.example.KevinHeart.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeRepo {

    Joke getRandomJoke();
    List<Joke> getAllJokes();
    Optional<Joke> getJokeById(Long id);
    Joke saveJoke(Joke joke);
    void deleteJokeById(Long id);

    List<Joke> findAll();

    Optional<Joke> findById(Long id);

    Joke save(Joke joke);

    void deleteById(Long id);
}
