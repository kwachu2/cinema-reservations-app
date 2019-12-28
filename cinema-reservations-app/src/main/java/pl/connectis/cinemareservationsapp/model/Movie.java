package pl.connectis.cinemareservationsapp.model;

public class Movie {
    private long id;
    private String name;
    private String category;
    private int length;
    private String description;
    private int ageLimit;

    public Movie() {
    }

    public Movie(long id, String name, String category, int length, String description, int ageLimit) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", length=" + length +
                ", description='" + description + '\'' +
                ", ageLimit=" + ageLimit +
                '}';
    }
}