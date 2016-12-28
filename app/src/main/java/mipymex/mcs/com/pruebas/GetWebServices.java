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

public class GetWebServices extends AsyncTask<String, Void, String> {

    private String IPpublic = Login.IPpublic;
    private Context context;
    private String credito; // Variable para delete from clientes,cuentas

    public GetWebServices(Context context){
        this.context = context;
        connection = new Connection(this.context);
    }

    private SQLiteDatabase db = null;      // Objeto para usar la Base de Datos Local
    private Connection connection; // Objeto para saber si estamos conectados (wifi/datos)

    /***********************************************************************************************
     *                                        VARIABLES GLOBALES                                   *
     **********************************************************************************************/
    private String usuario;     // Recuperar el usuario
    private String password;    // Recuperar el password
    private int num_agencia;            // Recuperra el numero de agencia
    private boolean flagLogin = false;  // Bandera para saber si existe usuario o no
    private boolean flagSend = false;   // Bandera para saber si se envio el cliente o no
    private String flagOnPostExcecute = ""; // Bandera pasa saber que executar despues de consumir el servicio

    @Override
    protected String doInBackground(String... params) {

        System.out.println("Consumir: " + params[1]);

        HttpURLConnection conn;
        JSONObject respuestaJSON;

        String cadena = params[0];      // Recuperar la dirección del HOST
        System.out.println(cadena);
        flagOnPostExcecute = params[1]; // Recuperar bandera de solicitud de servicio
        usuario  = params[2];   // Guardar el usuario
        password = params[3];   // Guardar el password

        URL url; // URL de donde queremos obtener información
        String devuelve = "";

        switch (params[1]) {
                /*--------------------------------- Web Service Usuario --------------------------*/
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
                            result.append(line); // Pasar todas las entradas al StringBuilder
                        }

                        respuestaJSON = new JSONObject(result.toString());
                        JSONArray numAgencia = respuestaJSON.getJSONArray("Login_Usuario");

                        for (int i = 0; i < numAgencia.length(); i++) {
                            System.out.println("Número de agencia: " + numAgencia.getJSONObject(i).getString("Num_Agencia"));
                            num_agencia = Integer.parseInt(numAgencia.getJSONObject(i).getString("Num_Agencia"));
                        }
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

            case "config":
                try {
                    String IPConfig = IPpublic + "Configuracion?v_cliente=1";
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
                        JSONArray config = respuestaJSON.getJSONArray("Configuracion");

                        String cat_cm_valor_min;
                        String cat_cm_valor_max;
                        String cat_cm_valor_wifi;
                        String cat_cm_valor_version;

                        db = context.openOrCreateDatabase(DataDB.DB_NAME, Context.MODE_PRIVATE ,null);

                            cat_cm_valor_min = config.getJSONObject(0).getString("CAT_CM_VALOR");
                            cat_cm_valor_max = config.getJSONObject(1).getString("CAT_CM_VALOR");
                            cat_cm_valor_wifi = config.getJSONObject(2).getString("CAT_CM_VALOR");
                            cat_cm_valor_version = config.getJSONObject(4).getString("CAT_CM_VALOR");

                            System.out.println("cat_cm_valor_min:" + cat_cm_valor_min);
                            System.out.println("cat_cm_valor_max:" + cat_cm_valor_max);
                            System.out.println("cat_cm_valor_wifi:" + cat_cm_valor_wifi);
                            System.out.println("cat_cm_valor_version:" + cat_cm_valor_version);

                            try {
                                ContentValues values = new ContentValues();
                                values.put(DataDB.CAT_CM_VALOR_MIN, Integer.parseInt(cat_cm_valor_min));
                                values.put(DataDB.CAT_CM_VALOR_MAX, Integer.parseInt(cat_cm_valor_max));
                                values.put(DataDB.CAT_CM_VALOR_WIFI, Integer.parseInt(cat_cm_valor_wifi));
                                values.put(DataDB.CAT_CM_VALOR_VERSION, cat_cm_valor_version);
                                db.insert(DataDB.TABLE_NAME_CONFIG_IMAGENES, null, values);
                            } catch (SQLException ex) {
                                System.out.println("Error al insertar la configuración de las fotos: " + ex);
                                break;
                            } finally {
                                System.out.println("Configuración de fotos agregada correctamente");
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

            case "fotos":
                try {
                    String IPConfig = IPpublic + "TipoFoto?v_cliente=1";
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

                        db = context.openOrCreateDatabase(DataDB.DB_NAME, Context.MODE_PRIVATE ,null);
                        for (int i = 0; i < fotos.length(); i++) {

                            cat_pa_id = fotos.getJSONObject(i).getString("CAT_PA_ID");
                            cat_pa_descripcion = fotos.getJSONObject(i).getString("CAT_PA_PARENTESCO");

                            System.out.println("cat_pa_id:" + cat_pa_id);
                            System.out.println("cat_pa_descripcion:" + cat_pa_descripcion);

                            try {
                                ContentValues values = new ContentValues();
                                values.put("_id", i + 1);
                               // values.put(DataDB.CAT_CM_ID, cat_pa_id);
                                //values.put(DataDB.CAT_CM_DESCRIPCION, cat_pa_descripcion);
                                //db.insert(DataDB.TABLE_NAME_TIPO_FOTO, null, values);
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

            case "enviar":

                try{
                    url = new URL(cadena);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");

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

                        if (resultJSON.equals("OK")){
                            System.out.println("Usuario enviado");
                            credito = params[4];
                            flagSend = true;
                        }
                        else{
                            System.out.println("Usuario NO enviado");
                            flagSend = false;
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
                /* --------------------------------- CASO PARA EL LOGIN --------------------------*/
            case "login":
                if (flagLogin) {
                    // Si los datos de Login son correctos consumir servicio de sus Cuentas
                    // Guardamos el usuario y la contraseña en la base de datos local
                    if (saveUser(usuario, password) > 0) {
                        Toast.makeText(context, "Usuario guardado ", Toast.LENGTH_SHORT).show();
                        getCuentas();
                    }
                    else
                        Toast.makeText(context, "Usuario NO guardado ", Toast.LENGTH_SHORT).show();

                    // Abrimos el Activity Catalogo
                   // Intent intentCatalogo = new Intent(context, Catalogo.class);
                   // context.startActivity(intentCatalogo);
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

            default: break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
    /* -------------------------- Guardar Núm de agencia, Nombre y password ----------------------*/
    private int saveUser(String nom, String pass) {
        int idSave = 0;
        db = context.openOrCreateDatabase(DataDB.DB_NAME, Context.MODE_PRIVATE ,null);
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("_id",1);
            values.put(DataDB.NUM_AGENCIA, num_agencia);
            values.put(DataDB.NAME, nom);
            values.put(DataDB.PASSWORD, pass);
            idSave = (int) db.insert(DataDB.TABLE_NAME_USERS, null, values);
        }
        if (db != null) {
            db.close();
        }

        return idSave;
    }
    /*------------------------------ Obtener las cuentas de clientes -----------------------------*/
    private void getCuentas() {

        db = context.openOrCreateDatabase(DataDB.DB_NAME, Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT " + DataDB.NAME + " FROM " + DataDB.TABLE_NAME_USERS, null);
        try {
            if (c.moveToFirst()) {
                System.out.println("Usuario: " + c.getString(0));

                String user = c.getString(0);

                if (connection.getConnection("No informar")) {
                    String IPCuentas = IPpublic + "InfoGral?v_cliente=1";
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
