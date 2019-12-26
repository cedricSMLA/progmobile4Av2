package cedric.smla.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cedric.smla.Model.Characters;
import cedric.smla.R;
import cedric.smla.View.CharacterActivity;

public class Adapter_character extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Characters> characters;
    private Context context;
    public Adapter_character(List<Characters> characters) {
        this.characters = characters;
        this.context = context;
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
            final Characters characters = this.characters.get(position);
            dataHolder.title.setText(characters.getName());
        }

    }

    @Override
    public int getItemCount() {
        return this.characters.size();
    }
    public void addItem(int position, Characters characters){
        this.characters.add(position, characters);

    }
    public void removeItem(int position){
        this.characters.remove(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public Characters getItem(int position){
        return this.characters.get(position);
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        View layout;
        public DataHolder(View itemView){
            super(itemView);
            layout = itemView;
            title = layout.findViewById(R.id.title);
            icon = layout.findViewById(R.id.view);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CharacterActivity.class);
                    intent.putExtra("characters",getPeoplePosition((String)title.getText()));
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
    public String getPeoplePosition(String name){
        String data = "";
        for(int i = 0; i<this.characters.size(); i++){
            if(this.characters.get(i).getName().equals(name)){
                data = Integer.toString(i);
                break;
            }
        }
        return data;
    }
}

