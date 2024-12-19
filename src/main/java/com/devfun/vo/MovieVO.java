package com.devfun.vo;

public class MovieVO {
    private String movie_name;
    private String director;
    private String types;

    // 기본 생성자
    public MovieVO() {}

    // Getter 및 Setter 메서드
    public String getMovieName() {
        return movie_name;
    }

    public void setMovieName(String movieName) {
        this.movie_name = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    // toString 메서드 (디버깅용)
    @Override
    public String toString() {
        return "MovieVO{" +
                "movieName='" + movie_name + '\'' +
                ", director='" + director + '\'' +
                ", types='" + types + '\'' +
                '}';
    }
}
