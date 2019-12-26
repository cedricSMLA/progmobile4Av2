package cedric.smla.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface DBZ {
    @GET("planet/?format=json")
    Call<PlanetList> getAllplanet();
    @GET("planet/{id}/")
    Call<Planets> getIdPlanet(@Path("id") int idPlanet);
    @GET("character/?format=json")
    Call<CharacterList> getAllCharaters();
    @GET("character/{id}/?format=json")
    Call<Characters> getIdCharacter(@Path("id") int idCharacter);
}
