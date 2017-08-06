package com.example.azizainun.maps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static com.example.azizainun.maps.R.id.info;

/**
 * Created by aziza on 6/9/2017.
 */
public class CardFragment extends Fragment {
    RecyclerView myRecyclerView;
    ArrayList<Model> listitem = new ArrayList<>();
    String Bangunan[] = {"Hotel", "Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu"};
    int Imag [] = {R.drawable.sukses, R.drawable.ic_exit, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses};

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
        myRecyclerView.setLayoutManager(MyLayoutManager);
        if  (listitem.size() > 0 & myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapter(listitem));
        }

        int t = listitem.size();

        return view;
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
            holder.titlePlace.setText(list.get(position).getLokasi());
            holder.titlePrice.setText(list.get(position).getPrice());
            holder.coverImageView.setImageResource(list.get(position).getUrut());

            holder.setItemClickCard(new ItemClickCard() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailUnit.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Model", list.get(position));
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
        ImageView coverImageView;
        CardView cardView;
        private ItemClickCard itemClickCard;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            titlePlace = (TextView) itemView.findViewById(R.id.titlePlace);
            titlePrice = (TextView) itemView.findViewById(R.id.titlePrice);
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

    public void Initialist() {
        listitem.clear();

        FirebaseDatabase databaseReference = FirebaseDatabase.getInstance();
        databaseReference.getReference().child("Home").child("ZrUnzGpRW3PrRk9f56B8VcWPxMJ3").limitToLast(4).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                Model item = new Model();
//                ArrayList<Model> mod = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Model model = snapshot.getValue(Model.class);
//                    mod.add(sewa);
                    String lokasi = model.getLokasi();
                    String price = model.getPrice();
                    int urut = model.getUrut();
                    item.setLokasi(lokasi);
                    item.setPrice(price);
                    item.setUrut(urut);
                    listitem.add(item);
                    ArrayList s = listitem;
                    String aw = lokasi;
                    Bangunan [i] = lokasi;
                    String asq = Bangunan[0];
                    i++;
                }

                ArrayList qw = listitem;
                int w = listitem.size();
            }

            String as = Bangunan[0];
            String as1 = Bangunan[1];
            String as2 = Bangunan[2];
            String as3 = Bangunan[3];
            String as4 = Bangunan[4];
            String a = User.getUID();
            String a1 = User.getUID();

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*for (int i=0; i<4 ; i++) {
            Model item = new Model();
            item.setLokasi(Bangunan[i]);
            item.setPrice(Bangunan[i]);
            item.setUrut(Imag[i]);
            listitem.add(item);
        }*/
    }
}
