package com.example.lab3_fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddPhoto extends Fragment
{

    ImageView photo;
    Uri imageUri;
    EditText title;
    EditText info;

    public static int flagLoc=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.add_photo_fragment, container, false);

        photo = view.findViewById(R.id.coverPost);
        title = view.findViewById(R.id.Title);
        info = view.findViewById(R.id.Info);
        Button CloseButton = (Button) view.findViewById(R.id.Close);
        Button AddButton = (Button) view.findViewById(R.id.Add_Image);
        ImageView Addp = (ImageView) view.findViewById(R.id.coverPost);

        Addp.setOnClickListener(new View.OnClickListener()
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
                Fragment fragment = fm.findFragmentById(R.id.fr_head);
                fm.beginTransaction() .remove(fragment) .commit();
                Galery.flag = 0;
                MainActivity.whatscene = 1;
            }

        });

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (flagLoc == 1 && title.getText().toString().equals("") != true && info.getText().toString().equals("") != true)
                {
                    String title_ = title.getText().toString();
                    String info_ = info.getText().toString();
                    Galery.AddItem(title_, imageUri, info_);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fr_head);
                    fm.beginTransaction().remove(fragment).commit();
                    flagLoc = 0;
                    Galery.flag = 0;
                    MainActivity.whatscene = 1;
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
                    flagLoc = 1;
                    break;
            }
    }

}

