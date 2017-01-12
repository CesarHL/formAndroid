package mipymex.mcs.com.pruebas.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mipymex.mcs.com.pruebas.FotosAdapter;
import mipymex.mcs.com.pruebas.FotosRespuesta;
import mipymex.mcs.com.pruebas.Login;
import mipymex.mcs.com.pruebas.R;

public class CatalogoImagenes extends Fragment {

   @BindView(R.id.listaFotos)RecyclerView listaFotos;
    private FotosAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.catalogo_foto, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new FotosAdapter();
        listaFotos.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaFotos.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences(Login.PREFS_NAME, 0);
        int arraySize = preferences.getInt("array_size", 0);
        FotosRespuesta respuesta = new FotosRespuesta();
        List<String> cat_pa_id = new ArrayList<>(arraySize);
        List<String> cat_pa_parentesco = new ArrayList<>(arraySize);
        for (int i = 0; i<arraySize; i++) {
            cat_pa_id.add(preferences.getString("cat_pa_id" + i, "Error"));
            cat_pa_parentesco.add(preferences.getString("cat_pa_descripcion" + i, "Error"));
        }
        respuesta.setCat_pa_id(cat_pa_id);
        respuesta.setCat_pa_parentesco(cat_pa_parentesco);
        adapter.addAll(respuesta);
    }


    private void showOptions() {

       /* final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        builder.setAdapter(itemAdapterFoto, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                tmpDescImagen = items.get(item).getCat_cm_descripcion();
                Toast.makeText(Catalogo.this,items.get(item).getCat_cm_descripcion(),Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });
        builder.show();*/
    }

    private void openCamera() {
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void onElementClicked(View v) {
        openCamera();
    }
}
