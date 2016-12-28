package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetWebServicesFotos extends AsyncTask<String, Void, String> {

    private Context context;

    private String credito; // Variable para delete from clientes,cuentas
    private String imgTipo; // Variable para delete from imagenes

    public GetWebServicesFotos(Context context){
        this.context = context;
    }
    /***********************************************************************************************
     *                                        VARIABLES GLOBALES                                   *
     **********************************************************************************************/
    private boolean flagSendFotos = false;

    private String flagOnPostExcecute = ""; // Bandera pasa saber que executar despues de consumir el servicio

    @Override
    protected String doInBackground(String... params) {

        System.out.println("Consumir: " + params[1]);

        HttpURLConnection conn;
        JSONObject respuestaJSON;

        String cadena = params[0];      // Recuperar la dirección del HOST
        flagOnPostExcecute = params[1]; // Recuperar bandera de solicitud de servicio

        URL url; // URL de donde queremos obtener información

        String devuelve = "";

        switch (params[1]) {

            case "enviar_fotos":

                try{
                    url = new URL(cadena);
                    System.out.println(cadena);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    conn.setFixedLengthStreamingMode(cadena.getBytes().length);

                    int respuesta = conn.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){

                        System.out.println("HTTP_OK if");

                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line); // Pasar todas las entradas al StringBuilder
                        }

                        respuestaJSON = new JSONObject(result.toString());

                        String resultJSON = respuestaJSON.getString("Respuesta");

                        if (resultJSON.equals("Error")){
                            System.out.println("Imagen no enviada");
                            flagSendFotos = false;
                        }
                        else{
                            System.out.println("Imagen enviada");
                            credito = params[2];
                            imgTipo = params[3];
                            flagSendFotos = true;

                            try{
                             //   SQLiteDatabase db = context.openOrCreateDatabase(DataDB.DB_NAME, Context.MODE_PRIVATE ,null);
                             //   db.delete(DataDB.TABLE_NAME_IMAGEN,DataDB.PR_CF_CREDITO + "=? and " + DataDB.CAT_CM_ID + "=?",new String[]{credito,imgTipo});
                                System.out.println("Imagen eliminada");
                                //db.close();
                            }catch (SQLException e) {
                                e.printStackTrace();
                                System.out.println("Imagen no eliminada");
                            }
                        }
                    }
                    else{
                        System.out.println("HTTP_OK else");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("MalformedURLException: " + e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IOException: " + e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("JSONException: " + e.toString());
                }
                break;
        }
        return devuelve;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
