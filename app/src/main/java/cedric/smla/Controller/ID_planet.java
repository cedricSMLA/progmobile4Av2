package cedric.smla.Controller;

import android.content.SharedPreferences;
import android.util.Log;

import cedric.smla.Model.ApiRest;
import cedric.smla.Model.Planets;
import cedric.smla.View.PlanetActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ID_planet implements Callback<Planets> {
    private String Id;
    private PlanetActivity fa;
    private SharedPreferences sp;
    private Planets planet = new Planets();

    public ID_planet(String id, PlanetActivity fa, SharedPreferences sp) {
        Id = id;
        this.fa = fa;
        this.sp = sp;
    }

    public void start(){
        Log.d("TAG    >>>","CALLING  "+this.Id);
        Call<Planets> planets = ApiRest.get().getIdPlanet(Integer.parseInt(this.Id)+1);
        Log.v("TAG    >>>","END CALLING");
        planets.enqueue(this);
    }

    @Override
    public void onResponse(Call<Planets> call, Response<Planets> response) {
        if (response.isSuccessful()){
            this.planet = response.body();
            //fa.displayData(planet);
        }else{
            Log.d("TAG    >>>","Error   "+response.raw().request().url());
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Planets> call, Throwable t) {
        Log.d("TAG    >>>","Error   "+t.getMessage());

    }


}
