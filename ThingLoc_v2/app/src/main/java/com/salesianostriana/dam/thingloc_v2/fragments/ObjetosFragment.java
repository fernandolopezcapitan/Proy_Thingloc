package com.salesianostriana.dam.thingloc_v2.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.Objetos.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjetosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Result> listadoObjetos;


    public ObjetosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_objetos, container, false);

        //mRecyclerView = (RecyclerView) view.findViewById(R.id.comentarios_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }


}
