package com.salesianostriana.dam.thingloc_v2.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.adapters.AdapterObjeto;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.Result;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjetosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int errorCode;


    public ObjetosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_objetos, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.objetos_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Petición 5. Obtener todos los objetos.
        // DEVUELVE el listado con todos los objetos.
        loadDataObjetos();

        return v;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void loadDataObjetos() {

        Call<List<Result>> userCall = Utiles.serviceConInterceptorsAuth(getContext()).obtenerMisObjetos();

        userCall.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Response<List<Result>> response, Retrofit retrofit) {


                List<Result> result = response.body();
                errorCode = response.code();

                Log.i("USUARIO", String.valueOf(result));
                Log.i("CÓDIGO DE ERROR", String.valueOf(errorCode));


                if ((errorCode == 200) || (errorCode == 201)) {

                    mAdapter = new AdapterObjeto(result);
                    mRecyclerView.setAdapter(mAdapter);


                } else {


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }


}
