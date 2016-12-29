package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class UtilImages extends Activity {


    public static ArrayList<String> arrayListFotoTomada = new ArrayList<>();
    public static ArrayList<String> listFecha = new ArrayList<>();
    public static Map<String,Integer> mapImages;
    public static Map<Integer,String> mapImagesTmp;
    private String tmpDescImagen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            //itemCam.setIcon(R.drawable.camara);

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

           // Service_trabajar.fotos.setText("Fotos: " + mapImagesTmp.size());
        }
    }

    private void openCamera() {
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public String convertBitmapToString(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 55, stream);
        byte[] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }
}
