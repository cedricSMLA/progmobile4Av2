package cedric.smla.Controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cedric.smla.Model.ApiRest;
import cedric.smla.Model.PlanetList;
import cedric.smla.Model.Planets;
import cedric.smla.View.Fragment_planet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerPlanet implements Callback<PlanetList> {
    private List<Planets> results = new ArrayList<>();
    private Fragment_planet fragment_planet;
    public ControllerPlanet(Fragment_planet ff){
        this.fragment_planet = ff;
    }

    public void start(){
        Call<PlanetList> films = ApiRest.get().getAllplanet();
        films.enqueue(this);
    }

    @Override
    public void onResponse(Call<PlanetList> call, Response<PlanetList> response) {
        if(response.isSuccessful()) {
            PlanetList list = response.body();
            this.results = list.getListePlanet();
            //setUrl(this.results);
            Log.v("TAG    >>>>>>>","Response Success >>>>>>"+this.results.size());
            this.fragment_planet.setData(this.results);
        }else{
            System.out.println(response.raw().request().url());

        }

    }

    @Override
    public void onFailure(Call<PlanetList> call, Throwable t) {
        Log.v("TAG    >>>","Response failure >>>"+t.getMessage());
    }
    public List<Planets> getResults(){
        return this.results;
    }

}
