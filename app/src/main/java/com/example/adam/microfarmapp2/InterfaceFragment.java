/**
 * Created by Adam on 11/24/2015.
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//This is a fragment class To easily switch between statistics, history, and settings
//This is all accessible from the home screen

public class InterfaceFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.interface_fragment, container, false);

        //gets the info on all the button widgets
        final Button statsbutton = (Button) view.findViewById(R.id.statisticsbutton);
        final Button historybutton = (Button) view.findViewById(R.id.historybutton);
        final Button settingsbutton = (Button) view.findViewById(R.id.settingsbutton);

        //listens for stats button to be clicked then calls button clicked method
        statsbutton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        statsButtonClicked(v);

                    }
                }
        );

        //listens for history button to be clicked then calls button clicked method
        historybutton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        historyButtonClicked(v);

                    }
                }
        );

        //listens for settings button to be clicked then calls button clicked method
        settingsbutton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        settingsButtonClicked(v);

                    }
                }
        );

        return view;
    }

    //method which changes the active view to the stats screen
    @TargetApi(Build.VERSION_CODES.M)
    public void statsButtonClicked(View view){

        Intent i = new Intent(getActivity().getBaseContext(), Statistics.class);
        startActivity(i);

    }

    //method which changes the active view to the history screen
    @TargetApi(Build.VERSION_CODES.M)
    public void historyButtonClicked(View view){

        Intent i = new Intent(getActivity().getBaseContext(), History.class);
        startActivity(i);

    }

    //method which changes the active view to the settings screen
    @TargetApi(Build.VERSION_CODES.M)
    public void settingsButtonClicked(View view){

        Intent i = new Intent(getActivity().getBaseContext(), Settings.class);
        startActivity(i);

    }

}
