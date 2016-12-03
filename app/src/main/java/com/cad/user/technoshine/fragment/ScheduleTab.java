package com.cad.user.technoshine.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cad.user.technoshine.R;
import com.cad.user.technoshine.RecyclerItemClickListener;
import com.cad.user.technoshine.RecyclerViewSchedule;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScheduleTab extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ImageView imageView;


    public static ScheduleTab newInstance() {
        return new ScheduleTab();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        imageView = (ImageView) view.findViewById(R.id.downloadSchedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewMaterialAdapter(new RecyclerViewSchedule());
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                File file = new File("/sdcard/technoshineSchedule.jpg");
                if (!file.exists()) {
                    if (haveNetworkConnection()) {
                        new DownloadImg().execute();
                    } else {
                        Toast.makeText(getContext(), "Turn on Net Connection to download Schedule", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Schedule already downloaded @ "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
    }


    private class DownloadImg extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Bitmap doInBackground(String... args) {
            Bitmap myBitmap = null;
            try {
                URL url = new URL("http://2015.cadnitd.co.in/images/schedule.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return myBitmap;
        }

        protected void onPostExecute(Bitmap mybitmap) {


            String filename = String.valueOf(String.format("/sdcard/technoshineSchedule.jpg"));

            try {
                FileOutputStream stream = new FileOutputStream(filename);
                ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                mybitmap.compress(Bitmap.CompressFormat.JPEG, 85, outstream);
                byte[] byteArray = outstream.toByteArray();

                stream.write(byteArray);
                stream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (
                    IOException e
                    )

            {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), "Downloaded Schedule to SDCARD", Toast.LENGTH_SHORT).
                    show();
        }

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        Context context = getContext().getApplicationContext();


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}

