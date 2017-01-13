package mipymex.mcs.com.pruebas.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mipymex.mcs.com.pruebas.GetWebServices;
import mipymex.mcs.com.pruebas.ItemAdapterFoto;
import mipymex.mcs.com.pruebas.ItemTipoFoto;
import mipymex.mcs.com.pruebas.R;

public class CatalogoImagenes extends Fragment {

    public static ImageView foto;
    public static ArrayList<String> arrayListFotoTomada = new ArrayList<>();
    public static Map<String,Integer> mapImages;
    public static Map<Integer,String> mapImagesTmp;
    private String tmpDescImagen = "";
    public static ArrayList<String> listFecha = new ArrayList<>();
    private ItemAdapterFoto itemAdapterFoto;
    public static ArrayList<ItemTipoFoto> items = null;
    private SQLiteDatabase db = null;
    private Cursor c;
    public static int tamDatos;
    public ArrayList tipoFotos = new ArrayList();
    private GetWebServices gw;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mapImages = new HashMap<>();
        mapImagesTmp = new HashMap <>();
        View view = inflater.inflate(R.layout.catalogo_foto, null);
        foto = (ImageView) view.findViewById(R.id.imgCheckPhoto);
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // showOptions();
                openCamera();
            }
        });
        return view;
    }


   /* private void showOptions() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        builder.setAdapter(itemAdapterFoto, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                tmpDescImagen = items.get(item).getCat_cm_descripcion();
                Toast.makeText(CatalogoImagenes.this,items.get(item).getCat_cm_descripcion(),Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });
        builder.show();
    }*/

    private void openCamera() {
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {


            Bitmap bitmap;
            bitmap = (Bitmap) data.getExtras().get("data");

            System.out.println("Ancho: " + bitmap.getWidth());
            System.out.println("Alto: " + bitmap.getHeight());

            /******************************** REDIMENSIONAR LA IMAGEN *****************************/
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = 350;
            int newHeight = 288;

            System.out.println(newWidth + "/" + width);
            System.out.println(newHeight + "/" + height);

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth,scaleHeight);
            Bitmap resizeBitmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
            String stringImg = convertBitmapToString(resizeBitmap);
            /**************************************************************************************/

            System.out.println("Encode img: \n" + stringImg.substring(0,10));

            mapImagesTmp.put(mapImages.get(tmpDescImagen), stringImg);
            arrayListFotoTomada.add(tmpDescImagen);

            System.out.println("mapImageTmp: " + mapImagesTmp.get(mapImages.get(tmpDescImagen)).substring(0,30));

            DateFormat dateFormatFoto = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            Date dateFoto = new Date();
            listFecha.add(dateFormatFoto.format(dateFoto));

        }
    }

    public String convertBitmapToString(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 55, stream);
        byte[] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }




}
