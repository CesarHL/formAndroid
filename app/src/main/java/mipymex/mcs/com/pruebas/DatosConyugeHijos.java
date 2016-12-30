package mipymex.mcs.com.pruebas;

import android.app.Activity;
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
}
