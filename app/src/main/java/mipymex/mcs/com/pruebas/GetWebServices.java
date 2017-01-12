package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
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
import java.util.ArrayList;

public class GetWebServices extends AsyncTask<String, Void, String> {

    private String IPpublic = Login.IPpublic;
    private Context context;
    private boolean flagSend = false;
    private URL url;
    public  static ArrayList descList;

    public GetWebServices(Context context){
        this.context = context;
        connection = new Connection(this.context);
    }

    private SQLiteDatabase db = null;
    private Connection connection;
    private String usuario;
    private String password;
    private boolean flagLogin = false;
    private String flagOnPostExcecute = "";

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection conn;
        JSONObject respuestaJSON;

        System.out.println("Consumir: " + params[1]);
        String cadena = params[0];
        System.out.println(cadena);
        flagOnPostExcecute = params[1];
        usuario  = params[2];
        password = params[3];
        URL url;
        String devuelve = "";

        switch (params[1]) {
            case "login":
                try {
                    url = new URL(cadena);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = conn.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        respuestaJSON = new JSONObject(result.toString());
                        String resultJSON = respuestaJSON.getString("Acceso");
                        flagLogin = resultJSON.contains("Correcto");
                    }
                } catch (MalformedURLException e) {
                    devuelve = devuelve + "eMAL: " + e.toString();
                    e.printStackTrace();
                } catch (IOException e) {
                    devuelve = devuelve + "eIO: " + e.toString();
                    e.printStackTrace();
                } catch (JSONException e) {
                    devuelve = devuelve + "eJSON: " + e.toString();
                    e.printStackTrace();
                }
                break;

            case "enviar":

                try{
                    url = new URL(cadena);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = conn.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){

                        System.out.println("HTTP_OK if - DATOS");

                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        respuestaJSON = new JSONObject(result.toString());

                        String resultJSON = respuestaJSON.getString("Respuesta");

                        if (resultJSON.equals("OK")){
                            System.out.println("Datos enviados");
                            flagSend = true;
                        }
                        else{
                            System.out.println("Datos no enviados");
                            flagSend = false;
                        }
                    } else {
                        System.out.println("HTTP_OK else - DATOS");
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

            case "fotos":
                try {
                    String IPConfig = IPpublic + "TipoFoto?v_cliente=2";
                    url = new URL(IPConfig);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = conn.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line); // Pasar todas las entradas al StringBuilder
                        }

                        respuestaJSON = new JSONObject(result.toString());
                        JSONArray fotos = respuestaJSON.getJSONArray("TipoFoto");

                        String cat_pa_id;
                        String cat_pa_descripcion;

                        db = context.openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
                        for (int i = 0; i < fotos.length(); i++) {

                            cat_pa_id = fotos.getJSONObject(i).getString("CAT_PA_ID");
                            cat_pa_descripcion = fotos.getJSONObject(i).getString("CAT_PA_PARENTESCO");

                            System.out.println("cat_pa_id:" + cat_pa_id);
                            System.out.println("cat_pa_descripcion:" + cat_pa_descripcion);
                            descList = new ArrayList<>();
                            descList.add(cat_pa_descripcion);

                            try {
                                ContentValues values = new ContentValues();
                                values.put("_id", i + 1);
                                values.put(DataDB.CAT_CM_ID, cat_pa_id);
                                values.put(DataDB.CAT_CM_DESCRIPCION, cat_pa_descripcion);
                                db.insert(DataDB.TABLE_NAME_TIPO_FOTO, null, values);
                            } catch (SQLException ex) {
                                System.out.println("Error al insertar tipo de fotos " + ex);
                                break;
                            } finally {
                                System.out.println("tipo de fotos agregadas correctamente");
                            }
                        }
                        db.close();
                    }
                } catch (MalformedURLException e) {
                    devuelve = devuelve + "eMAL: " + e.toString();
                    e.printStackTrace();
                } catch (IOException e) {
                    devuelve = devuelve + "eIO: " + e.toString();
                    e.printStackTrace();
                } catch (JSONException e) {
                    devuelve = devuelve + "eJSON: " + e.toString();
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }

        return devuelve;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s){

        switch (flagOnPostExcecute) {

            case "login":
                if (flagLogin) {

                    if (saveUser(usuario, password) > 0) {
                        Toast.makeText(context, "Usuario guardado ", Toast.LENGTH_SHORT).show();
                        getCuentas();
                    }else
                        Toast.makeText(context, "Usuario NO guardado ", Toast.LENGTH_SHORT).show();

                    Intent intentVentanaPrincipal = new Intent(context, VentanaPrincipal.class);
                    context.startActivity(intentVentanaPrincipal);
                    ((Activity)context).finish();

                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setCancelable(true);
                    builder1.setTitle("Usario no encontrado");
                    builder1.setMessage("Acceso: Denegado");
                    builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    Button bq = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                    bq.setTextColor(Color.GRAY);
                    Login.usuario.setVisibility(View.VISIBLE);
                    Login.password.setVisibility(View.VISIBLE);
                    Login.entrar.setVisibility(View.VISIBLE);
                    Login.progress.setVisibility(View.GONE);
                }
                break;

            case "enviar":

                if(flagSend){
                    try{
                        //db = context.openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
                        //TODO borrar tablas

                    }catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Cliente no eliminado");
                    }
                }
                break;


            default: break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values); }

    private int saveUser(String nom, String pass) {
        int idSave = 0;
        db = context.openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(DataDB.NAME, nom);
            values.put(DataDB.PASSWORD, pass);
            idSave = (int) db.insert(DataDB.TABLE_NAME_USUARIOS, null, values);
        }

        if (db != null) {
            db.close();
        }

        return idSave;
    }

    private void getCuentas() {
        db = context.openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT " + DataDB.NAME + " FROM " + DataDB.TABLE_NAME_USUARIOS, null);
        try {
            if (c.moveToFirst()) {
                System.out.println("Usuario: " + c.getString(0));
                String user = c.getString(0);

                if (connection.getConnection("No informar")) {
                    String IPCuentas = IPpublic + "usuario?v_cliente=2";
                    String strCuenta = IPCuentas + "&v_usuario=" + user;
                    new GetWebServices(context).execute(strCuenta, "cuentas", user, null);
                }
            } else
                System.out.println("No existe usuario");

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
    }

}
