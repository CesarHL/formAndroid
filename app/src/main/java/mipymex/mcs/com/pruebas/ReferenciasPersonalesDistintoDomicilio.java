package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ReferenciasPersonalesDistintoDomicilio extends AppCompatActivity {

    private EditText txtApaternoRefP, txtAMaternoRefP, txtNombresRefP, txtCalleRefP, txtExtRefP, txtIntRefP, txtColRefP, txtCpRefP, txtMunRefP, txtEdoRefP;
    private EditText txtTelRefP, txtCelRefP, txtCorreoRefP, txtParentescoRefP;
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referencias_personalesfamiliares);

        txtApaternoRefP = (EditText) findViewById(R.id.txtApellidoPaternoReferenciasP);
        txtAMaternoRefP = (EditText) findViewById(R.id.txtApellidoMaternoReferenciasP);
        txtNombresRefP = (EditText) findViewById(R.id.txtNombresReferenciasP);
        txtCalleRefP = (EditText) findViewById(R.id.txtCalleReferenciasP);
        txtExtRefP = (EditText) findViewById(R.id.txtExteriorReferenciasP);
        txtIntRefP = (EditText) findViewById(R.id.txtInteriorReferenciasP);
        txtColRefP = (EditText) findViewById(R.id.txtColoniaReferenciasP);
        txtCpRefP = (EditText) findViewById(R.id.txtCpReferenciasP);
        txtMunRefP = (EditText) findViewById(R.id.txtMunicipioReferenciasP);
        txtEdoRefP = (EditText) findViewById(R.id.txtEstadoReferenciasP);
        txtTelRefP = (EditText) findViewById(R.id.txtTelefonoReferenciasP);
        txtCelRefP = (EditText) findViewById(R.id.txtCelularReferenciasP);
        txtCorreoRefP = (EditText) findViewById(R.id.txtCorreoReferenciasP);
        txtParentescoRefP = (EditText) findViewById(R.id.txtParentescoReferenciasP);
    }

    public void validar(){
        Boolean v1 = txtApaternoRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v2 = txtAMaternoRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v3 = txtNombresRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v4 = txtCalleRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v5 = txtExtRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v6 = txtIntRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v7 = txtColRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v8 = txtCpRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v9 = txtMunRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v10 = txtEdoRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v11 = txtTelRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v12 = txtCelRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v13 = txtCorreoRefP.getText().toString().trim().equalsIgnoreCase("");
        Boolean v14 = txtParentescoRefP.getText().toString().trim().equalsIgnoreCase("");

        if(v1) {
            txtApaternoRefP.setError("Este campo no puede estar vacio");
            txtApaternoRefP.setText("");
        } else if(v2) {
            txtAMaternoRefP.setError("Este campo no puede estar vacio");
            txtAMaternoRefP.setText("");
        } else if(v3){
            txtNombresRefP.setError("Este campo no puede estar vacio");
            txtNombresRefP.setText("");
        } else if(v4) {
            txtCalleRefP.setError("Este campo no puede estar vacio");
            txtCalleRefP.setText("");
        } else if(v5) {
            txtExtRefP.setError("Este campo no puede estar vacio");
            txtExtRefP.setText("");
        } else if(v6){
            txtIntRefP.setError("Este campo no puede estar vacio");
            txtIntRefP.setText("");
        } else if(v7){
            txtColRefP.setError("Este campo no puede estar vacio");
            txtColRefP.setText("");
        } else if(v8){
            txtCpRefP.setError("Este campo no puede estar vacio");
            txtCpRefP.setText("");
        } else if(v9){
            txtMunRefP.setError("Este campo no puede estar vacio");
            txtMunRefP.setText("");
        } else if(v10){
            txtEdoRefP.setError("Este campo no puede estar vacio");
            txtEdoRefP.setText("");
        } else if(v11){
            txtTelRefP.setError("Este campo no puede estar vacio");
            txtTelRefP.setText("");
        } else if(v12){
            txtCelRefP.setError("Este campo no puede estar vacio");
            txtCelRefP.setText("");
        } else if ( v13) {
            txtCorreoRefP.setError("Este campo no puede estar vacio");
            txtCorreoRefP.setText("");
        } else if ( v14) {
            txtParentescoRefP.setError("Este campo no puede estar vacio");
            txtParentescoRefP.setText("");
        }

    }

    public void guardarReferenciasPersonalesDistintoDomiciolio() {

        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_APATERNO_REF_P, txtApaternoRefP.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO_REF_P, txtAMaternoRefP.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_REF_P, txtNombresRefP.getText().toString());
            values.put(DataDB.PR_SO_CALLE_REF_P, txtCalleRefP.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_REF_P, txtExtRefP.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_REF_P, txtIntRefP.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_REF_P, txtColRefP.getText().toString());
            values.put(DataDB.PR_SO_CP_REF_P, txtCpRefP.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_REF_P, txtMunRefP.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_REF_P, txtEdoRefP.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA_REF_P, txtTelRefP.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL_REF_P, txtCelRefP.getText().toString());
            values.put(DataDB.PR_SO_CORREO_REF_P, txtCorreoRefP.getText().toString());

            db.insert(DataDB.TABLE_NAME_SOLICITUD, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }
}
