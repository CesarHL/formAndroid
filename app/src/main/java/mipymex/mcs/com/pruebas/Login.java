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

    /***********************************************************************************************
     *                                      DECLARACIÓN DE OBJETOS                                 *
     **********************************************************************************************/
    public static EditText usuario;   // Campo de texto para usuario
    public static EditText password;  // Campo de texto para contraseña
    public static Button entrar;      // Boton para ingresar
    public static ProgressBar progress;
    public static TextView titulo;    // Texto para el titulo
    private TextView txtUser;         // Texto para usuaio en sesion
    private TextView cerrar;          // Texto para cerrar sesión
    private Toast toast;              // Toast para mostrar mensajes
    public SQLiteDatabase db = null;   // Objeto para usar la Base de Datos Local
    private Cursor c;                   // Objeto para hacer consultas la Base de Datos
    private DBHelper sqliteHelper;      // Objeto para abrir la base de Datos
    private Connection connection;      // Objeto para saber si estamos conectados (wifi/datos)
    private String strLogin = "";       // Cadena para concatenar el usuario y contraseña
    private String strPassword = "";    // String que recuperra la contraseña
    private boolean flagSesion = false; // Bandera para saber si esta abierta la sesión

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
                }
                /**
                 * Si ya tiene la sesión abierta comparamos la contraseña con la base de datos
                 * local para no acceder al recurso y sea mas rápida la autenticación.
                 */
                else if (flagSesion) {
                    // Si las contraseñas son iguales abrimos el Activity de catalogo
                    if (pass.equals(strPassword)) {
                        Intent intentCatalogo = new Intent(Login.this, VentanaPrincipal.class);
                        startActivity(intentCatalogo);
                        finish();
                    }
                    // Si las contraseñas son distintas mostrar mensaje de contraseña incorrecta
                    else {
                        if (toast != null)
                            toast.cancel();
                        toast = Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {  // Si no existe una sesión cosumir web service para autentificar usuarios y password
                    progress.setVisibility(View.VISIBLE);
                    usuario.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.INVISIBLE);
                    entrar.setVisibility(View.INVISIBLE);
                    /**
                     * Si no existe una sesión verificar el estado de red, ya que para el primer
                     * Login es necesario acceder al recurso a traves de internet.
                     */
                    if (connection.getConnection("No informar")) {//usuario?v_cliente=2&v_usuario=ORIGINACION&v_contrasena=12345678
                        strLogin = IPpublic + "usuario?v_cliente=2&v_usuario=" + user + "&v_contrasena=" + pass;
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

        getSession();       // Revisar si tiene la sesion abierta
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
            System.out.println("Fragment actualizado: HOME");
            db.close();
        }

        super.onStart();
    }
}
