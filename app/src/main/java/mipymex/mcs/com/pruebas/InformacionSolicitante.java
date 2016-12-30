package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

public class InformacionSolicitante extends AppCompatActivity {

    private ViewPager viewPager;

    private LinearLayout page1;
    private LinearLayout page2;
    private ListView page3;
    private LinearLayout page4;

    private EditText txtAp, txtAm, txtNombre, txtLYF, txtEdad, txtRfc, txtCurp, txtFolioIfe, txtInConyuge;
    private EditText txtRegimen, txtEstadoCivil, txtPersonas, txtCalle, txtNExt, txtNInt, txtColonia, txtCP, txtMun;
    private EditText txtEstado, txtMontoCred, txtTelCasa, txtCelular, txtCorreo, txtEspCargoPublico;
    private Button agregar;
    private RadioGroup rdSexo, rdTConyuge, rdDep, rdRes, rdTRes, rdCVivienda, rdCPublico, rdCConyuge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_solicitante);

        txtAp = (EditText) findViewById(R.id.txtApellidoPaterno);
        txtAm = (EditText) findViewById(R.id.txtApellidoMaterno);
        txtNombre = (EditText) findViewById(R.id.txtNombresSolicitante);
        txtLYF = (EditText) findViewById(R.id.txtLugarFechaNacimiento);
        txtEdad = (EditText) findViewById(R.id.txtEdadSolicitante);
        rdSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rdSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.masculino){

                }else if (checkedId == R.id.femenino){

                }
            }

        });

        txtRfc = (EditText) findViewById(R.id.txtRfcSolicitante);
        txtCurp = (EditText) findViewById(R.id.txtCurp);
        txtFolioIfe = (EditText) findViewById(R.id.txtFolioCurp);
        txtEstadoCivil = (EditText) findViewById(R.id.txtEstadoCivil);
        rdTConyuge = (RadioGroup) findViewById(R.id.rgTrabajaConyuge);
        rdTConyuge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativo){

                }else if (checkedId == R.id.negativo){

                }
            }
        });

        txtInConyuge = (EditText) findViewById(R.id.txtIngresoConyuge);
        txtRegimen = (EditText) findViewById(R.id.txtRegimen);
        rdDep = (RadioGroup) findViewById(R.id.rgDependientes);
        rdDep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoDependientes){

                }else if (checkedId == R.id.negativoDependientes){

                }
            }
        });

        txtPersonas = (EditText) findViewById(R.id.txtNumeroPersonas);
        txtCalle = (EditText) findViewById(R.id.txtCalle);
        txtNExt = (EditText) findViewById(R.id.txtExterior);
        txtNInt = (EditText) findViewById(R.id.txtInterior);
        txtColonia = (EditText) findViewById(R.id.txtColoniaSolicitante);
        txtCP = (EditText) findViewById(R.id.txtCpSolicitante);
        txtMun = (EditText) findViewById(R.id.txtMunicipioSolicitante);
        txtEstado = (EditText) findViewById(R.id.txtEstadoSolicitante);
        rdRes =(RadioGroup) findViewById(R.id.rgResidencia);
        rdRes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.propia){

                }else if (checkedId == R.id.pagandose){

                } else if (checkedId == R.id.conFamiliares){

                }
            }
        });

        rdTRes =(RadioGroup) findViewById(R.id.rgTiempoResidencia);
        rdTRes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.anios){

                }else if (checkedId == R.id.meses){

                }
            }
        });

        rdCVivienda =(RadioGroup) findViewById(R.id.rgCreditoVivienda);
        rdCVivienda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.infonavit){

                }
            }
        });

        txtMontoCred = (EditText) findViewById(R.id.txtMontoMesual);
        txtTelCasa = (EditText) findViewById(R.id.txtTelefono);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtEspCargoPublico = (EditText) findViewById(R.id.txtEspecificarCargoPublicoConyuge);
        rdCPublico =(RadioGroup) findViewById(R.id.grpBtnCargoPublico);
        rdCPublico.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoCargoPublico){

                } else if (checkedId == R.id.negativoCargoPublico){

                }
            }
        });

        rdCConyuge =(RadioGroup) findViewById(R.id.grpBtnCargoPublicoConyuge);
        rdCConyuge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoCargoPublicoConyugeo){

                } else if (checkedId == R.id.negativoCargoPublicoConyuge){

                }
            }
        });

        agregar =(Button)findViewById(R.id.btnGuardar);
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Boolean v1 = txtAp.getText().toString().trim().equalsIgnoreCase("");
                Boolean v2 = txtAm.getText().toString().trim().equalsIgnoreCase("");
                Boolean v3 = txtNombre.getText().toString().trim().equalsIgnoreCase("");
                Boolean v4 = txtLYF.getText().toString().trim().equalsIgnoreCase("");
                Boolean v5 = txtEdad.getText().toString().trim().equalsIgnoreCase("");
                Boolean v6 = txtRfc.getText().toString().trim().equalsIgnoreCase("");
                Boolean v7 = txtCurp.getText().toString().trim().equalsIgnoreCase("");
                Boolean v8 = txtFolioIfe.getText().toString().trim().equalsIgnoreCase("");
                Boolean v9 = txtInConyuge.getText().toString().trim().equalsIgnoreCase("");
                Boolean v10 = txtRegimen.getText().toString().trim().equalsIgnoreCase("");
                Boolean v11 = txtEstadoCivil.getText().toString().trim().equalsIgnoreCase("");
                Boolean v12 = txtPersonas.getText().toString().trim().equalsIgnoreCase("");
                Boolean v13 = txtCalle.getText().toString().trim().equalsIgnoreCase("");
                Boolean v14 = txtNExt.getText().toString().trim().equalsIgnoreCase("");
                Boolean v15 = txtNInt.getText().toString().trim().equalsIgnoreCase("");
                Boolean v16 = txtColonia.getText().toString().trim().equalsIgnoreCase("");
                Boolean v17 = txtCP.getText().toString().trim().equalsIgnoreCase("");
                Boolean v18 = txtEstado.getText().toString().trim().equalsIgnoreCase("");
                Boolean v19 = txtMontoCred.getText().toString().trim().equalsIgnoreCase("");
                Boolean v20 = txtTelCasa.getText().toString().trim().equalsIgnoreCase("");
                Boolean v21 = txtCelular.getText().toString().trim().equalsIgnoreCase("");
                Boolean v22 = txtCorreo.getText().toString().trim().equalsIgnoreCase("");
                Boolean v23 = txtEspCargoPublico.getText().toString().trim().equalsIgnoreCase("");

                if (v1 || v2 || v3||v4||v5||v6||v7||v8||v9||v10||v11 || v12||v13||v14||v15||v16||
                        v17||v18||v19||v20||v21||v22||v23) {
                    txtNombre.setError("Este campo no puede estar vacio");
                    txtNombre.setText("");
                } else {
                    System.out.println("===================================" + txtAp.getText().toString());
                    Intent intent = new Intent(InformacionSolicitante.this, DatosConyugeHijos.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}
