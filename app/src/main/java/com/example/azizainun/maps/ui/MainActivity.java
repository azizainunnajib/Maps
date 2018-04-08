package com.example.azizainun.maps.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.azizainun.maps.services.Database;
import com.example.azizainun.maps.R;
import com.example.azizainun.maps.User;
import com.example.azizainun.maps.ui.AddUnit.AddUnitAkhir;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button choose_image, upload_image;
    ImageView imageView;
    int PICK_IMAGE_REQUEST = 123;
    Uri filePath;
    ProgressDialog progressDialog;
    ImageView profil;
    ImageView explore;
    ImageView search;
    ImageView home;
    ImageView trending;
    ImageView ic_search;
    LinearLayout lin_search;
    AutoCompleteTextView text_search;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseUser mFirebaseUser;
    private String UIDp;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_navigator);

        //for R.id.home_navigator
        profil = (ImageView) findViewById(R.id.profil);
        explore = (ImageView) findViewById(R.id.explore);
        search = (ImageView) findViewById(R.id.search);
        home = (ImageView) findViewById(R.id.home);
        trending = (ImageView) findViewById(R.id.trending);
        lin_search = (LinearLayout) findViewById(R.id.linear_search);
        ic_search = (findViewById(R.id.ic_search));
        text_search = findViewById(R.id.search_bar);

        profil.setOnClickListener(this);
        explore.setOnClickListener(this);
        search.setOnClickListener(this);
        home.setOnClickListener(this);
        trending.setOnClickListener(this);
        ic_search.setOnClickListener(this);

//        setTitle("");
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
//        EditText search = (EditText) findViewById(R.id.search);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//
//        choose_image = (Button) findViewById(R.id.choose_image);
//        upload_image = (Button) findViewById(R.id.upload_image);
//        imageView = (ImageView) findViewById(R.id.image_upload);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("uploading");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            Intent i = new Intent(this, Login.class);
            i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else {
            UIDp = User.getUID();
            User.setUID();
            MainDisplay();
//            UIDp = mFirebaseUser.getUid();
//            Model userid= new Model();
//            userid.setUID(UIDp);
        }

//        final AutoCompleteTextView kotakab = (AutoCompleteTextView) view.findViewById(R.id.kotakab);
        String[] Skotakab = getResources().getStringArray(R.array.kotakab);
        ArrayAdapter<String> Akotakab = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Skotakab);
        text_search.setAdapter(Akotakab);

        /*viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/
    }
        /*Fragment f;
        f = new CardFragment();
        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, f);
            ft.commit();
        }*/

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CardFragment(), "ONE");
        adapter.addFragment(new TrendingInstagram(), "TWO");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        int id = v.getId();
        switch (v.getId()) {
            case R.id.profil:
                Log.d("mausk profil", "benar");
                Toast.makeText(this, "profil", Toast.LENGTH_SHORT).show();
                lin_search.setVisibility(View.GONE);
                fragment = new Profil();
                break;
            case R.id.explore:
                Log.d("mausk explore", "benar");
                Toast.makeText(this, "explore", Toast.LENGTH_SHORT).show();
                lin_search.setVisibility(View.GONE);
                break;
            case R.id.search:
                text_search.getText().clear();
                if (lin_search.getVisibility() == View.GONE) {
                    Log.d("mausk search", "benar");
                    Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                    lin_search.setVisibility(View.VISIBLE);
                } else {
                    lin_search.setVisibility(View.GONE);
                }
                break;
            case R.id.home:
                Log.d("mausk home", "benar");
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                text_search.getText().clear();
                lin_search.setVisibility(View.GONE);
                fragment = new CardFragment();
                break;
            case R.id.trending:
                Log.d("mausk trending", "benar");
                Toast.makeText(this, "trending", Toast.LENGTH_SHORT).show();
                text_search.getText().clear();
                lin_search.setVisibility(View.GONE);
                fragment = new TrendingInstagram();
                break;
            case R.id.ic_search:
                Log.d("masuk ic_search", "onClick: ");
                String text_sch = text_search.getText().toString();
                if (!text_sch.equals("")){
                    Log.d(TAG, "onClick: " + text_sch);
                    getSearchText(text_sch);
                }
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null);
            ft.commit();
        }
    }

    private void getSearchText(String text_sch) {
        Log.d(TAG, "getSearchText: " + text_sch);
        new Database().SearchDatabase("DetailUnit", "kotakab", text_sch, new Database.OnGetDataListener() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: masuk OnStart");
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for (DataSnapshot childSnapshot: data.getChildren()) {
                    Log.d(TAG, "onSuccess: " + childSnapshot.toString());
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d(TAG, "onFailed: " + databaseError.getMessage());
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

        /*choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image*//*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);

            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filePath != null) {
                    progressDialog.show();
                    StorageReference childRef = storageReference.child("images/image.jpg");
                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Upload Succes", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "asgagaw", Toast.LENGTH_SHORT).show();
                }
            }
        });*/



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public void onBackPressed (){
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
//        drawer.closeDrawer(GravityCompat.START);
        if (getFragmentManager().getBackStackEntryCount() > 0){
            Toast.makeText(this, "onbackpresed", Toast.LENGTH_SHORT).show();
        }
        else if(lin_search.getVisibility() == View.VISIBLE){
            lin_search.setVisibility(View.GONE);
        }
        else {
            super.onBackPressed();
        }
    }

    public void butt(View view) {
        Toast.makeText(this, "okeeee", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }


    private void displaySelectedScreen(int id) {
        Fragment fragment = new CardFragment();
        switch (id) {
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                fragment = new CardFragment();
                break;
            case R.id.profil:
                Toast.makeText(this, "profil", Toast.LENGTH_SHORT).show();
                fragment = new Profil();
                break;
            case R.id.setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                fragment = new AddUnitAkhir();
                break;
            case R.id.search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                fragment = new Penyewa();
                break;
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                mFirebaseAuth.signOut();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                finish();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void MainDisplay () {
        Fragment f;
        f = new CardFragment();
            if (f != null) {
                Log.d("mausk Main display", "benar");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, f);
                ft.commit();
        }
    }
}
