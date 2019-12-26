package cedric.smla.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cedric.smla.Adapter.Adapter_character;
import cedric.smla.Controller.ControllerCharacter;
import cedric.smla.Model.Characters;
import cedric.smla.R;

public class Fragment_character extends Fragment {
    private ControllerCharacter controllerCharacter;
    private RecyclerView recycler;
    private Adapter_character adapter;
    private TabView tabViewModel;
    private List<Characters> characters;
    public Fragment_character(){}
    public static Fragment_character newInstance() {
        return new Fragment_character();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tabViewModel = ViewModelProviders.of(requireActivity()).get(TabView.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_planet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        this.controllerCharacter = new ControllerCharacter(this, sharedPreferences);
        controllerCharacter.start();
        this.characters = controllerCharacter.getList_characters();
    }
    public void setData(List<Characters> results){
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        adapter = new Adapter_character(controllerCharacter.getList_characters());
        recycler.setAdapter(adapter);
    }

}
