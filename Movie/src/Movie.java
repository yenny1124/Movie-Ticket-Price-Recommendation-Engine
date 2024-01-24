public class Movie {
    private int id;
    private String title;
    private String description;
    private String releaseDate;
    private String removalDate;
    private Boolean isAvailable;
    private String isan;

    public Movie(int id, String title, String description, String releaseDate, String removalDate, Boolean isAvailable, String isan) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.removalDate = removalDate;
        this.isAvailable = isAvailable;
        this.isan = isan;
    }

    public int getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRemovalDate() {
        return removalDate;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public String getIsan() {
        return isan;
    }


}
