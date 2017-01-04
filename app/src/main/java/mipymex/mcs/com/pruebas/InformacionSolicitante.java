package mipymex.mcs.com.pruebas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InformacionSolicitante extends AppCompatActivity {

    private EditText txtAp, txtAm, txtNombre, txtLugarNacimiento, txtFechaNacimiento, txtEdad, txtRfc, txtCurp, txtFolioIfe, txtInConyuge;
    private EditText txtRegimen, txtEstadoCivil, txtPersonas, txtCalle, txtNExt, txtNInt, txtColonia, txtCP, txtMun;
    private EditText txtEstado, txtMontoCred, txtTelCasa, txtCelular, txtCorreo, txtCargoPublicSolicitante, txtEspCargoPublico;
    private Button agregar;
    private RadioGroup rdSexo, rdTConyuge, rdDep, rdRes, rdTRes, rdCPublico, rdCConyuge;
    private TextView lblMontoCred, lblInConyuge, lblDependientes;
    private CheckBox checkVivienda;
    private SQLiteDatabase db = null;
    private Cursor c = null;
    private String sexo , estadoPropiedad, tiempoResidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_solicitante);

        txtAp = (EditText) findViewById(R.id.txtApellidoPaterno);
        txtAp.requestFocus();
        txtAm = (EditText) findViewById(R.id.txtApellidoMaterno);
        txtNombre = (EditText) findViewById(R.id.txtNombresSolicitante);
        txtLugarNacimiento = (EditText) findViewById(R.id.txtLugarNacimiento);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        txtEdad = (EditText) findViewById(R.id.txtEdadSolicitante);
        rdSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rdSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.masculino){
                    sexo = "masculino";
                } else if (checkedId == R.id.femenino){
                    sexo = "femenino";
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
                    lblInConyuge.setVisibility(View.VISIBLE);
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
                    estadoPropiedad = "propia";
                }else if (checkedId == R.id.pagandose){
                    estadoPropiedad = "pagandose";
                } else if (checkedId == R.id.conFamiliares){
                    estadoPropiedad = "vive con familiares";
                }
            }
        });

        /*
        * String tipoResidencia = rdTRes.isActivated() ? "meses" : "años";
                System.out.println("================================ =============" + tipoResidencia);
        * */

        rdTRes =(RadioGroup) findViewById(R.id.rgTiempoResidencia);
        rdTRes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.anios){
                    tiempoResidencia = "años";
                } else if (checkedId == R.id.meses){
                    tiempoResidencia = "menses";
                }
            }
        });


        lblMontoCred = (TextView) findViewById(R.id.lblMontoMensual);
        lblMontoCred.setVisibility(View.GONE);
        txtMontoCred = (EditText) findViewById(R.id.txtMontoMesual);
        txtMontoCred.setVisibility(View.GONE);
        checkVivienda =(CheckBox) findViewById(R.id.infonavit);
        checkVivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    lblMontoCred.setVisibility(View.VISIBLE);
                    txtMontoCred.setVisibility(View.VISIBLE);
                } else {
                    lblMontoCred.setVisibility(View.GONE);
                    txtMontoCred.setVisibility(View.GONE);
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
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validar();
                //guardarInformacionSolicitante();
                //mostraDatos();
            }
        });
    }

    public void validar(){
        Boolean v1 = txtAp.getText().toString().trim().equalsIgnoreCase("");
        Boolean v2 = txtAm.getText().toString().trim().equalsIgnoreCase("");
        Boolean v3 = txtNombre.getText().toString().trim().equalsIgnoreCase("");
        Boolean v4 = txtLugarNacimiento.getText().toString().trim().equalsIgnoreCase("");
        Boolean v24 = txtFechaNacimiento.getText().toString().trim().equalsIgnoreCase("");
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
            txtAp.requestFocus();
        } else if(v2) {
            txtAm.setError("Este campo no puede estar vacio");
            txtAm.setText("");
            txtAm.requestFocus();
        } else if(v3){
            txtNombre.setError("Este campo no puede estar vacio");
            txtNombre.setText("");
            txtNombre.requestFocus();
        } else if(v4) {
            txtLugarNacimiento.setError("Este campo no puede estar vacio");
            txtLugarNacimiento.setText("");
            txtLugarNacimiento.requestFocus();
        } else if(v24) {
            txtFechaNacimiento.setError("Este campo no puede estar vacio");
            txtFechaNacimiento.setText("");
            txtFechaNacimiento.requestFocus();
        }else if(v5) {
            txtEdad.setError("Este campo no puede estar vacio");
            txtEdad.setText("");
            txtEdad.requestFocus();
        } else if(v6){
            txtRfc.setError("Este campo no puede estar vacio");
            txtRfc.setText("");
            txtRfc.requestFocus();
        } else if(v7){
            txtCurp.setError("Este campo no puede estar vacio");
            txtCurp.setText("");
            txtCurp.requestFocus();
        } else if(v8){
            txtFolioIfe.setError("Este campo no puede estar vacio");
            txtFolioIfe.setText("");
            txtFolioIfe.requestFocus();
        } else if(v9){
            txtInConyuge.setError("Este campo no puede estar vacio");
            txtInConyuge.setText("");
            txtInConyuge.requestFocus();
        } else if(v10){
            txtRegimen.setError("Este campo no puede estar vacio");
            txtRegimen.setText("");
            txtRegimen.requestFocus();
        } else if(v11){
            txtEstadoCivil.setError("Este campo no puede estar vacio");
            txtEstadoCivil.setText("");
            txtEstadoCivil.requestFocus();
        } else if(v12){
            txtPersonas.setError("Este campo no puede estar vacio");
            txtPersonas.setText("");
            txtPersonas.requestFocus();
        } else if ( v13) {
            txtCalle.setError("Este campo no puede estar vacio");
            txtCalle.setText("");
            txtCalle.requestFocus();
        } else if ( v14) {
            txtNExt.setError("Este campo no puede estar vacio");
            txtNExt.setText("");
        } else if ( v15) {
            txtNInt.setError("Este campo no puede estar vacio");
            txtNInt.setText("");
            txtNInt.requestFocus();
        } else if ( v16) {
            txtColonia.setError("Este campo no puede estar vacio");
            txtColonia.setText("");
            txtColonia.requestFocus();
        } else if ( v17) {
            txtCP.setError("Este campo no puede estar vacio");
            txtCP.setText("");
            txtCP.requestFocus();
        } else if ( v18) {
            txtEstado.setError("Este campo no puede estar vacio");
            txtEstado.setText("");
            txtEstado.requestFocus();
        } else if ( v19) {
            txtMontoCred.setError("Este campo no puede estar vacio");
            txtMontoCred.setText("");
            txtMontoCred.requestFocus();
        } else if ( v20) {
            txtTelCasa.setError("Este campo no puede estar vacio");
            txtTelCasa.setText("");
            txtTelCasa.requestFocus();
        } else if ( v21) {
            txtCelular.setError("Este campo no puede estar vacio");
            txtCelular.setText("");
            txtCelular.requestFocus();
        } else if ( v22) {
            txtCorreo.setError("Este campo no puede estar vacio");
            txtCorreo.setText("");
            txtCorreo.requestFocus();
        } else if ( v23) {
            txtEspCargoPublico.setError("Este campo no puede estar vacio");
            txtEspCargoPublico.setText("");
            txtEspCargoPublico.requestFocus();
        }

    }

    public void guardarInformacionSolicitante() {
        db = getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NUMSOLICITUD, txtAp.getText().toString());
            values.put(DataDB.PR_SO_MTO_PRESTAMO, txtAp.getText().toString());
            values.put(DataDB.PR_SO_PLAZO, txtAp.getText().toString());
            values.put(DataDB.PR_SO_ASESOR, txtAp.getText().toString());
            values.put(DataDB.PR_SO_DTE_SOLICITUD, txtAp.getText().toString());
            values.put(DataDB.PR_SO_DESTINO, txtAp.getText().toString());

            values.put(DataDB.PR_SO_APATERNO, txtAp.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO, txtAm.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE, txtNombre.getText().toString());
            values.put(DataDB.PR_SO_DTE_NACIMIENTO, txtLugarNacimiento.getText().toString());
            values.put(DataDB.PR_SO_LUGAR, txtFechaNacimiento.getText().toString());
            values.put(DataDB.PR_SO_EDAD, txtEdad.getText().toString());
            values.put(DataDB.PR_SO_SEXO, sexo); //rg
            values.put(DataDB.PR_SO_RFC , txtRfc.getText().toString());
            values.put(DataDB.PR_SO_CURP, txtCurp.getText().toString());
            values.put(DataDB.PR_SO_INE, txtFolioIfe.getText().toString());
            values.put(DataDB.PR_SO_EDO_CIVIL, txtEstadoCivil.getText().toString());
            values.put(DataDB.PR_SO_CONYUGE_TRABAJA, txtAp.getText().toString());//rg y n
            values.put(DataDB.PR_SO_INGRESO_CONYUGE, txtInConyuge.getText().toString());
            values.put(DataDB.PR_SO_DEPENDIENTES, txtAp.getText().toString());//rg y n
            values.put(DataDB.PR_SO_NUMDEPENDIENTES, txtPersonas.getText().toString());
            values.put(DataDB.PR_SO_CALLE, txtCalle.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT, txtNExt.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT, txtNInt.getText().toString());
            values.put(DataDB.PR_SO_COLONIA, txtColonia.getText().toString());
            values.put(DataDB.PR_SO_CP, txtCP.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO, txtMun.getText().toString());
            values.put(DataDB.PR_SO_ESTADO, txtEstado.getText().toString());
            values.put(DataDB.PR_SO_TIPO_RECIDENCIA, estadoPropiedad);//rg
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_A, tiempoResidencia);//rg revisar esto
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_M, tiempoResidencia);//rg revisar esto
            values.put(DataDB.PR_SO_CREDITO_VI, txtAp.getText().toString());//rg
            values.put(DataDB.PR_SO_PAGO_VIVIENDA, txtMontoCred.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA, txtTelCasa.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL, txtCelular.getText().toString());
            values.put(DataDB.PR_SO_CORREO, txtCorreo.getText().toString());
            values.put(DataDB.PR_SO_CARGO_P_PUBLICO, txtAp.getText().toString());//rg Y N
            values.put(DataDB.PR_SO_CARGO_PUBLICO, txtAp.getText().toString());//rg Y N
            values.put(DataDB.PR_SO_CONYUGE_P_PUBLICO, txtAp.getText().toString());//rg Y N
            values.put(DataDB.PR_SO_CONYUGE_PUBLICO, txtAp.getText().toString());//rg Y N

            db.insert(DataDB.TABLE_NAME_INFO_SOLICITANTE, null, values);
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

            c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_INFO_SOLICITANTE, null);

            if (c.moveToFirst()) {
                do {

                    for(int i = 1; i<23; i++) {
                        System.out.println("Dato: " + c.getString(i));
                    }

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


