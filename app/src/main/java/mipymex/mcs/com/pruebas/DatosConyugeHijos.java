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
    private EditText txtNombreC, txtEdadC, txtParentescoC, txtTelefonoC, txtCelularC;
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_conyuge);

        txtNombreC = (EditText) findViewById(R.id.txtNombreC);
        txtEdadC = (EditText) findViewById(R.id.txtEdadC);
        txtParentescoC = (EditText) findViewById(R.id.txtParentescoC);
        txtTelefonoC = (EditText) findViewById(R.id.txtTelefonoC);
        txtCelularC = (EditText) findViewById(R.id.txttCelularC);

        lista = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, arrayList);
        lista.setAdapter(adaptador);

        agregarLista = (Button) findViewById(R.id.btnAgregarDatos);
        agregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(txtNombreC.getText().toString()
                            + txtEdadC.getText().toString()
                            + txtParentescoC.getText().toString()
                            + txtTelefonoC.getText().toString()
                            + txtCelularC.getText().toString());
                adaptador.notifyDataSetChanged();
            }
        });
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
            /*values.put(DataDB.PR_SO_NOMBRE2_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_EDAD2_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO2_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_TEL2_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_CEL2_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE3_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_EDAD3_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO3_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_TEL3_CONY,txtAp.getText().toString());
            values.put(DataDB.PR_SO_CEL3_CONY,txtAp.getText().toString());*/

            db.insert(DataDB.TABLE_NAME_SOLICITUD, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }

    }
}
