package com.example.adam.microfarmapp2;

/**
 * Created by Adam on 11/30/2015.
 */
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

/**
 * Created by Adam on 11/24/2015.
 */
public class InterfaceFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.interface_fragment, container, false);

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

        historybutton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        historyButtonClicked(v);

                    }
                }
        );

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

        Intent i = new Intent(getContext(), Statistics.class);
        startActivity(i);



        //overridePendingTransition(R.transition.animation1, R.transition.animation2);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void historyButtonClicked(View view){

        Intent i = new Intent(getContext(), History.class);
        startActivity(i);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void settingsButtonClicked(View view){

        Intent i = new Intent(getContext(), Settings.class);
        startActivity(i);

    }

}
