package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class ReferenciasPersonales extends AppCompatActivity {

    private EditText txtApaternoRef, txtAMaternoRef, txtNombresRef, txtCalleRef, txtExtRef, txtIntRef, txtColRef, txtCpRef, txtMunRef, txtEdoRef;
    private EditText txtTelRef, txtCelRef, txtCorreoRef, txtParentescoRef;
    private SQLiteDatabase db = null;
    private Cursor c = null;

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

            db.insert(DataDB.TABLE_NAME_SOLICITUD, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

}

