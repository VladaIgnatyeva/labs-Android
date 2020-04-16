package com.example.lab3_fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CardPost extends Fragment
{

    public static ImageView card;
    public static TextView infa;
    public static TextView titla;

    public static String inftext;
    public static String titltext;
    public static Uri carduri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.card_post_fragment, container, false);
        Button CloseButtonPLS = (Button) view.findViewById(R.id.closeCardPost);

        CloseButtonPLS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fr_head);
                fm.beginTransaction() .remove(fragment) .commit();
                MainActivity.whatscene = 1;
            }

        });

        card = view.findViewById(R.id.coverPost);
        infa = view.findViewById(R.id.infoPost);
        titla = view.findViewById(R.id.titlePost);

        titla.setText(titltext);
        infa.setText(inftext);
        card.setImageURI(carduri);

        return  view;
    }

}
