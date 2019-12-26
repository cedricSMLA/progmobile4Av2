package cedric.smla.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cedric.smla.Adapter.Adapter_planet;
import cedric.smla.Controller.ControllerPlanet;
import cedric.smla.Model.Planets;
import cedric.smla.R;


public class Fragment_planet extends Fragment {
    private TabView tabViewModel;
    private ControllerPlanet controllerPlanet;
    private List<Planets> planets;
    private RecyclerView recycler;
    private Adapter_planet adapter;
    public Fragment_planet(){}
    public static Fragment_planet newInstance() {
        return new Fragment_planet();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        tabViewModel = ViewModelProviders.of(requireActivity()).get(TabView.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //setData();
        //System.out.println(films.isEmpty());
        //View view = inflater.inflate(R.layout.film_holderview, container, false);
        //TextView tv = view.findViewById(R.id.title1);
        //tv.setText(films.get(0).getTitle());
        return inflater.inflate(R.layout.activity_planet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        this.controllerPlanet = new ControllerPlanet(this);
        controllerPlanet.start();
        this.planets = controllerPlanet.getResults();
    }
    public void setData(List<Planets> results){
        recycler = getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        adapter = new Adapter_planet(controllerPlanet.getResults());
        recycler.setAdapter(adapter);
    }
}
