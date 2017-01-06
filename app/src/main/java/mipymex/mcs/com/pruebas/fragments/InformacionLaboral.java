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
import android.widget.RadioGroup;

import mipymex.mcs.com.pruebas.DataDB;
import mipymex.mcs.com.pruebas.R;

public class InformacionLaboral extends Fragment {

    private EditText txtNEmp, txtDCalle, txtNInt, txtNExtm, txtCol, txtCp, txtMun, txtEdo, txtDepLab, txtPuesto;
    private EditText txtIngreso, txtEMontoCred, txtInst, txtNJefe, txtAntig, txtTel, txtExt, txtFax, txtOtros;
    private RadioGroup rgRegistroIms, rgOtrosIngresos, rgComprobable, rgCredito, rgPeriodicidad;
    private Button btnLaboral;
    private SQLiteDatabase db = null;
    private Cursor c = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informacion_laboral, null);

        btnLaboral = (Button) view.findViewById(R.id.btnGuardarLaboral);
        btnLaboral.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validar();
                guardarInformacionLaboral();
            }
        });


        txtNEmp = (EditText) view.findViewById(R.id.txtNombreEmpresa);
        rgRegistroIms = (RadioGroup) view.findViewById(R.id.rgRegistroIMSS);
        rgRegistroIms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoRegistroImss) {

                } else if (checkedId == R.id.negativoRegistroImss) {

                }
            }

        });

        txtDCalle = (EditText) view.findViewById(R.id.txtLaboralDomicilioCalle);
        txtNInt = (EditText) view.findViewById(R.id.txtLaboralInterior);
        txtNExtm = (EditText) view.findViewById(R.id.txtLaboralExterior);
        txtCol = (EditText) view.findViewById(R.id.txtLaboralColonia);
        txtCp = (EditText) view.findViewById(R.id.txtLaboralCP);
        txtMun= (EditText) view.findViewById(R.id.txtLaboralMunicipio);
        txtEdo = (EditText) view.findViewById(R.id.txtLaboralEstado);
        txtDepLab = (EditText) view.findViewById(R.id.txtLaboralDepartamento);
        txtPuesto = (EditText)view.findViewById(R.id.txtLaboralPuesto);
        txtIngreso = (EditText)view.findViewById(R.id.txtLaboralIngreso);


        rgOtrosIngresos = (RadioGroup) view.findViewById(R.id.rgOtrosIngresos);
        txtOtros = (EditText) view.findViewById(R.id.txtEspecificacionMonto);
        txtOtros.setVisibility(View.GONE);

        rgOtrosIngresos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoOtrosIngresos) {
                    txtOtros.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.negativoOtrosIngresos) {
                    txtOtros.setVisibility(View.GONE);
                }
            }

        });

        rgComprobable = (RadioGroup) view.findViewById(R.id.rgComprobable);
        rgComprobable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoComprobable) {

                } else if (checkedId == R.id.negativoComprobable) {

                }
            }

        });

        txtEMontoCred = (EditText) view.findViewById(R.id.txtEspecificacionMontoCredito);
        txtEMontoCred.setVisibility(View.GONE);
        rgCredito = (RadioGroup) view.findViewById(R.id.rgPagoDeCredito);
        rgCredito.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoPagoDeCredito) {
                    txtEMontoCred.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.negativoPagoDeCredito) {
                    txtEMontoCred.setVisibility(View.GONE);
                }
            }
        });

        txtInst = (EditText) view.findViewById(R.id.txtInstitucion);
        txtNJefe = (EditText) view.findViewById(R.id.txtNombreJefeInmediato);
        txtAntig = (EditText) view.findViewById(R.id.txtAntiguedad);
        txtTel = (EditText) view.findViewById(R.id.txtTelefonoOficina);
        txtExt = (EditText) view.findViewById(R.id.txtExtension);
        txtFax = (EditText) view.findViewById(R.id.txtFax);
        rgPeriodicidad = (RadioGroup) view.findViewById(R.id.rgPeriodicidad);
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

        return view;
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
                //txtNEmp.setError(null);
            } else if ( v2) {
                txtDCalle.setError("Este campo no puede estar vacio");
                txtDCalle.setText("");
                txtDCalle.requestFocus();
                //txtDCalle.setError(null);
            } else if ( v3) {
                txtNExtm.setError("Este campo no puede estar vacio");
                txtNExtm.setText("");
                txtNExtm.requestFocus();
                //txtNExtm.setError(null);
            } else if ( v4) {
                txtNInt.setError("Este campo no puede estar vacio");
                txtNInt.setText("");
                txtNInt.requestFocus();
              //  txtNInt.setError(null);
            } else if ( v5) {
                txtCol.setError("Este campo no puede estar vacio");
                txtCol.setText("");
                txtCol.requestFocus();
               // txtCol.setError(null);
            } else if ( v6) {
                txtCp.setError("Este campo no puede estar vacio");
                txtCp.setText("");
                txtCp.requestFocus();
               // txtCp.setError(null);
            } else if ( v7) {
                txtMun.setError("Este campo no puede estar vacio");
                txtMun.setText("");
                txtMun.requestFocus();
                //txtCp.setError(null);
            } else if ( v8) {
                txtEdo.setError("Este campo no puede estar vacio");
                txtEdo.setText("");
                txtEdo.requestFocus();
               // txtEdo.setError(null);
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


    public void guardarInformacionLaboral() {

        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

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
