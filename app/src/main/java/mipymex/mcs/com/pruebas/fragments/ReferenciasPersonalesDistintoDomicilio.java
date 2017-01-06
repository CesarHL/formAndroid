package mipymex.mcs.com.pruebas.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mipymex.mcs.com.pruebas.DataDB;
import mipymex.mcs.com.pruebas.R;

public class ReferenciasPersonalesDistintoDomicilio extends Fragment {

    private EditText txtApaternoRefP, txtAMaternoRefP, txtNombresRefP, txtCalleRefP, txtExtRefP, txtIntRefP, txtColRefP, txtCpRefP, txtMunRefP, txtEdoRefP;
    private EditText txtTelRefP, txtCelRefP, txtCorreoRefP, txtParentescoRefP;
    private SQLiteDatabase db = null;
    private Cursor c = null;
    private  Button agregar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.referencias_personalesfamiliares, null);

        txtApaternoRefP = (EditText) view.findViewById(R.id.txtApellidoPaternoReferenciasP);
        txtAMaternoRefP = (EditText) view.findViewById(R.id.txtApellidoMaternoReferenciasP);
        txtNombresRefP = (EditText) view.findViewById(R.id.txtNombresReferenciasP);
        txtCalleRefP = (EditText) view.findViewById(R.id.txtCalleReferenciasP);
        txtExtRefP = (EditText) view.findViewById(R.id.txtExteriorReferenciasP);
        txtIntRefP = (EditText) view.findViewById(R.id.txtInteriorReferenciasP);
        txtColRefP = (EditText) view.findViewById(R.id.txtColoniaReferenciasP);
        txtCpRefP = (EditText) view.findViewById(R.id.txtCpReferenciasP);
        txtMunRefP = (EditText) view.findViewById(R.id.txtMunicipioReferenciasP);
        txtEdoRefP = (EditText) view.findViewById(R.id.txtEstadoReferenciasP);
        txtTelRefP = (EditText) view.findViewById(R.id.txtTelefonoReferenciasP);
        txtCelRefP = (EditText) view.findViewById(R.id.txtCelularReferenciasP);
        txtCorreoRefP = (EditText) view.findViewById(R.id.txtCorreoReferenciasP);
        txtParentescoRefP = (EditText) view.findViewById(R.id.txtParentescoReferenciasP);

        agregar =(Button)view.findViewById(R.id.btnGuardarReferenciasDistintoDomicilio);
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validarReferenciasDistintoDomiciolio();
                guardarReferenciasPersonalesDistintoDomiciolio();
                //mostraDatos();
            }
        });

        return view;
    }

    public void validarReferenciasDistintoDomiciolio(){
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
            txtApaternoRefP.requestFocus();
        } else if(v2) {
            txtAMaternoRefP.setError("Este campo no puede estar vacio");
            txtAMaternoRefP.setText("");
            txtAMaternoRefP.requestFocus();
        } else if(v3){
            txtNombresRefP.setError("Este campo no puede estar vacio");
            txtNombresRefP.setText("");
            txtNombresRefP.requestFocus();
        } else if(v4) {
            txtCalleRefP.setError("Este campo no puede estar vacio");
            txtCalleRefP.setText("");
            txtCalleRefP.requestFocus();
        } else if(v5) {
            txtExtRefP.setError("Este campo no puede estar vacio");
            txtExtRefP.setText("");
            txtExtRefP.requestFocus();
        } else if(v6){
            txtIntRefP.setError("Este campo no puede estar vacio");
            txtIntRefP.setText("");
            txtIntRefP.requestFocus();
        } else if(v7){
            txtColRefP.setError("Este campo no puede estar vacio");
            txtColRefP.setText("");
            txtColRefP.requestFocus();
        } else if(v8){
            txtCpRefP.setError("Este campo no puede estar vacio");
            txtCpRefP.setText("");
            txtCpRefP.requestFocus();
        } else if(v9){
            txtMunRefP.setError("Este campo no puede estar vacio");
            txtMunRefP.setText("");
            txtMunRefP.requestFocus();
        } else if(v10){
            txtEdoRefP.setError("Este campo no puede estar vacio");
            txtEdoRefP.setText("");
            txtExtRefP.requestFocus();
        } else if(v11){
            txtTelRefP.setError("Este campo no puede estar vacio");
            txtTelRefP.setText("");
            txtTelRefP.requestFocus();
        } else if(v12){
            txtCelRefP.setError("Este campo no puede estar vacio");
            txtCelRefP.setText("");
            txtCelRefP.requestFocus();
        } else if ( v13) {
            txtCorreoRefP.setError("Este campo no puede estar vacio");
            txtCorreoRefP.setText("");
            txtCorreoRefP.requestFocus();
        } else if ( v14) {
            txtParentescoRefP.setError("Este campo no puede estar vacio");
            txtParentescoRefP.setText("");
            txtParentescoRefP.requestFocus();
        }
    }

    public void guardarReferenciasPersonalesDistintoDomiciolio() {

        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

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

            db.insert(DataDB.TABLE_NAME_INFO_REF_P, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }
}
