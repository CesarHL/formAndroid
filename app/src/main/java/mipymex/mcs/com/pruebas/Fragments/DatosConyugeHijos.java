package mipymex.mcs.com.pruebas.fragments;

import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import mipymex.mcs.com.pruebas.DataDB;
import mipymex.mcs.com.pruebas.R;


public class DatosConyugeHijos extends Fragment {

    public static EditText txtNombreC, txtEdadC, txtParentescoC, txtTelefonoC, txtCelularC,txtNombreC2, txtEdadC2, txtParentescoC2, txtTelefonoC2, txtCelularC2,txtNombreC3, txtEdadC3, txtParentescoC3, txtTelefonoC3, txtCelularC3;
    private SQLiteDatabase db = null;
    private Cursor c = null;
    private Button agregar;
    public static int tamDatos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.datos_conyuge, null);
        txtNombreC = (EditText) view.findViewById(R.id.txtNombreCompletoCony);
        txtEdadC = (EditText) view.findViewById(R.id.txtEdadConyuge);
        txtParentescoC = (EditText) view.findViewById(R.id.txtParentesco1);
        txtTelefonoC = (EditText) view.findViewById(R.id.txtTelefonoConyuge);
        txtCelularC = (EditText) view.findViewById(R.id.txtCelularConyuge);

        txtNombreC2 = (EditText) view.findViewById(R.id.txtNombreCompletoCony2);
        txtEdadC2 = (EditText) view.findViewById(R.id.txtEdadConyuge2);
        txtParentescoC2 = (EditText) view.findViewById(R.id.txtParentescoConyuge2);
        txtTelefonoC2 = (EditText) view.findViewById(R.id.txtTelefonoConyuge2);
        txtCelularC2 = (EditText) view.findViewById(R.id.txtCelularConyuge2);

        txtNombreC3 = (EditText) view.findViewById(R.id.txtNombreCompletoCony3);
        txtEdadC3 = (EditText) view.findViewById(R.id.txtEdadConyuge3);
        txtParentescoC3 = (EditText) view.findViewById(R.id.txtParentescoConyuge3);
        txtTelefonoC3 = (EditText) view.findViewById(R.id.txtTelefonoConyuge3);
        txtCelularC3 = (EditText) view.findViewById(R.id.txtCelularConyuge3);

        agregar =(Button) view.findViewById(R.id.btnGuardarDatosConyuge);
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validarDatosConyuge();
                guardarDatosConyugeEHijos();
                // mostraDatos();
            }
        });
        return view;
    }

    public void validarDatosConyuge() {

        Boolean v1 = txtNombreC.getText().toString().trim().equalsIgnoreCase("");
        Boolean v2 = txtEdadC.getText().toString().trim().equalsIgnoreCase("");
        Boolean v3 = txtParentescoC.getText().toString().trim().equalsIgnoreCase("");
        Boolean v4 = txtTelefonoC.getText().toString().trim().equalsIgnoreCase("");
        Boolean v5 = txtCelularC.getText().toString().trim().equalsIgnoreCase("");
        Boolean v6 = txtNombreC2.getText().toString().trim().equalsIgnoreCase("");
        Boolean v7 = txtEdadC2.getText().toString().trim().equalsIgnoreCase("");
        Boolean v8 = txtParentescoC2.getText().toString().trim().equalsIgnoreCase("");
        Boolean v9 = txtTelefonoC2.getText().toString().trim().equalsIgnoreCase("");
        Boolean v10 = txtCelularC2.getText().toString().trim().equalsIgnoreCase("");
        Boolean v11 = txtNombreC3.getText().toString().trim().equalsIgnoreCase("");
        Boolean v12 = txtEdadC3.getText().toString().trim().equalsIgnoreCase("");
        Boolean v13 = txtParentescoC3.getText().toString().trim().equalsIgnoreCase("");
        Boolean v14 = txtTelefonoC3.getText().toString().trim().equalsIgnoreCase("");
        Boolean v15 = txtCelularC3.getText().toString().trim().equalsIgnoreCase("");

            if(v1) {
                txtNombreC.setError("Este campo no puede estar vacio");
                txtNombreC.setText("");
                txtNombreC.requestFocus();
            } else if(v2) {
                txtEdadC.setError("Este campo no puede estar vacio");
                txtEdadC.setText("");
                txtEdadC.requestFocus();
            }else if(v3) {
                txtParentescoC.setError("Este campo no puede estar vacio");
                txtParentescoC.setText("");
                txtParentescoC.requestFocus();
            }else if(v4) {
                txtTelefonoC.setError("Este campo no puede estar vacio");
                txtTelefonoC.setText("");
                txtTelefonoC.requestFocus();
            }else if(v5) {
                txtCelularC.setError("Este campo no puede estar vacio");
                txtCelularC.setText("");
                txtCelularC.requestFocus();
            }else if(v6) {
                txtNombreC2.setError("Este campo no puede estar vacio");
                txtNombreC2.setText("");
                txtNombreC2.requestFocus();
            }else if(v7) {
                txtEdadC2.setError("Este campo no puede estar vacio");
                txtEdadC2.setText("");
                txtEdadC2.requestFocus();
            }else if(v8) {
                txtParentescoC2.setError("Este campo no puede estar vacio");
                txtParentescoC2.setText("");
                txtParentescoC2.requestFocus();
            }else if(v9) {
                txtTelefonoC2.setError("Este campo no puede estar vacio");
                txtTelefonoC2.setText("");
                txtTelefonoC2.requestFocus();
            }else if(v10) {
                txtCelularC2.setError("Este campo no puede estar vacio");
                txtCelularC2.setText("");
                txtCelularC2.requestFocus();
            }else if(v11) {
                txtNombreC3.setError("Este campo no puede estar vacio");
                txtNombreC3.setText("");
                txtNombreC3.requestFocus();
            }else if(v12) {
                txtEdadC3.setError("Este campo no puede estar vacio");
                txtEdadC3.setText("");
                txtEdadC3.requestFocus();
            }else if(v13) {
                txtParentescoC3.setError("Este campo no puede estar vacio");
                txtParentescoC3.setText("");
                txtParentescoC3.requestFocus();
            }else if(v14) {
                txtTelefonoC3.setError("Este campo no puede estar vacio");
                txtTelefonoC3.setText("");
                txtTelefonoC3.requestFocus();
            }else if(v15) {
                txtCelularC3.setError("Este campo no puede estar vacio");
                txtCelularC3.setText("");
                txtCelularC3.requestFocus();
            }
    }

    public void guardarDatosConyugeEHijos() {

        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NOMBRE1_CONY,txtNombreC.getText().toString());
            values.put(DataDB.PR_SO_EDAD1_CONY,txtEdadC.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO1_CONY,txtParentescoC.getText().toString());
            values.put(DataDB.PR_SO_TEL1_CONY,txtTelefonoC.getText().toString());
            values.put(DataDB.PR_SO_CEL1_CONY,txtCelularC.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE2_CONY, txtNombreC2.getText().toString());
            values.put(DataDB.PR_SO_EDAD2_CONY, txtEdadC2.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO2_CONY,txtParentescoC2.getText().toString());
            values.put(DataDB.PR_SO_TEL2_CONY,txtTelefonoC2.getText().toString());
            values.put(DataDB.PR_SO_CEL2_CONY,txtCelularC2.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE3_CONY,txtNombreC3.getText().toString());
            values.put(DataDB.PR_SO_EDAD3_CONY,txtEdadC3.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO3_CONY, txtParentescoC3.getText().toString());
            values.put(DataDB.PR_SO_TEL3_CONY, txtTelefonoC3.getText().toString());
            values.put(DataDB.PR_SO_CEL3_CONY,txtCelularC3.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_CONYUGE, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
             db.close();
        }

    }

    public void mostraDatos() {
        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {
            Cursor c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_CONYUGE, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    System.out.println("===============================================================================");
                    System.out.println("id:" + c.getString(0) + "\nNombre1: " + c.getString(1) + "\nEdad1: " + c.getString(2) + "\nParentesco: " + c.getString(3)+ "\nTelefono: " + c.getString(4) + "\nCelular: "
                            + c.getString(5) + "\nNombre2: " + c.getString(6) + "\nEdad2: " + c.getString(7) + "\nParentesco2: " + c.getString(8) + "\nTelefono2: " + c.getString(9) + "\nCelular2: "
                            + c.getString(10) + "\nNombre3: " + c.getString(11)+ "\nEdad3: " + c.getString(12) + "\nParentesco3: " + c.getString(13) + "\nTelefono3: " + c.getString(14) + "\nCelular3: " + c.getString(15));

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
