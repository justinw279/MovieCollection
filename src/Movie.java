public class Movie {


    String title;
    String cast;
    String director;
    String overview;
    int runtime;
    double userRating;


    public Movie(String t, String c, String d, String o, int r, double u) {
        title = t;
        cast = c;
        director = d;
        overview = o;
        runtime = r;
        userRating = u;
    }


    public String getTitle() {
        return title;
    }


    public String getCast() {
        return cast;
    }


    public String getDirector() {
        return director;
    }


    public String getOverview() {
        return overview;
    }


    public int getRuntime() {
        return runtime;
    }


    public double getUserRating() {
        return userRating;
    }


    public String toString() {
        return "Title: " + getTitle() + "\nRuntime: " + getRuntime() + "\nDirected by: " + getDirector() + "\nCast: " + getCast() + "\nOverview: " + getOverview() + "\nUser rating: " + getUserRating();
    }




}
