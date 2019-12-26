package cedric.smla.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cedric.smla.Model.Characters;
import cedric.smla.R;

public class CharacterActivity extends AppCompatActivity {

    private TextView name;
    private TextView species;
    private TextView status;
    private TextView series;
    private TextView gender;
    private TextView url;
    private TextView image;
    private TextView origin_planet;
    private String donnee;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Intent activ = getIntent();
        String donnee = activ.getStringExtra("people");
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }


    public void setDonnee(String name){
        this.donnee = name;
    }
    public void displayData(Characters characters) {
        this.name = findViewById(R.id.name);
        this.species = findViewById(R.id.species);
        this.status = findViewById(R.id.status);
        this.gender = findViewById(R.id.gender);
        this.url = findViewById(R.id.url);
        this.image = findViewById(R.id.image);
        this.origin_planet = findViewById(R.id.origin_planet);
        name.setText("Title    : " + characters.getName());
        gender.setText("Gender   : " + characters.getGender());
        species.setText("Total Species    : " + characters.getSpecies());
        url.setText("Url    : : " + characters.getUrl());
        origin_planet.setText("Origin planet   : " + characters.getOrigin_planet());
    }
}
