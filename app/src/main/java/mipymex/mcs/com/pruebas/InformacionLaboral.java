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
import android.widget.RadioGroup;

public class InformacionLaboral extends AppCompatActivity {

    public static EditText txtNEmp , txtDCalle, txtNInt, txtNExtm, txtCol, txtCp, txtMun, txtEdo, txtDepLab, txtPuesto;
    public static EditText txtIngreso, txtEMontoCred, txtInst, txtNJefe, txtAntig, txtTel, txtExt, txtFax, txtOtros;
    private RadioGroup rgRegistroIms, rgOtrosIngresos, rgComprobable, rgCredito, rgPeriodicidad;
    private Button btnLaboral;
    public static int tamDatos;
    public static String otrosIngresos = " ", registroImss = " " , otrosComprobable= " ", pagaCredito = " ", periodicidad = " ";
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_laboral);

        txtNEmp = (EditText) findViewById(R.id.txtNombreEmpresa);
        txtNEmp.setText("");

        rgRegistroIms = (RadioGroup) findViewById(R.id.rgRegistroIMSS);
        rgRegistroIms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoRegistroImss) {
                    registroImss = "si";
                } else if (checkedId == R.id.negativoRegistroImss) {
                    registroImss = "no";
                }
            }

        });

        txtDCalle = (EditText) findViewById(R.id.txtLaboralDomicilioCalle);
        txtDCalle.setText("");
        txtNInt = (EditText) findViewById(R.id.txtLaboralInterior);
        txtNInt.setText("");
        txtNExtm = (EditText) findViewById(R.id.txtLaboralExterior);
        txtNExtm.setText("");
        txtCol = (EditText) findViewById(R.id.txtLaboralColonia);
        txtCol.setText("");
        txtCp = (EditText) findViewById(R.id.txtLaboralCP);
        txtCp.setText("");
        txtMun = (EditText) findViewById(R.id.txtLaboralMunicipio);
        txtMun.setText("");
        txtEdo = (EditText) findViewById(R.id.txtLaboralEstado);
        txtEdo.setText("");
        txtDepLab = (EditText) findViewById(R.id.txtLaboralDepartamento);
        txtDepLab.setText("");
        txtPuesto = (EditText)findViewById(R.id.txtLaboralPuesto);
        txtPuesto.setText("");
        txtIngreso = (EditText)findViewById(R.id.txtLaboralIngreso);
        txtIngreso.setText("");

        rgOtrosIngresos = (RadioGroup) findViewById(R.id.rgOtrosIngresos);
        txtOtros = (EditText) findViewById(R.id.txtEspecificacionMonto);
        txtOtros.setVisibility(View.GONE);

        otrosIngresos = " ";
        rgOtrosIngresos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.afirmativoOtrosIngresos) {
                    otrosIngresos = "si";

                    txtOtros.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.negativoOtrosIngresos) {
                    otrosIngresos = "no";
                    txtOtros.setVisibility(View.GONE);
                }
            }

        });

        rgComprobable = (RadioGroup) findViewById(R.id.rgComprobable);
        rgComprobable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoComprobable) {
                    otrosComprobable = "si";
                } else if (checkedId == R.id.negativoComprobable) {
                    otrosComprobable = "no";
                }
            }

        });

        txtEMontoCred = (EditText) findViewById(R.id.txtEspecificacionMontoCredito);
        txtEMontoCred.setVisibility(View.GONE);
        rgCredito = (RadioGroup) findViewById(R.id.rgPagoDeCredito);
        rgCredito.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoPagoDeCredito) {
                    pagaCredito = "si";
                    txtEMontoCred.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.negativoPagoDeCredito) {
                    pagaCredito = "no";
                    txtEMontoCred.setVisibility(View.GONE);
                }
            }
        });

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
                    periodicidad = "mensual";
                } else if (checkedId == R.id.quicenal) {
                    periodicidad = "quincenal";
                } else if (checkedId == R.id.catorcenal) {
                    periodicidad = "catorcenal";
                } else if (checkedId == R.id.semanal) {
                    periodicidad = "semanal";
                }
            }
        });

        btnLaboral = (Button) findViewById(R.id.btnGuardarLaboral);
        btnLaboral.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              // validar();
                guardarInformacionLaboral();
              //  mostraDatos();
            }
        });

    }


    public void guardarInformacionLaboral() {
        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NOM_EMPRESA, txtNEmp.getText().toString());
            values.put(DataDB.PR_SO_IMSS, registroImss);//rg
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
            values.put(DataDB.PR_SO_OTROS_ING_EMP,otrosIngresos);//rg
            values.put(DataDB.PR_SO_OTRO_ING_C_EMP,txtOtros.getText().toString());
            values.put(DataDB.PR_SO_ING_COM, otrosComprobable);//rg
            values.put(DataDB.PR_SO_PAGA_CRED_INS, pagaCredito);//rg
            values.put(DataDB.PR_SO_PAGA_IMPORTE_INS,txtEMontoCred.getText().toString());//
            values.put(DataDB.PR_SO_PAGA_INS, txtInst.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_JEFE,txtNJefe.getText().toString());
            values.put(DataDB.PR_SO_ANTIGUEDAD_EMP,txtAntig.getText().toString());
            values.put(DataDB.PR_SO_TEL_EMP,txtTel.getText().toString());
            values.put(DataDB.PR_SO_EXTENSION_EMP,txtExt.getText().toString());
            values.put(DataDB.PR_SO_FAX_EMP,txtFax.getText().toString());
            values.put(DataDB.PR_SO_PERIODICIDAD_COBRO,periodicidad);//rg

                db.insert(DataDB.TABLE_NAME_INFO_LABORAL, null, values);
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
            Cursor c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_LABORAL, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    System.out.println("===============================================================================");
                    System.out.println("id:" + c.getString(0) + "\nEmpresa: " + c.getString(1) + "\nImss: " + c.getString(2) + "\nCalle: " + c.getString(3)+ "\nN Ext: " + c.getString(4) + "\nN Int: "
                            + c.getString(5) + "\nColonia: " + c.getString(6) + "\nCp: " + c.getString(7) + "\n" + c.getString(8) + "\n" + c.getString(9) + "\n"
                            + c.getString(10) + "\nPuesto: " + c.getString(11)+ "\nIngreso: " + c.getString(12) + "\nOtros ingresos: " + c.getString(13) + "\nMonto otros: " + c.getString(14) + "\nComprobable: "
                            + c.getString(15) + "\nPaga credito: " + c.getString(16) + "\n" + c.getString(17) + "\n" + c.getString(18) + "\n" + c.getString(19) + "\n"
                            + c.getString(20) + "\n" + c.getString(21) + "\nExtensión: " + c.getString(22)+ "\nFax: "  + c.getString(23) + "\nPeriodicidad: "  + c.getString(24));

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

    public void validar() {

        Boolean v1 = txtNEmp.getText().toString().trim().equalsIgnoreCase("");
        Boolean v2 = txtDCalle.getText().toString().trim().equalsIgnoreCase("");
        Boolean v3 = txtNExtm.getText().toString().trim().equalsIgnoreCase("");
        Boolean v4 = txtNInt.getText().toString().trim().equalsIgnoreCase("");
        Boolean v5 = txtCol.getText().toString().trim().equalsIgnoreCase("");
        Boolean v6 = txtCp.getText().toString().trim().equalsIgnoreCase("");
        Boolean v7 = txtMun.getText().toString().trim().equalsIgnoreCase("");
        Boolean v8 = txtEdo.getText().toString().trim().equalsIgnoreCase("");
        Boolean v9 = txtDepLab.getText().toString().trim().equalsIgnoreCase("");
        Boolean v10 = txtPuesto.getText().toString().trim().equalsIgnoreCase("");
        Boolean v11 = txtIngreso.getText().toString().trim().equalsIgnoreCase("");
        Boolean v12 = txtOtros.getText().toString().trim().equalsIgnoreCase("");
        Boolean v13 = txtEMontoCred.getText().toString().trim().equalsIgnoreCase("");
        Boolean v14 = txtInst.getText().toString().trim().equalsIgnoreCase("");
        Boolean v15 = txtNJefe.getText().toString().trim().equalsIgnoreCase("");
        Boolean v16 = txtAntig.getText().toString().trim().equalsIgnoreCase("");
        Boolean v17 = txtTel.getText().toString().trim().equalsIgnoreCase("");
        Boolean v18 = txtExt.getText().toString().trim().equalsIgnoreCase("");
        Boolean v19 = txtFax.getText().toString().trim().equalsIgnoreCase("");

        if ( v1) {
            txtNEmp.setError("Este campo no puede estar vacio");
            txtNEmp.setText("");
            txtNEmp.requestFocus();
        } else if ( v2) {
            txtDCalle.setError("Este campo no puede estar vacio");
            txtDCalle.setText("");
            txtDCalle.requestFocus();
        } else if ( v3) {
            txtNExtm.setError("Este campo no puede estar vacio");
            txtNExtm.setText("");
            txtNExtm.requestFocus();
        } else if ( v4) {
            txtNInt.setError("Este campo no puede estar vacio");
            txtNInt.setText("");
            txtNInt.requestFocus();

        } else if ( v5) {
            txtCol.setError("Este campo no puede estar vacio");
            txtCol.setText("");
            txtCol.requestFocus();

        } else if ( v6) {
            txtCp.setError("Este campo no puede estar vacio");
            txtCp.setText("");
            txtCp.requestFocus();

        } else if ( v7) {
            txtMun.setError("Este campo no puede estar vacio");
            txtMun.setText("");
            txtMun.requestFocus();

        } else if ( v8) {
            txtEdo.setError("Este campo no puede estar vacio");
            txtEdo.setText("");
            txtEdo.requestFocus();
        } else if ( v9) {
            txtDepLab.setError("Este campo no puede estar vacio");
            txtDepLab.setText("");
            txtDepLab.requestFocus();
        } else if ( v10) {
            txtPuesto.setError("Este campo no puede estar vacio");
            txtPuesto.setText("");
            txtPuesto.requestFocus();
            txtPuesto.setError(null);
        } else if ( v11) {
            txtIngreso.setError("Este campo no puede estar vacio");
            txtIngreso.setText("");
            txtIngreso.requestFocus();
        } else if ( v12) {
            txtOtros.setError("Este campo no puede estar vacio");
            txtOtros.setText("");
            txtOtros.requestFocus();
        } else if ( v13) {
            txtEMontoCred.setError("Este campo no puede estar vacio");
            txtEMontoCred.setText("");
            txtEMontoCred.requestFocus();
        } else if ( v14) {
            txtInst.setError("Este campo no puede estar vacio");
            txtInst.setText("");
            txtInst.requestFocus();
        } else if ( v15) {
            txtNJefe.setError("Este campo no puede estar vacio");
            txtNJefe.setText("");
            txtNJefe.requestFocus();
        } else if ( v16) {
            txtAntig.setError("Este campo no puede estar vacio");
            txtAntig.setText("");
            txtAntig.requestFocus();
        } else if ( v17) {
            txtTel.setError("Este campo no puede estar vacio");
            txtTel.setText("");
            txtTel.requestFocus();
        } else if ( v18) {
            txtExt.setError("Este campo no puede estar vacio");
            txtExt.setText("");
            txtTel.requestFocus();
        } else if ( v19) {
            txtFax.setError("Este campo no puede estar vacio");
            txtFax.setText("");
            txtFax.requestFocus();
        }
    }
}
