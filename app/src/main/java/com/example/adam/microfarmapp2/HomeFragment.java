package com.example.adam.microfarmapp2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;

/**
 * Created by Adam on 11/24/2015.
 * This Class is created for the Home Button Fragment
 * This will go on every Screen in the Interface to provide a way to get back to home.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        final Button button = (Button) view.findViewById(R.id.homebutton1);

        //listens for home button to be clicked then calls button clicked method
        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        buttonClicked(v);

                    }
                }
        );
        return view;
    }

    //method which changes the active view to the main activity
    @TargetApi(Build.VERSION_CODES.M)
    public void buttonClicked(View view){

        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);

    }

}

