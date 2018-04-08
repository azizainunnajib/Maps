package com.example.azizainun.maps.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.azizainun.maps.services.Database;
import com.example.azizainun.maps.ui.ui_custom.EndlessRecyclerOnScrollListener;
import com.example.azizainun.maps.ItemClickCard;
import com.example.azizainun.maps.Model.Model;
import com.example.azizainun.maps.R;
import com.example.azizainun.maps.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

/**
 * Created by aziza on 6/9/2017.
 */
public class CardFragment extends Fragment {
    private int currentPage = 0;
    RecyclerView myRecyclerView;
    MyAdapter mAdapter;
    private static final String TAG = "CardFragment";
    ArrayList<Model> listunit = new ArrayList<>();
    String Bangunan[] = {"Hotel", "Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu"};
    int Imag [] = {R.drawable.sukses, R.drawable.ic_exit, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listunit.clear();
        mAdapter = new MyAdapter(listunit);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String UID = User.getUID();
        final View view = inflater.inflate(R.layout.fragment_card, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(getContext());
        myRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                moredataload();
            }
        });
        if (listunit.size() == 0) {
            dataload();
        } else {
            Initialist(myRecyclerView);
        }
        return view;
    }

    private void moredataload() {
        currentPage++;
        dataload();
    }

    private void dataload() {
        new Database().mReadDataOnce("DetailUnit", new Database.OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                if(!data.hasChildren()) {
                    Toast.makeText(getContext(), "ini yang terakhir", Toast.LENGTH_SHORT).show();
                    currentPage--;
                }

                Model item = new Model();
                for (DataSnapshot snapshot : data.getChildren()) {
                    Model model = snapshot.getValue(Model.class);
                    listunit.add(model);
                }
                myRecyclerView.setHasFixedSize(true);
                LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
                MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(MyLayoutManager);
                if (listunit.size() > 0 & myRecyclerView != null) {
                    myRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onSuccess: "+ listunit.get(1).getKotakab());
                }
                int t = listunit.size();
                int v = t++;
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Model> list;
        private Context context;

        public MyAdapter(ArrayList<Model> Data) {
            this.list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items,parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.titlePrice.setText(list.get(position).getHarga() + " untuk " + list.get(position).getTipe_bangunan());
            holder.titleName.setText(list.get(position).getNama_tempat());
            holder.titlePlace.setText("di " + list.get(position).getKotakab());
//            holder.coverImageView.setImageResource(list.get(position).getUrut());

            Glide
                    .with(getActivity())
                    .load(list.get(position).getUrl())
                    .asBitmap()
                    .centerCrop()
//                    .override(50, 50)
                    .dontAnimate()
                    .into(holder.coverImageView);

            holder.setItemClickCard(new ItemClickCard() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailUnit.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", list.get(position));
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);

                    //Toast.makeText(getActivity(), "terclick " + list.get(position), Toast.LENGTH_LONG).show();



                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titlePlace;
        TextView titlePrice;
        TextView titleName;
        ImageView coverImageView;
        CardView cardView;
        private ItemClickCard itemClickCard;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            titlePlace = (TextView) itemView.findViewById(R.id.kotakab);
            titlePrice = (TextView) itemView.findViewById(R.id.titlePrice);
            titleName = (TextView) itemView.findViewById(R.id.titlePlace);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);

            itemView.setOnClickListener(this);
        }

        public void setItemClickCard(ItemClickCard itemClickCard) {
            this.itemClickCard = itemClickCard;
        }

        @Override
        public void onClick(View v) {
            itemClickCard.onClick(v, getAdapterPosition());
        }
    }

    public void Initialist(RecyclerView myRecyclerView) {
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(MyLayoutManager);
        if (listunit.size() > 0 & myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapter(listunit));
        }
        /*for (int i=0; i<4 ; i++) {
            Model item = new Model();
            item.setLokasi(Bangunan[i]);
            item.setHarga(Bangunan[i]);
            item.setUrut(Imag[i]);
            listunit.add(item);
        }*/
    }
}
