package mipymex.mcs.com.pruebas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class ReferenciasPersonales extends AppCompatActivity {

    private EditText txtApaternoRef, txtAMaternoRef, txtNombresRef, txtCalleRef, txtExtRef, txtIntRef, txtColRef, txtCpRef, txtMunRef, txtEdoRef;
    private EditText txtTelRef, txtCelRef, txtCorreoRef, txtParentescoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referencias_personalesfamiliares);

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
}
