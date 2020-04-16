package com.example.lab3_fragments;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Galery extends Fragment
{
    public static int flag = 0;
    public static RecyclerView recyclerView;
    public static ArrayList<Item> items = new ArrayList<Item>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.galery_fragment, container, false);
        Button AddButton = (Button) view.findViewById(R.id.Add);

        recyclerView = view.findViewById(R.id.Recycler);
        Galery g= new Galery();
        recyclerView.setLayoutManager(new LinearLayoutManager(g.getContext()));

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (flag == 0)
                {
                    flag = 1;
                    Fragment fragment = null;
                    fragment = new AddPhoto();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.add(R.id.fr_head, fragment);
                    ft.commit();
                    MainActivity.whatscene = 2;
                }
            }
        });

        return view;
    }

    public static void AddItem(String title, Uri image, String info)
    {
        items.add(new Item(title,info, image));
        ItemAdapter itemAdapter = new ItemAdapter(items);
        recyclerView.setAdapter(itemAdapter);
    }

}
