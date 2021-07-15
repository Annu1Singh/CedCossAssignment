package in.sundram.cedcossassignment;

public class Config {
    public static String getSearchURL(String movieName) {
//        https://api.themoviedb.org/3/search/movie?api_key=e993c390c1257911397532b19da6e2df&query=Black
        return Constant.BASE_URL + "api_key=" + Constant.API_KEY + "&language=en-US&query=" + movieName;

    }
}
