package com.example.azizainun.maps;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingInstagram extends Fragment {
    private int currentPage = 0;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model_data_ig2> data_ig = new ArrayList<>();
    private static final String TAG = "TrendingInstagram";
    LinearLayoutManager mLayoutManager;

    public TrendingInstagram() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        data_ig.clear();
        adapter = new Adapter(data_ig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ins_tran_zit, container, false);
        recyclerView = view.findViewById(R.id.rv_trending);
        recyclerView.setAdapter(adapter);
        mLayoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                moredataload();
            }
        });
        Log.d(TAG, "onCreateView: ");
        if (data_ig.size() == 0) {
            loadData();
            Log.d(TAG, "if data_ig =0: " + data_ig.size());
        } else {
            Initialist(recyclerView);
        }
        // Inflate the layout for this fragment
        return view;
    }

    private void Initialist(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(MyLayoutManager);
        Log.d(TAG, "Initialist: ");
        if (data_ig.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(new Adapter(data_ig));
        }
    }

    private void moredataload() {
        Log.d(TAG, "moredataload: " +String.valueOf(currentPage));
        currentPage++;
        Log.d(TAG, "moredataload1: " +String.valueOf(currentPage));
        loadData();
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        new Database().mReadDataPagination("Trending/data", "key",5, currentPage, new Database.OnGetDataListener() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: " + String.valueOf(currentPage));

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                Log.d(TAG, "onSuccess0: " + data.getChildrenCount());
                if(!data.hasChildren()) {
                    Toast.makeText(getContext(), "ini yang terakhir", Toast.LENGTH_SHORT).show();
                    currentPage--;
                }
                for (DataSnapshot snapshot:data.getChildren()) {
                    String url= (String) snapshot.child("images/standard_resolution/url").getValue();
                    Long id_location =  (Long) snapshot.child("location/id").getValue();
                    Double lat = (Double) snapshot.child("location/latitude").getValue();
                    Double longi = (Double) snapshot.child("location/longitude").getValue();
                    String nama_location = (String) snapshot.child("location/name").getValue();
                    String username_ig = (String) snapshot.child("user/username").getValue();
                    String caption = (String) snapshot.child("caption/text").getValue();
                    long key = (long) snapshot.child("key").getValue();
                    Model_data_ig2 model_data_ig2 = new Model_data_ig2();
                    model_data_ig2.setUrl(url);
                    model_data_ig2.setId_location(id_location);
                    model_data_ig2.setLat(lat);
                    model_data_ig2.setLng(longi);
                    model_data_ig2.setNama_lokasi(nama_location);
                    model_data_ig2.setUsername_ig(username_ig);
                    model_data_ig2.setCaption(caption);
                    data_ig.add(model_data_ig2);
                    Log.d(TAG, "onSuccess: urut " + String.valueOf(key));
                }
                recyclerView.setHasFixedSize(true);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mLayoutManager);
                if (data_ig.size() > 0 & recyclerView != null) {
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
//                    Log.d(TAG, "onSuccess: "+ data_ig.get(1).getKotakab());
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d(TAG, "onFailed: ");
            }
        });
    }

    public class Adapter extends RecyclerView.Adapter<myViewHolder>{
        ArrayList<Model_data_ig2> data_ig;

        public Adapter(ArrayList<Model_data_ig2> data_ig) {
            this.data_ig = data_ig;
        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_unit,parent, false);
            myViewHolder holder = new myViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.nama_tempat.setText("Di " + data_ig.get(position).getNama_lokasi());
            holder.nama_user.setText("Diambil oleh " + data_ig.get(position).getUsername_ig());
            holder.caption.setText(data_ig.get(position).getCaption());

            Glide
                    .with(getActivity())
                    .load(data_ig.get(position).getUrl())
                    .asBitmap()
                    .into(holder.gambar);
        }

        @Override
        public int getItemCount() {
            return data_ig.size();
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        MyTextView nama_tempat, nama_user;
        ExpandableTextView caption;
        
        public myViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            nama_tempat = itemView.findViewById(R.id.nama_tempat);
            nama_user = itemView.findViewById(R.id.nama_user);
            caption = itemView.findViewById(R.id.caption);
        }
    }
}
