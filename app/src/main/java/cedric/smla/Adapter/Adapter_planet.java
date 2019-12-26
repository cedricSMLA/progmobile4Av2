package cedric.smla.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cedric.smla.Model.Planets;
import cedric.smla.R;
import cedric.smla.View.PlanetActivity;

public class Adapter_planet extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Planets> planets;
    public Adapter_planet(List<Planets> planets) {
        this.planets = planets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DataHolder){
            final DataHolder dataHolder = (DataHolder)holder;
            final Planets planet= this.planets.get(position);
            dataHolder.name.setText(planet.getName());
        }

    }

    @Override
    public int getItemCount() {
        return this.planets.size();
    }
    public void addItem(int position, Planets planet){
        this.planets.add(position, planet);

    }
    public void removeItem(int position){
        this.planets.remove(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public Planets getItem(int position){
        return this.planets.get(position);
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;
        View layout;
        String url = "";
        public DataHolder(View itemView){
            super(itemView);
            layout = itemView;
            name = layout.findViewById(R.id.name);
            icon = layout.findViewById(R.id.view);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlanetActivity.class);
                    String[] data = getFilmPosition((String)name.getText());
                    intent.putExtra("planet",data[0]);
                    //intent.putExtra("url", data[1]);
                    Log.d("TAG    >>>",intent.getStringExtra("planet"));
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
    public String[] getFilmPosition(String title){
        String [] data = new String[2];
        for(int i = 0; i<this.planets.size(); i++){
            if(this.planets.get(i).getName().equals(title)){
                data[0] = Integer.toString(i);
                //data[1] = this.planets.get(i).getYouTubeUrl();
                break;
            }
        }
        return data;
    }

}
