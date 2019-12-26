package cedric.smla.Controller;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cedric.smla.Model.ApiRest;
import cedric.smla.Model.CharacterList;
import cedric.smla.Model.Characters;
import cedric.smla.View.CharacterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ID_character implements Callback<Characters> {
    private String Id;
    private CharacterActivity pa;
    private SharedPreferences sharedPreferences;

    public ID_character(String id, CharacterActivity fa, SharedPreferences prefs) {
        this.Id = id;
        this.pa = fa;
        this.sharedPreferences = prefs;
    }

    public void start(){
        //Log.d("TAG    >>>","CALLING");
        Log.d("TAG    >>>","PARSED ID >>>>>"+this.Id);
        //Integer id = Integer.parseInt(this.Id);

        Call<Characters> characters = ApiRest.get().getIdCharacter(Integer.parseInt(this.Id)+1);
        Log.v("TAG    >>>","END CALLING");
        characters.enqueue(this);
    }
    @Override
    public void onResponse(Call<Characters> call, Response<Characters> response) {
        if (response.isSuccessful()){
            Characters characters = response.body();
            storeData(characters);
            pa.displayData(characters);
        }else{
            Log.d("TAG    >>>","Error   "+response.raw().request().url());
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Characters> call, Throwable t) {
        Log.d("TAG    >>>","Error   "+t.getMessage());

    }
    private void storeData(Characters characters) {
        Gson gson = new Gson();
        String data = gson.toJson(characters);
        sharedPreferences
                .edit()
                .putString("cle_string" + this.Id, data)
                .apply();
    }

    private Characters getDataFromCache() {
        String characters = sharedPreferences.getString("cle_string"  + this.Id, "");
        if(characters != null && !TextUtils.isEmpty(characters)){
            Type listType = new TypeToken<Characters>(){}.getType();
            Characters character = new Gson().fromJson(characters, listType);
            return character;
        }
        return new Characters();
    }
}
