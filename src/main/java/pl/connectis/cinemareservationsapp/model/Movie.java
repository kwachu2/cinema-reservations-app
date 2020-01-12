package pl.connectis.cinemareservationsapp.model;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String category;
    private int length;
    private String description;
    private int ageLimit;

    public Movie() {
    }

    public Movie(long id, String name, String category, int length, String description, int ageLimit) {
        this.id = id;
        this.title = name;
        this.category = category;
        this.length = length;
        this.description = description;
        this.ageLimit = ageLimit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", category='" + category + '\'' +
                ", length=" + length +
                ", description='" + description + '\'' +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
