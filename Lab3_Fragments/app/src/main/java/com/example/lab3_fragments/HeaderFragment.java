package com.example.lab3_fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HeaderFragment extends Fragment
{
    ImageView photo;
    Uri imageUri;
    public static int flag =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.header_fragment, container, false);

        photo = view.findViewById(R.id.PreView);
        Button BrowseImageBtn = (Button) view.findViewById(R.id.Browse_Image);
        Button CloseButton = (Button) view.findViewById(R.id.Close);
        Button AddButton = (Button) view.findViewById(R.id.Add_Image);

        BrowseImageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                startActivityForResult(intent, 0);
            }
        });

        CloseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.TopScreenFragment);
                fm.beginTransaction() .remove(fragment) .commit();
                Galery.flag = 0;
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (flag == 1)
                {
                    Galery.AddImage(imageUri);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.TopScreenFragment);
                    fm.beginTransaction().remove(fragment).commit();
                    flag = 0;
                    Galery.flag = 0;
                }
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode)
            {
                case 0:
                    imageUri = data.getData();
                    photo.setImageURI(imageUri);
                    flag = 1;
                    break;
            }
    }
}

