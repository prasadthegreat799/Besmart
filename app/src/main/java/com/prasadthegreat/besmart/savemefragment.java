package com.prasadthegreat.besmart;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class savemefragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Button msendlocationbtn;
    Button mvoicelistenerbtn;
    Button msendlivelocaitonbtn;
    EditText mphonenumber;
    FusedLocationProviderClient fusedLocationProviderClient;
    Context context;


    public savemefragment() {

    }

    public static savemefragment newInstance(String param1, String param2) {
        savemefragment fragment = new savemefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_savemefragment, container, false);

        mphonenumber = (EditText) view.findViewById(R.id.editphonenumber);
        msendlocationbtn = (Button) view.findViewById(R.id.sendlocationbtn);
        mvoicelistenerbtn = (Button) view.findViewById(R.id.voicefunctionbtn);
        msendlivelocaitonbtn = (Button) view.findViewById(R.id.sendlivelocationbtn);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        msendlocationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    sendlocationmethod();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }
        });

        mvoicelistenerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while (true) {
                    //listen for some sound
                    if ("voice" == "gevendata") {
                        sendlocationmethod();
                    }
                }

            }
        });

        msendlivelocaitonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send data to firebase  and to parents
            }
        });
        return view;
    }

    private void sendlocationmethod() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                if(location != null){
                    Geocoder geocoder=new Geocoder(context, Locale.getDefault());
                    try {
                        List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        String msg_data="Latitude:"+addresses.get(0).getLatitude()+" Longitude: "+addresses.get(0).getLongitude()
                                +" Country: "+addresses.get(0).getCountryName()+" Locality: "+addresses.get(0).getLocality();

                        Intent intent=new Intent(context,MainActivity.class);
                        PendingIntent pi= PendingIntent.getActivity(context, 0, intent,0);


                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage("+917997288157", null, msg_data, pi,null);
                        Toast.makeText(context,"send success",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}