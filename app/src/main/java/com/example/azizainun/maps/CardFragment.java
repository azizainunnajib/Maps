package com.example.azizainun.maps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aziza on 6/9/2017.
 */
public class CardFragment extends Fragment{
    ArrayList<Model> listitem = new ArrayList<>();
    RecyclerView myRecyclerView;
    String Bangunan[] = {"Hotel", "Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu"};
    int Imag [] = {R.drawable.sukses, R.drawable.ic_exit, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Initialist();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if  (listitem.size() > 0 & myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapter(listitem));
        }
        myRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Model> list;

        public MyAdapter(ArrayList<Model> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items,parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.titlePlace.setText(list.get(position).getLokasi());
            holder.titlePrice.setText(list.get(position).getPrice());
            holder.coverImageView.setImageResource(list.get(position).getUrut());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titlePlace;
        public TextView titlePrice;
        public ImageView coverImageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            titlePlace = (TextView) itemView.findViewById(R.id.titlePlace);
            titlePrice = (TextView) itemView.findViewById(R.id.titlePrice);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
        }
    }

    public void Initialist() {
        listitem.clear();

        for (int i=0; i<4 ; i++) {
            Model item = new Model();
            item.setLokasi(Bangunan[i]);
            item.setPrice(Bangunan[i]);
            item.setUrut(Imag[i]);
            listitem.add(item);
        }
    }
}
