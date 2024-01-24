public class ShowTime {
    private int id;
    private int auditoriumID;
    private int movieID;
    private String startTime;
    private String endTime;
    private String date;
    private double price;

    public ShowTime(int id, int auditoriumID, int movieID, String startTime, String endTime, String date, double price) {
        this.id = id;
        this.auditoriumID = auditoriumID;
        this.movieID = movieID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuditoriumID() {
        return auditoriumID;
    }

    public void setAuditoriumID(int auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
