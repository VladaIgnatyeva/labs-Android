package com.example.lab3_fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Galery extends Fragment{
    public static ImageView Im0;
    public static ImageView Im1;
    public static ImageView Im2;
    public static ImageView Im3;
    public static ImageView Im4;
    public static ImageView Im5;
    public static ImageView Im6;
    public static ImageView Im7;
    public static int counter =0;
    public static int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.scroll_fragment, container, false);
        Button AddButton = (Button) view.findViewById(R.id.Add);

        Im0 = view.findViewById(R.id.I0);
        Im1 = view.findViewById(R.id.I1);
        Im2 = view.findViewById(R.id.I2);
        Im3 = view.findViewById(R.id.I3);
        Im4 = view.findViewById(R.id.I4);
        Im5 = view.findViewById(R.id.I5);
        Im6 = view.findViewById(R.id.I6);
        Im7 = view.findViewById(R.id.I7);

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (flag == 0)
                {
                    flag = 1;
                    Fragment fragment = null;
                    fragment = new HeaderFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.add(R.id.TopScreenFragment, fragment);
                    ft.commit();
                }
            }
        });

        return view;
    }

    public static void AddImage(Uri image1)
    {
        switch (counter)
        {
            case 0:
                Im0.setImageURI(image1);
                counter += 1;
                break;
            case 1:
                Im1.setImageURI(image1);
                counter += 1;
                break;
            case 2:
                Im5.setImageURI(image1);
                counter += 1;
                break;
            case 3:
                Im4.setImageURI(image1);
                counter += 1;
                break;
            case 4:
                Im3.setImageURI(image1);
                counter += 1;
                break;
            case 5:
                Im2.setImageURI(image1);
                counter += 1;
                break;
            case 6:
                Im1.setImageURI(image1);
                counter += 1;
                break;
            case 7:
                Im0.setImageURI(image1);
                counter += 1;
                break;
        }
    }
}
