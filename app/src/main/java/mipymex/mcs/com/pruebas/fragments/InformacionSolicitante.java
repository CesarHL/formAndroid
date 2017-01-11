package mipymex.mcs.com.pruebas.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mipymex.mcs.com.pruebas.DataDB;
import mipymex.mcs.com.pruebas.R;

public class InformacionSolicitante extends Fragment {

    @BindView(R.id.txtApellidoPaterno)TextInputEditText txtAp;
    @BindView(R.id.txtApellidoMaterno)TextInputEditText txtAm;
    @BindView(R.id.txtNombresSolicitante)TextInputEditText txtNombre;
    @BindView(R.id.txtLugarNacimiento)TextInputEditText txtLugarNacimiento;
    @BindView(R.id.txtFechaNacimiento)TextInputEditText txtFechaNacimiento;
    @BindView(R.id.txtEdadSolicitante)TextInputEditText txtEdad;
    @BindView(R.id.txtRfcSolicitante)TextInputEditText txtRfc;
    @BindView(R.id.txtCurp)TextInputEditText txtCurp;
    @BindView(R.id.txtFolioCurp)TextInputEditText txtFolioIfe;
    @BindView(R.id.txtIngresoConyuge)TextInputEditText txtInConyuge;
    @BindView(R.id.txtRegimen)TextInputEditText txtRegimen;
    @BindView(R.id.txtEstadoCivil)TextInputEditText txtEstadoCivil;
    @BindView(R.id.txtNumeroPersonas)TextInputEditText txtPersonas;
    @BindView(R.id.txtCalle)TextInputEditText txtCalle;
    @BindView(R.id.txtExterior)TextInputEditText txtNExt;
    @BindView(R.id.txtInterior)TextInputEditText txtNInt;
    @BindView(R.id.txtColoniaSolicitante)TextInputEditText txtColonia;
    @BindView(R.id.txtCpSolicitante)TextInputEditText txtCP;
    @BindView(R.id.txtMunicipioSolicitante)TextInputEditText txtMun;
    @BindView(R.id.txtEstadoSolicitante)TextInputEditText txtEstado;
    @BindView(R.id.txtMontoMesual)TextInputEditText txtMontoCred;
    @BindView(R.id.txtTelefono)TextInputEditText txtTelCasa;
    @BindView(R.id.txtCelular)TextInputEditText txtCelular;
    @BindView(R.id.txtCorreo)TextInputEditText txtCorreo;
    @BindView(R.id.txtEspecificarSolicitante)TextInputEditText txtCargoPublicSolicitante;
    @BindView(R.id.txtEspecificarCargoPublicoConyuge)TextInputEditText txtEspCargoPublico;

    @BindView(R.id.rgSexo)RadioGroup rdSexo;
    @BindView(R.id.rgTrabajaConyuge)RadioGroup rdTConyuge;
    @BindView(R.id.rgDependientes)RadioGroup rdDep;
    @BindView(R.id.rgTiempoResidencia)RadioGroup rdTRes;
    @BindView(R.id.rgResidencia)RadioGroup rdRes;
    @BindView(R.id.grpBtnCargoPublico)RadioGroup rdCPublico;
    @BindView(R.id.grpBtnCargoPublicoConyuge)RadioGroup rdCConyuge;

    @BindView(R.id.infonavit)CheckBox checkVivienda;
    @BindView(R.id.btnSiguiente)Button agregar;
    private SQLiteDatabase db = null;
    private String sexo , estadoPropiedad, tiempoResidencia;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informacion_solicitante, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setTextInputEditTexts();
        setRadioGroups();
        setButton();
        setCheckBox();


    }

    private void setCheckBox() {
        checkVivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    txtMontoCred.setVisibility(View.VISIBLE);
                } else {
                    txtMontoCred.setVisibility(View.GONE);
                }

            }
        });
    }

    private void setButton() {
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //validar();
                //guardarInformacionSolicitante();
                TabLayout tabs = (TabLayout) getActivity().findViewById(R.id.tabs);
                TabLayout.Tab tab = tabs.getTabAt(1);
                if (tab != null) {
                    tab.select();
                }
                //mostraDatos();
            }
        });
    }

    private void setTextInputEditTexts() {
        txtAp.requestFocus();
        txtInConyuge.setVisibility(View.GONE);
        txtPersonas.setVisibility(View.GONE);
        txtMontoCred.setVisibility(View.GONE);
        txtCargoPublicSolicitante.setVisibility(View.GONE);
        txtEspCargoPublico.setVisibility(View.GONE);
    }
    private void setRadioGroups() {
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


        rdTConyuge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativo){
                    txtInConyuge.setVisibility(View.VISIBLE);
                    txtInConyuge.requestFocus();
                }else if (checkedId == R.id.negativo){
                    txtInConyuge.setVisibility(View.GONE);
                }
            }
        });



        rdDep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.afirmativoDependientes){
                    txtPersonas.setVisibility(View.VISIBLE);
                    txtPersonas.requestFocus();
                }else if (checkedId == R.id.negativoDependientes){
                    txtPersonas.setVisibility(View.GONE);
                }
            }
        });

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
    }
    private void validarCampo(TextInputEditText textInputEditText) {
        boolean estaVacio = textInputEditText.getText().toString().trim().isEmpty();
        if (estaVacio) {
            textInputEditText.setError("Este campo no puede estar vacío");
            textInputEditText.setText("");
            textInputEditText.requestFocus();
        }
    }
    public void validar(){
        validarCampo(txtAp);
        validarCampo(txtAm);
        validarCampo(txtNombre);
        validarCampo(txtLugarNacimiento);
        validarCampo(txtFechaNacimiento);
        validarCampo(txtEdad);
        validarCampo(txtRfc);
        validarCampo(txtCurp);
        validarCampo(txtFolioIfe);
        validarCampo(txtInConyuge);
        validarCampo(txtRegimen);
        validarCampo(txtEstadoCivil);
        validarCampo(txtPersonas);
        validarCampo(txtCalle);
        validarCampo(txtNExt);
        validarCampo(txtNInt);
        validarCampo(txtColonia);
        validarCampo(txtCP);
        validarCampo(txtEstado);
        validarCampo(txtMontoCred);
        validarCampo(txtTelCasa);
        validarCampo(txtCelular);
        validarCampo(txtCorreo);
        validarCampo(txtEspCargoPublico);
    }

    public void guardarInformacionSolicitante() {
        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

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

        db = getActivity().getApplicationContext().openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
        try {
            String[] valores_recuperar = {
                    DataDB.PR_SO_APATERNO,
                    DataDB.PR_SO_AMATERNO,
                    DataDB.PR_SO_CALLE
            };

            Cursor c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_INFO_SOLICITANTE, null);

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


