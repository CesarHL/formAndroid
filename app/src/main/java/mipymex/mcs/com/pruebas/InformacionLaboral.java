package mipymex.mcs.com.pruebas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by Erick on 27/12/2016.
 */

public class InformacionLaboral extends AppCompatActivity {

    private EditText txtNEmp, txtDCalle, txtNInt, txtNExtm, txtCol, txtCp, txtMun, txtEdo, txtDepLab, txtPuesto;
    private EditText txtIngreso, txtEMonto, txtEMontoCred, txtInst, txtNJefe, txtAntig, txtTel, txtExt, txtFax;
    private RadioGroup rgRegistroIms, rgOtrosIngresos, rgComprobable, rgCredito, rgPeriodicidad;

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

        txtEMonto = (EditText) findViewById(R.id.txtEspecificacionMonto);
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
}
