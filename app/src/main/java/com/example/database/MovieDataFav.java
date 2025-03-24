package com.example.database;

public class MovieDataFav {
        private String movieName;
        private String movieKey;

        public MovieDataFav(String movieName, String movieKey) {
            this.movieName = movieName;
            this.movieKey = movieKey;
        }

        public MovieDataFav() {
        }

        public String getMovieName() {
            return movieName;
        }

        public String getMovieIdKey() {
            return movieKey;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }
        public void setMovieKey(String movieKey) {
            this.movieKey = movieKey;
        }
}
