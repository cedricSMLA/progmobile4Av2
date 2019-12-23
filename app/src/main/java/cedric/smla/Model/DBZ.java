package cedric.smla.Model;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DBZ {
    @GET("planet/?format=json")
    Call<PlanetList> getAllplanet();
    @GET("character/?format=json")
    Call<CharacterList> getAllPeople();
}
