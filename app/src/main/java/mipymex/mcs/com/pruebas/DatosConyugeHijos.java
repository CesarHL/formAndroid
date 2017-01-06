package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DatosConyugeHijos extends Activity {

    private ListView lista;
    private ArrayList arrayList;
    private ArrayAdapter<String> adaptador;
    private Button agregarLista;
    private EditText txtNombreC, txtEdadC, txtParentescoC, txtTelefonoC, txtCelularC,txtNombreC2, txtEdadC2, txtParentescoC2, txtTelefonoC2, txtCelularC2,txtNombreC3, txtEdadC3, txtParentescoC3, txtTelefonoC3, txtCelularC3;
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_conyuge);

        txtNombreC = (EditText) findViewById(R.id.txtNombreCompletoCony);
        txtEdadC = (EditText) findViewById(R.id.txtEdadConyuge);
        txtParentescoC = (EditText) findViewById(R.id.txtParentesco1);
        txtTelefonoC = (EditText) findViewById(R.id.txtTelefonoConyuge);
        txtCelularC = (EditText) findViewById(R.id.txtCelularConyuge);
        txtNombreC2 = (EditText) findViewById(R.id.txtNombreCompletoCony2);
        txtEdadC2 = (EditText) findViewById(R.id.txtEdadConyuge2);
        txtParentescoC2 = (EditText) findViewById(R.id.txtParentescoConyuge2);
        txtTelefonoC2 = (EditText) findViewById(R.id.txtTelefonoConyuge2);
        txtCelularC2 = (EditText) findViewById(R.id.txtCelularConyuge2);
        txtNombreC3 = (EditText) findViewById(R.id.txtNombreCompletoCony3);
        txtEdadC3 = (EditText) findViewById(R.id.txtEdadConyuge3);
        txtParentescoC3 = (EditText) findViewById(R.id.txtParentescoConyuge3);
        txtTelefonoC3 = (EditText) findViewById(R.id.txtTelefonoConyuge3);
        txtCelularC3 = (EditText) findViewById(R.id.txtCelularConyuge3);

    }

    public void guardarDatosConyugeEHijos() {

        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

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
           // db.close();
        }

    }
}
