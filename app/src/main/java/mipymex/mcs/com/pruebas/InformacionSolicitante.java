package mipymex.mcs.com.pruebas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InformacionSolicitante extends AppCompatActivity {

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
        rdSexo = (RadioGroup) findViewById(R.id.sexo);
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
                System.out.println(txtAp.toString() + txtAm.toString() + txtNombre.toString() + txtLYF.toString());
                Intent intent = new Intent(InformacionSolicitante.this, DatosConyugeHijos.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
