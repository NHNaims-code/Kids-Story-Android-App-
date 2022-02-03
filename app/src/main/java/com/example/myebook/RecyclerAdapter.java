package com.example.myebook;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myebook.Models.DataModel;


import org.parceler.Parcels;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<DataModel> resourceData;

    public RecyclerAdapter(Context context, ArrayList<DataModel> resourceData) {
        this.context = context;
        this.resourceData = resourceData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

        Glide.with(context)
                .load(resourceData.get(position).getthumbnail_url())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.lion_and_mouse)
                .into(holder.thumbnail);
//        holder.thumbnail.setImageResource(resourceData.get(position).getThumbnail());
        holder.title.setText(resourceData.get(position).getTitle());
        holder.description.setText(resourceData.get(position).getDescription());

        holder.gridItem.setOnClickListener(new View.OnClickListener() {

            Parcelable parcelable = Parcels.wrap(resourceData.get(position));
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, StoryDetails.class);
                intent.putExtra("data", parcelable);



                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resourceData.size();
    }

    //    My View Holder For Setting Data
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, moral;
        ImageView thumbnail;
        CardView gridItem;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.storyTitle);
            description = itemView.findViewById(R.id.storyShortDescription);
            thumbnail = itemView.findViewById(R.id.storyThumbnail);
            gridItem = itemView.findViewById(R.id.gridItem);

        }
    }
}
