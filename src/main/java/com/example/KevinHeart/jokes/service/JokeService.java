package com.example.KevinHeart.jokes.service;


import com.example.KevinHeart.Joke;
import com.example.KevinHeart.jokes.repos.JokeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class JokeService {
    @Autowired
    private JokeRepo jokeRepository;

    public Joke getRandomJoke() {
        List<Joke> jokes = jokeRepository.findAll();
        Random rand = new Random();
        return jokes.get(rand.nextInt(jokes.size()));
    }

    public Joke getRandomJoke(int limit) {
        List<Joke> jokes = jokeRepository.findAll();
        if (limit > jokes.size()) {
            limit = jokes.size();
        }
        List<Joke> limitedJokes = jokes.stream().limit(limit).toList();
        Random rand = new Random();
        return limitedJokes.get(rand.nextInt(limitedJokes.size()));
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public Joke saveJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public void deleteJokeById(Long id) {
        jokeRepository.deleteById(id);
    }
}
