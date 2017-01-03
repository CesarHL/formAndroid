package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InformacionLaboral extends AppCompatActivity {

    private EditText txtNEmp, txtDCalle, txtNInt, txtNExtm, txtCol, txtCp, txtMun, txtEdo, txtDepLab, txtPuesto;
    private EditText txtIngreso, txtEMontoCred, txtInst, txtNJefe, txtAntig, txtTel, txtExt, txtFax;
    private RadioGroup rgRegistroIms, rgOtrosIngresos, rgComprobable, rgCredito, rgPeriodicidad;
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_laboral);

        txtNEmp = (EditText) findViewById(R.id.txtNombreEmpresa);
        rgRegistroIms = (RadioGroup) findViewById(R.id.rgRegistroIMSS);
        rgRegistroIms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoRegistroImss) {

                } else if (checkedId == R.id.negativoRegistroImss) {

                }
            }

        });

        txtDCalle = (EditText) findViewById(R.id.txtLaboralDomicilioCalle);
        txtNInt = (EditText) findViewById(R.id.txtLaboralInterior);
        txtNExtm = (EditText) findViewById(R.id.txtLaboralExterior);
        txtCol = (EditText) findViewById(R.id.txtLaboralColonia);
        txtCp = (EditText) findViewById(R.id.txtLaboralCP);
        txtMun= (EditText) findViewById(R.id.txtLaboralMunicipio);
        txtEdo = (EditText) findViewById(R.id.txtLaboralEstado);
        txtDepLab = (EditText) findViewById(R.id.txtLaboralDepartamento);
        txtPuesto = (EditText)findViewById(R.id.txtLaboralPuesto);
        txtIngreso = (EditText)findViewById(R.id.txtLaboralIngreso);
        rgOtrosIngresos = (RadioGroup) findViewById(R.id.rgOtrosIngresos);
        rgOtrosIngresos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoOtrosIngresos) {

                } else if (checkedId == R.id.negativoOtrosIngresos) {

                }
            }

        });

        rgComprobable = (RadioGroup) findViewById(R.id.rgComprobable);
        rgComprobable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoComprobable) {

                } else if (checkedId == R.id.negativoComprobable) {

                }
            }

        });

        rgCredito = (RadioGroup) findViewById(R.id.rgPagoDeCredito);
        rgCredito.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoPagoDeCredito) {

                } else if (checkedId == R.id.negativoPagoDeCredito) {

                }
            }
        });

        txtEMontoCred = (EditText) findViewById(R.id.txtEspecificacionMontoCredito);
        txtInst = (EditText) findViewById(R.id.txtInstitucion);
        txtNJefe = (EditText) findViewById(R.id.txtNombreJefeInmediato);
        txtAntig = (EditText) findViewById(R.id.txtAntiguedad);
        txtTel = (EditText) findViewById(R.id.txtTelefonoOficina);
        txtExt = (EditText) findViewById(R.id.txtExtension);
        txtFax = (EditText) findViewById(R.id.txtFax);
        rgPeriodicidad = (RadioGroup) findViewById(R.id.rgPeriodicidad);
        rgPeriodicidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.mesualLaboral) {

                } else if (checkedId == R.id.quicenal) {

                } else if (checkedId == R.id.catorcenal) {

                } else if (checkedId == R.id.semanal) {

                }
            }
        });


    }

    public void guardar() {

        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NOM_EMPRESA, txtNEmp.getText().toString());
            values.put(DataDB.PR_SO_IMSS,txtEMontoCred.getText().toString());//rg
            values.put(DataDB.PR_SO_CALLE_EMP,txtDCalle.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_EMP,txtNExtm.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_EMP,txtNInt.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_EMP,txtCol.getText().toString());
            values.put(DataDB.PR_SO_CP_EMP,txtCp.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_EMP,txtMun.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_EMP,txtEdo.getText().toString());
            values.put(DataDB.PR_SO_DEPARTAMENTO_EMP,txtDepLab.getText().toString());
            values.put(DataDB.PR_SO_PUESTO_EMP,txtPuesto.getText().toString());
            values.put(DataDB.PR_SO_INGRESO_EMP,txtIngreso.getText().toString());
            values.put(DataDB.PR_SO_OTROS_ING_EMP,txtIngreso.getText().toString());//rg
            values.put(DataDB.PR_SO_OTRO_ING_C_EMP,txtIngreso.getText().toString());
            values.put(DataDB.PR_SO_ING_COM,txtIngreso.getText().toString());//rg
            values.put(DataDB.PR_SO_PAGA_CRED_INS,txtIngreso.getText().toString());//rg
            values.put(DataDB.PR_SO_PAGA_IMPORTE_INS,txtEMontoCred.getText().toString());
            values.put(DataDB.PR_SO_PAGA_INS,txtInst.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_JEFE,txtNJefe.getText().toString());
            values.put(DataDB.PR_SO_ANTIGUEDAD_EMP,txtAntig.getText().toString());
            values.put(DataDB.PR_SO_TEL_EMP,txtTel.getText().toString());
            values.put(DataDB.PR_SO_EXTENSION_EMP,txtExt.getText().toString());
            values.put(DataDB.PR_SO_FAX_EMP,txtFax.getText().toString());
            values.put(DataDB.PR_SO_PERIODICIDAD_COBRO,txtIngreso.getText().toString());//rg
            //values.put(DataDB.PR_SO_PAGA_INS,txtAp.getText().toString());

                db.insert(DataDB.TABLE_NAME_INFO_LABORAL, null, values);
                System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }
}
