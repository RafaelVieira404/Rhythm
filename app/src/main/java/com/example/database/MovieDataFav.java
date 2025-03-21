package com.example.database;

public class MovieDataFav {
        private String movieName;
        private String movieScore;

        public MovieDataFav(String movieName, String movieScore) {
            this.movieName = movieName;
            this.movieScore = movieScore;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getMovieIdKey() {
            return movieScore;
        }
}
