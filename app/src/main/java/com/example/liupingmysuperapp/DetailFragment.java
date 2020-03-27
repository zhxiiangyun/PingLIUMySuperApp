package com.example.liupingmysuperapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private static final String ARG_FACT = "fact";
    private String fact;

    public DetailFragment() {

    }

    public static DetailFragment newInstance(String fact) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FACT, fact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fact = getArguments().getString(ARG_FACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        TextView textView = getView().findViewById(R.id.textView);
        textView.setText(fact);
        String urlFact = fact.replaceAll("[%/\\\\/]", "");
        String[] first5words = Arrays.copyOfRange(urlFact.split(" ", 200),0,5);
        String words = String.join(" ", first5words)+"...";
        System.out.println(words);
        RequestCreator rc = Picasso.get().load("https://cataas.com/cat/says/"+words);
        ImageView imageView = getView().findViewById(R.id.imageView);
        rc.into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                ProgressBar progressBar1 = getView().findViewById(R.id.progressBar1);
                progressBar1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
