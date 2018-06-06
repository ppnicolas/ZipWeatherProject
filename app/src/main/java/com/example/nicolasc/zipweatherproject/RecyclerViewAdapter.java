package com.example.nicolasc.zipweatherproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>{

    private final Context context;
    private final List<WeatherInfo> listZipWeather;
    private int itemLayout;
    private AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, List<WeatherInfo> list, int itemLayout) {

        Log.d("WHEATHER", "++++++ RecyclerViewSchoolsListAdapter  list.size=" + list.size());

        this.context = context;
        this.listZipWeather = list;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("WHEATHER", "++++++ onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_layout, parent, false);

        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final WeatherInfo info = listZipWeather.get(position);

        Log.d("WHEATHER", "++++++ onCreateViewHolder position=" + position);

        //Setting text views
        holder.textZipCity.setText(info.getCityName());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("WHEATHER", "++++++ OnClickListener city=" + info.getCityName());

                Intent intent = new Intent(view.getContext(), ActivityDetails.class);

                Bundle bundle = new Bundle();
                bundle.putString("value", info.getCityId());
                bundle.putString("type", "city");

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        };
        holder.textZipCity.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return listZipWeather == null? 0 : listZipWeather.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textZipCity;

        public ItemViewHolder(View view) {
            super(view);

            textZipCity = (TextView) view.findViewById(R.id.text_view);

        }
    }
}
