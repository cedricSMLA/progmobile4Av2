package cedric.smla.Controller;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cedric.smla.Model.ApiRest;
import cedric.smla.Model.CharacterList;
import cedric.smla.Model.Characters;
import cedric.smla.View.Fragment_character;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerCharacter implements Callback<CharacterList>{
    private List<Characters> list_characters = new ArrayList<>();
    private Fragment_character character_fragment;
    private SharedPreferences sharedPreferences;
    public ControllerCharacter(Fragment_character ff, SharedPreferences sharedPreferences) {
        this.character_fragment = ff;
        this.sharedPreferences = sharedPreferences;
    }
    public ControllerCharacter(){}

    public void start(){
        Log.d("TAG    >>>","CALLING");
        Call<CharacterList> charaters = ApiRest.get().getAllCharaters();
        Log.v("TAG    >>>","END CALLING");
        charaters.enqueue(this);
    }

    @Override
    public void onResponse(Call<CharacterList> call, Response<CharacterList> response) {
        if(response.isSuccessful()) {
            CharacterList list = response.body();
            this.list_characters = list.getListeCharacters();
            Log.v("TAG    >>>>>>>","Response Success >>>>>>"+this.list_characters.size());
                this.character_fragment.setData(this.list_characters);
            }else{
                Log.v("TAG    >>>","Response Error");
                System.out.println(response.raw().request().url());
            }
        }

        @Override
        public void onFailure(Call<CharacterList> call, Throwable t) {
            Log.v("TAG    >>>","Response failure >>>"+t.getMessage());
        }
        public List<Characters> getList_characters(){
            return this.list_characters;
        }

        private void storeData(List<CharacterList> people) {
            Gson gson = new Gson();
            String data = gson.toJson(people);
            sharedPreferences
                    .edit()
                    .putString("cle_string", data)
                    .apply();
        }

        private List<Characters> getDataFromCache() {
            String data = sharedPreferences.getString("cle_string", "");
            if (data != null && !TextUtils.isEmpty(data)) {
                Type listType = new TypeToken<CharacterList>() {
                }.getType();
                List<Characters> characters_ = new Gson().fromJson(data, listType);
                return characters_;
            }
            return new ArrayList<>();
        }
    }
