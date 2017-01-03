package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.TextView;
import org.json.JSONException;
import java.io.IOException;
import java.net.MalformedURLException;

public class InformacionSolicitante extends AppCompatActivity {

    private EditText txtAp, txtAm, txtNombre, txtLYF, txtEdad, txtRfc, txtCurp, txtFolioIfe, txtInConyuge;
    private EditText txtRegimen, txtEstadoCivil, txtPersonas, txtCalle, txtNExt, txtNInt, txtColonia, txtCP, txtMun;
    private EditText txtEstado, txtMontoCred, txtTelCasa, txtCelular, txtCorreo, txtCargoPublicSolicitante, txtEspCargoPublico;
    private Button agregar;
    private RadioGroup rdSexo, rdTConyuge, rdDep, rdRes, rdTRes, rdCVivienda, rdCPublico, rdCConyuge;
    private TextView lblMontoCred, lblInConyuge, lblDependientes;
    private SQLiteDatabase db = null;
    private Cursor c = null;

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

        lblInConyuge = (TextView) findViewById(R.id.lblIngresoConyuge);
        lblInConyuge.setVisibility(View.GONE);
        txtInConyuge = (EditText) findViewById(R.id.txtIngresoConyuge);
        txtInConyuge.setVisibility(View.GONE);
        rdTConyuge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativo){
                    txtInConyuge.setVisibility(View.VISIBLE);
                    txtInConyuge.requestFocus();
                }else if (checkedId == R.id.negativo){
                    lblInConyuge.setVisibility(View.GONE);
                    txtInConyuge.setVisibility(View.GONE);
                }
            }
        });
        txtRegimen = (EditText) findViewById(R.id.txtRegimen);


        lblDependientes = (TextView) findViewById(R.id.lblNumeroPersonas);
        lblDependientes.setVisibility(View.GONE);
        txtPersonas = (EditText) findViewById(R.id.txtNumeroPersonas);
        txtPersonas.setVisibility(View.GONE);

        rdDep = (RadioGroup) findViewById(R.id.rgDependientes);
        rdDep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoDependientes){
                    lblDependientes.setVisibility(View.VISIBLE);
                    txtPersonas.setVisibility(View.VISIBLE);
                    txtPersonas.requestFocus();
                }else if (checkedId == R.id.negativoDependientes){
                    lblDependientes.setVisibility(View.GONE);
                    txtPersonas.setVisibility(View.GONE);
                }
            }
        });

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


        lblMontoCred = (TextView) findViewById(R.id.txtMontoMesual);
        lblMontoCred.setVisibility(View.GONE);
        txtMontoCred = (EditText) findViewById(R.id.txtMontoMesual);
        txtMontoCred.setVisibility(View.GONE);

        rdCVivienda =(RadioGroup) findViewById(R.id.rgCreditoVivienda);
        rdCVivienda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.infonavit){
                    lblMontoCred.setVisibility(View.VISIBLE);
                    txtMontoCred.setVisibility(View.VISIBLE);
                }
            }
        });


        txtTelCasa = (EditText) findViewById(R.id.txtTelefono);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtCargoPublicSolicitante = (EditText) findViewById(R.id.txtEspecificarSolicitante);
        txtCargoPublicSolicitante.setVisibility(View.GONE);
        txtEspCargoPublico = (EditText) findViewById(R.id.txtEspecificarCargoPublicoConyuge);
        txtEspCargoPublico.setVisibility(View.GONE);
        rdCPublico =(RadioGroup) findViewById(R.id.grpBtnCargoPublico);

        rdCPublico.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoCargoPublico){
                    txtCargoPublicSolicitante.setVisibility(View.VISIBLE);
                    txtCargoPublicSolicitante.requestFocus();
                } else if (checkedId == R.id.negativoCargoPublico){
                    txtCargoPublicSolicitante.setVisibility(View.GONE);
                }
            }
        });

        rdCConyuge =(RadioGroup) findViewById(R.id.grpBtnCargoPublicoConyuge);
        rdCConyuge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoCargoPublicoConyugeo){
                    txtEspCargoPublico.setVisibility(View.VISIBLE);
                    txtEspCargoPublico.requestFocus();
                } else if (checkedId == R.id.negativoCargoPublicoConyuge){
                    txtEspCargoPublico.setVisibility(View.GONE);
                }
            }
        });

        agregar =(Button)findViewById(R.id.btnGuardar);
       // agregar.setOnClickListener({});
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardar();
                mostraDatos();
            }
        });


    }

    public void validar(){
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

        if(v1) {
            txtAp.setError("Este campo no puede estar vacio");
            txtAp.setText("");
        } else if(v2) {
            txtAm.setError("Este campo no puede estar vacio");
            txtAm.setText("");
        } else if(v3){
            txtNombre.setError("Este campo no puede estar vacio");
            txtNombre.setText("");
        } else if(v4) {
            txtLYF.setError("Este campo no puede estar vacio");
            txtLYF.setText("");
        } else if(v5) {
            txtEdad.setError("Este campo no puede estar vacio");
            txtEdad.setText("");
        } else if(v6){
            txtRfc.setError("Este campo no puede estar vacio");
            txtRfc.setText("");
        } else if(v7){
            txtCurp.setError("Este campo no puede estar vacio");
            txtCurp.setText("");
        } else if(v8){
            txtFolioIfe.setError("Este campo no puede estar vacio");
            txtFolioIfe.setText("");
        } else if(v9){
            txtInConyuge.setError("Este campo no puede estar vacio");
            txtInConyuge.setText("");
        } else if(v10){
            txtRegimen.setError("Este campo no puede estar vacio");
            txtRegimen.setText("");
        } else if(v11){
            txtEstadoCivil.setError("Este campo no puede estar vacio");
            txtEstadoCivil.setText("");
        } else if(v12){
            txtPersonas.setError("Este campo no puede estar vacio");
            txtPersonas.setText("");
        } else if ( v13) {
            txtCalle.setError("Este campo no puede estar vacio");
            txtCalle.setText("");
        } else if ( v14) {
            txtNExt.setError("Este campo no puede estar vacio");
            txtNExt.setText("");
        } else if ( v15) {
            txtNInt.setError("Este campo no puede estar vacio");
            txtNInt.setText("");
        } else if ( v16) {
            txtColonia.setError("Este campo no puede estar vacio");
            txtColonia.setText("");
        } else if ( v17) {
            txtCP.setError("Este campo no puede estar vacio");
            txtCP.setText("");
        } else if ( v18) {
            txtEstado.setError("Este campo no puede estar vacio");
            txtEstado.setText("");
        } else if ( v19) {
            txtMontoCred.setError("Este campo no puede estar vacio");
            txtMontoCred.setText("");
        } else if ( v20) {
            txtTelCasa.setError("Este campo no puede estar vacio");
            txtTelCasa.setText("");
        } else if ( v21) {
            txtCelular.setError("Este campo no puede estar vacio");
            txtCelular.setText("");
        } else if ( v22) {
            txtCorreo.setError("Este campo no puede estar vacio");
            txtCorreo.setText("");
        } else if ( v23) {
            txtEspCargoPublico.setError("Este campo no puede estar vacio");
            txtEspCargoPublico.setText("");
        }

    }

    public void guardar() {
        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

           /* values.put(DataDB.PR_SO_NUMSOLICITUD, pr_so_numsolicitud);
            values.put(DataDB.PR_SO_MTO_PRESTAMO, pr_so_mto_prestamo);
            values.put(DataDB.PR_SO_PLAZO, pr_so_plazo);
            values.put(DataDB.PR_SO_ASESOR, pr_so_asesor);
            values.put(DataDB.PR_SO_DTE_SOLICITUD, pr_so_dte_solicitud);
            values.put(DataDB.PR_SO_DESTINO, pr_so_destino);*/

            values.put(DataDB.PR_SO_APATERNO, txtAp.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO, txtAm.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE, txtNombre.getText().toString());
            values.put(DataDB.PR_SO_DTE_NACIMIENTO, txtLYF.getText().toString());// dividir lugar y fecha de naciento
            values.put(DataDB.PR_SO_LUGAR, txtAp.getText().toString());// dividir lugar y fecha de naciento
            values.put(DataDB.PR_SO_EDAD, txtEdad.getText().toString());
            values.put(DataDB.PR_SO_SEXO, txtAp.getText().toString()); //sacar de rg
            values.put(DataDB.PR_SO_RFC , txtRfc.getText().toString());
            values.put(DataDB.PR_SO_CURP, txtCurp.getText().toString());
            values.put(DataDB.PR_SO_INE, txtFolioIfe.getText().toString());
            values.put(DataDB.PR_SO_EDO_CIVIL, txtEstadoCivil.getText().toString());
            values.put(DataDB.PR_SO_CONYUGE_TRABAJA, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_INGRESO_CONYUGE, txtInConyuge.getText().toString());
            values.put(DataDB.PR_SO_DEPENDIENTES, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_NUMDEPENDIENTES, txtPersonas.getText().toString());
            values.put(DataDB.PR_SO_CALLE, txtCalle.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT, txtNExt.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT, txtNInt.getText().toString());
            values.put(DataDB.PR_SO_COLONIA, txtColonia.getText().toString());
            values.put(DataDB.PR_SO_CP, txtCP.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO, txtMun.getText().toString());
            values.put(DataDB.PR_SO_ESTADO, txtEstado.getText().toString());
            values.put(DataDB.PR_SO_TIPO_RECIDENCIA, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_A, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_M, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_CREDITO_VI, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_PAGO_VIVIENDA, txtMontoCred.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA, txtTelCasa.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL, txtCelular.getText().toString());
            values.put(DataDB.PR_SO_CORREO, txtCorreo.getText().toString());
            values.put(DataDB.PR_SO_CARGO_P_PUBLICO, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_CARGO_PUBLICO, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_CONYUGE_P_PUBLICO, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_CONYUGE_PUBLICO, txtAp.getText().toString());//rg

            db.insert(DataDB.TABLE_NAME_SOLICITUD, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

    public void mostraDatos() {

        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
        try {
            String[] valores_recuperar = {
                    DataDB.PR_SO_APATERNO,
                    DataDB.PR_SO_AMATERNO,
                    DataDB.PR_SO_CALLE
            };

            c = db.query(DataDB.TABLE_NAME_SOLICITUD, valores_recuperar,
                    null, null, null, null, null);
            System.out.println(c.getColumnCount());

            if (c.moveToFirst()) {
                do {
                    System.out.println(c.getString(0)+c.getString(1)+c.getString(2));

                } while (c.moveToNext());
                    c.close();
            } else {
                System.out.println("No existe información");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}


