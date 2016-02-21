package com.salesianostriana.dam.thingloc_v2.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.salesianostriana.dam.thingloc_v2.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by flopez on 21/02/2016.
 */
public class ObjetosAddFragment extends Fragment {


    public ObjetosAddFragment() {
        // Required empty public constructor
    }


    final static int FILE_CODE= 1;
    File f;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_objetos_add, container, false);

        img = (ImageView) v.findViewById(R.id.img_logo_obj);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FilePickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                startActivityForResult(i, FILE_CODE);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO Funcionalidad añadir objetos

            }
        });


        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == FILE_CODE && resultCode == Activity.RESULT_OK){
            if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip = data.getClipData();
                    if (clip != null) {
                        for (int i = 0; i < clip.getItemCount(); i++) {

                            Uri uri = clip.getItemAt(i).getUri();
                            Toast.makeText(getContext(), "Fichero seleccionado..." + uri.getPath(), Toast.LENGTH_SHORT).show();
                            f=new File(uri.getPath());
                            Picasso.with(getContext())
                                    .load(f)
                                    .resize(50,50)
                                    .error(R.drawable.icon_lostfound)
                                    .into(img);
                        }
                    }

                    //Para otras versiones de Android más antiguas.
                }else{
                    ArrayList<String> paths = data.getStringArrayListExtra
                            (FilePickerActivity.EXTRA_PATHS);

                    if (paths != null) {
                        for (String path: paths) {

                            Uri uri = Uri.parse(path);
                            Toast.makeText(getContext(), "Fichero seleccionado..." + uri.getPath(), Toast.LENGTH_SHORT).show();

                            f=new File(uri.getPath());
                            Picasso.with(getContext())
                                    .load(f)
                                    .resize(50, 50)
                                    .error(R.drawable.icon_lostfound)
                                    .into(img);


                        }
                    }
                }

            }else {
                Uri uri = data.getData();
                Toast.makeText(getContext(), "Fichero seleccionado..." + uri.getPath(), Toast.LENGTH_SHORT).show();
                f=new File(uri.getPath());
                Picasso.with(getContext())
                        .load(f)
                        .resize(50, 50)
                        .error(R.drawable.icon_lostfound)
                        .into(img);
            }
        }
    }
}
