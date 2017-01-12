package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ReferenciasPersonales extends AppCompatActivity {

    public static EditText txtApaternoRef, txtAMaternoRef, txtNombresRef, txtCalleRef, txtExtRef, txtIntRef, txtColRef, txtCpRef, txtMunRef, txtEdoRef;
    public static EditText txtTelRef, txtCelRef, txtCorreoRef, txtParentescoRef;
    private SQLiteDatabase db = null;
    private Cursor c = null;
    private Button agregar ;
    public static int tamDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referencias_personales);

        txtApaternoRef = (EditText) findViewById(R.id.txtApellidoPaternoReferencias);
        txtAMaternoRef = (EditText) findViewById(R.id.txtApellidoMaternoReferencias);
        txtNombresRef = (EditText) findViewById(R.id.txtNombresReferencias);
        txtCalleRef = (EditText) findViewById(R.id.txtCalleReferencias);
        txtExtRef = (EditText) findViewById(R.id.txtExteriorReferencias);
        txtIntRef = (EditText) findViewById(R.id.txtInteriorReferencias);
        txtColRef = (EditText) findViewById(R.id.txtColoniaReferencias);
        txtCpRef = (EditText) findViewById(R.id.txtCpReferencias);
        txtMunRef = (EditText) findViewById(R.id.txtMunicipioReferencias);
        txtEdoRef = (EditText) findViewById(R.id.txtEstadoReferencias);
        txtTelRef = (EditText) findViewById(R.id.txtTelefonoReferencias);
        txtCelRef = (EditText) findViewById(R.id.txtCelularReferencias);
        txtCorreoRef = (EditText) findViewById(R.id.txtCorreoReferencias);
        txtParentescoRef = (EditText) findViewById(R.id.txtParentescoReferencias);

        agregar =(Button)findViewById(R.id.btnGuardarReferencias);
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // validarReferenciasPersonales();
                guardarReferenciasPersonales();
                mostraDatos();
            }
        });
    }


    public void validarReferenciasPersonales(){
        Boolean v1 = txtApaternoRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v2 = txtAMaternoRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v3 = txtNombresRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v4 = txtCalleRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v5 = txtExtRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v6 = txtIntRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v7 = txtColRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v8 = txtCpRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v9 = txtMunRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v10 = txtEdoRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v11 = txtTelRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v12 = txtCelRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v13 = txtCorreoRef.getText().toString().trim().equalsIgnoreCase("");
        Boolean v14 = txtParentescoRef.getText().toString().trim().equalsIgnoreCase("");

        if(v1) {
            txtApaternoRef.setError("Este campo no puede estar vacio");
            txtApaternoRef.setText("");
            txtApaternoRef.requestFocus();
        } else if(v2) {
            txtAMaternoRef.setError("Este campo no puede estar vacio");
            txtAMaternoRef.setText("");
            txtAMaternoRef.requestFocus();
        } else if(v3){
            txtNombresRef.setError("Este campo no puede estar vacio");
            txtNombresRef.setText("");
            txtNombresRef.requestFocus();
        } else if(v4) {
            txtCalleRef.setError("Este campo no puede estar vacio");
            txtCalleRef.setText("");
            txtCalleRef.requestFocus();
        } else if(v5) {
            txtExtRef.setError("Este campo no puede estar vacio");
            txtExtRef.setText("");
            txtExtRef.requestFocus();
        } else if(v6){
            txtIntRef.setError("Este campo no puede estar vacio");
            txtIntRef.setText("");
            txtIntRef.requestFocus();
        } else if(v7){
            txtColRef.setError("Este campo no puede estar vacio");
            txtColRef.setText("");
            txtColRef.requestFocus();
        } else if(v8){
            txtCpRef.setError("Este campo no puede estar vacio");
            txtCpRef.setText("");
            txtCpRef.requestFocus();
        } else if(v9){
            txtMunRef.setError("Este campo no puede estar vacio");
            txtMunRef.setText("");
            txtMunRef.requestFocus();
        } else if(v10){
            txtEdoRef.setError("Este campo no puede estar vacio");
            txtEdoRef.setText("");
            txtExtRef.requestFocus();
        } else if(v11){
            txtTelRef.setError("Este campo no puede estar vacio");
            txtTelRef.setText("");
            txtTelRef.requestFocus();
        } else if(v12){
            txtCelRef.setError("Este campo no puede estar vacio");
            txtCelRef.setText("");
            txtCelRef.requestFocus();
        } else if ( v13) {
            txtCorreoRef.setError("Este campo no puede estar vacio");
            txtCorreoRef.setText("");
            txtCorreoRef.requestFocus();
        } else if ( v14) {
            txtParentescoRef.setError("Este campo no puede estar vacio");
            txtParentescoRef.setText("");
            txtParentescoRef.requestFocus();
        }
    }

    public void guardarReferenciasPersonales() {

        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_APATERNO_REF,txtApaternoRef.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO_REF,txtAMaternoRef.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_REF,txtNombresRef.getText().toString());
            values.put(DataDB.PR_SO_CALLE_REF,txtCalleRef.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_REF,txtExtRef.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_REF,txtIntRef.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_REF,txtColRef.getText().toString());
            values.put(DataDB.PR_SO_CP_REF,txtCpRef.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_REF,txtMunRef.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_REF,txtEdoRef.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA_REF,txtTelRef.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL_REF,txtCelRef.getText().toString());
            values.put(DataDB.PR_SO_CORREO_REF,txtCorreoRef.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_REF, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

    public void mostraDatos() {
        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {
            Cursor c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_REF, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    System.out.println("===============================================================================");
                    System.out.println("id:" + c.getString(0) + "\nApellido Paterno: " + c.getString(1) + "\nApellido Materno: " + c.getString(2) + "\nNombres: " + c.getString(3)+ "\nN Ext: " + c.getString(4) + "\nN Int: "
                            + c.getString(5) + "\nColonia: " + c.getString(6) + "\nCp: " + c.getString(7) + "\n" + c.getString(8) + "\n" + c.getString(9) + "\n"
                            + c.getString(10) + "\nPuesto: " + c.getString(11)+ "\nIngreso: " + c.getString(12) + "\nOtros ingresos: " + c.getString(13));

                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen cuentas a sincronizar");
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
    }
}

