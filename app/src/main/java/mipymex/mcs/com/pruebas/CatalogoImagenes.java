package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CatalogoImagenes extends Activity {

    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_foto);

        foto = (ImageView) findViewById(R.id.imgCheckPhoto);
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // showOptions();
                openCamera();
            }
        });
    }

    private void showOptions() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        /*builder.setAdapter(itemAdapterFoto, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                tmpDescImagen = items.get(item).getCat_cm_descripcion();
                Toast.makeText(Catalogo.this,items.get(item).getCat_cm_descripcion(),Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });*/
        builder.show();
    }

    private void openCamera() {
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
