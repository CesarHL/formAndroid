package mipymex.mcs.com.pruebas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mipymex.mcs.com.pruebas.R;

public class CatalogoImagenes extends Fragment {

    ImageView foto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalogo_foto, null);
        foto = (ImageView) view.findViewById(R.id.imgCheckPhoto);
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                showOptions();
                openCamera();
            }
        });
        return view;
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
}
