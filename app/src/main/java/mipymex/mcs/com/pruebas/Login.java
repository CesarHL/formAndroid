package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    final static public String IPpublic = "http://masternoc1.mx/wsmovil/api/";

    public static EditText usuario;
    public static EditText password;
    public static Button entrar;
    public static ProgressBar progress;
    public static TextView titulo;
    private TextView txtUser;
    private TextView cerrar;
    private Toast toast;
    public SQLiteDatabase db = null;
    private Cursor c;
    private DBHelper sqliteHelper;
    private Connection connection;
    private String strLogin = "";
    private String strPassword = "";
    private boolean flagSesion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        sqliteHelper = new DBHelper(this, DataDB.DB_NAME, null, DataDB.VERSION);
        db = sqliteHelper.getWritableDatabase();
        db.close();

        connection = new Connection(this);
        usuario  = (EditText)  findViewById(R.id.txtUsuario);
        password = (EditText)  findViewById(R.id.txtPassword);
        titulo   = (TextView)  findViewById(R.id.lblTitulo);
        txtUser  = (TextView)  findViewById(R.id.lblUsuario);
        cerrar   = (TextView)  findViewById(R.id.lblCerrar);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        entrar = (Button) findViewById(R.id.btnIniciar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String user = usuario.getText().toString();
            String pass = password.getText().toString();

                if (user.trim().equalsIgnoreCase("")) {
                    usuario.setError("Este campo no puede estar vacio");
                    usuario.setText("");
                } else if (pass.trim().equalsIgnoreCase("")) {
                    password.setError("Este campo no puede estar vacio");
                    password.setText("");
                } else if (flagSesion) {
                    if (pass.equals(strPassword)) {
                        Intent intentCatalogo = new Intent(Login.this, VentanaPrincipal.class);
                        startActivity(intentCatalogo);
                        finish();
                    } else {
                        if (toast != null)
                            toast.cancel();
                        toast = Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    progress.setVisibility(View.VISIBLE);
                    usuario.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.INVISIBLE);
                    entrar.setVisibility(View.INVISIBLE);

                    if (connection.getConnection("No informar")) {
                        strLogin = IPpublic + "usuario?v_cliente=2&v_usuario=" + user + "&v_contrasena=" + pass;//
                        new GetWebServices(Login.this).execute(strLogin, "login", user, pass);// Parámetros que recibe doInBackground
                    } else {
                        usuario.setVisibility(View.VISIBLE);
                        password.setVisibility(View.VISIBLE);
                        entrar.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                    }
                }
                }
        });
        /*--------------------------------- Evento de label Cerrar -------------------------------*/
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrar();
            }
        });
        getSession();
        getTipoFoto();
    }

    public void getSession() {
        db = sqliteHelper.getWritableDatabase();
        c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_USUARIOS, null);
        try {
            if (c.moveToFirst()) {

                String strUsuario = c.getString(1);
                strPassword = c.getString(2);
                txtUser.setText(strUsuario);
                txtUser.setVisibility(View.VISIBLE);
                usuario.setVisibility(View.INVISIBLE);
                cerrar.setVisibility(View.VISIBLE);
                usuario.setText(strUsuario);
                password.requestFocus();
                flagSesion = true;
            } else {
                titulo.setText("Bienvenido");
                txtUser.setVisibility(View.INVISIBLE);
                usuario.setVisibility(View.VISIBLE);
                cerrar.setVisibility(View.INVISIBLE);
                usuario.requestFocus();
                flagSesion = false;
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }finally {
            db.close();
        }
    }

    public void getTipoFoto(){
        db = sqliteHelper.getWritableDatabase();
        c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_TIPO_FOTO, null);
        try {
            if(c.moveToFirst())
                System.out.println("Tipo de fotos existentes");
            else
            if(connection.getConnection("No informar"))
                new GetWebServices(Login.this).execute("", "fotos",null,null);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }finally {
            db.close();
        }
    }

    public void cerrar() {

        db = openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
        Cursor c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_USUARIOS, null);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
        builder1.setCancelable(true);
        builder1.setTitle("ATENCIÓN");
        try {
            if (c.moveToFirst()) {
                builder1.setMessage("Aún no has sincronizado los datos");
            } else {
                builder1.setMessage("¿Desea cerrar sesión?");
                builder1.setPositiveButton("Cerrar sesión", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db = sqliteHelper.getWritableDatabase();
                        if (db != null) {
                            db.delete(DataDB.TABLE_NAME_USUARIOS, null, null);
                            db.delete(DataDB.TABLE_NAME_SOLICITUD,null, null);
                            titulo.setText("Bienvenido");
                            usuario.setText("");
                            password.setText("");
                            password.setError(null);
                            usuario.requestFocus();
                            getSession();
                            db.close();
                        }
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }finally {
            db.close();
            builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            Button bp = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
            Button bn = alert11.getButton(DialogInterface.BUTTON_NEGATIVE);
            bp.setTextColor(Color.GRAY);
            bn.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onStart(){
        try {
            db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
            ContentValues updates = new ContentValues();
            //updates.put(DataDB.FRAGMENT,"home");
            //db.update(DataDB.TABLE_NAME_PARAMETROS,updates,null, null);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
           // System.out.println("Fragment actualizado: HOME");
            db.close();
        }

        super.onStart();
    }
}
