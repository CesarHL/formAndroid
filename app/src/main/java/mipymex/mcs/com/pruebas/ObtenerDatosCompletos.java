package mipymex.mcs.com.pruebas;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import mipymex.mcs.com.pruebas.fragments.DatosConyugeHijos;
import mipymex.mcs.com.pruebas.fragments.InformacionLaboral;
import mipymex.mcs.com.pruebas.fragments.InformacionSolicitante;
import mipymex.mcs.com.pruebas.fragments.ReferenciasPersonales;
import mipymex.mcs.com.pruebas.fragments.ReferenciasPersonalesDistintoDomicilio;

public class ObtenerDatosCompletos extends AppCompatActivity  {

    private Connection connection;
    public static int tamDatos;
    private Button btnSincronizar;
    private List datosSolicitanteLista, datosLaborales, datosConyuge, referencias, referencasP;
    private String strSend1, strSend2, strSend3, strSend4, strSend5;
    private SQLiteDatabase db = null;
    private InformacionSolicitante ifoInformacionSolicitante;
    private InformacionLaboral informacionLaboral;
    private DatosConyugeHijos datosConyugeHijos;
    private ReferenciasPersonales referenciasPersonales;
    private ReferenciasPersonalesDistintoDomicilio referenciasPersonalesDistintoDomicilio;
    private Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sincronizar_datos);

        datosSolicitanteLista = new ArrayList();
        datosLaborales = new ArrayList();
        datosConyuge = new ArrayList();
        referencias = new ArrayList();
        referencasP = new ArrayList();

        ifoInformacionSolicitante = new InformacionSolicitante();
        informacionLaboral = new InformacionLaboral();
        datosConyugeHijos = new DatosConyugeHijos();
        referenciasPersonales = new ReferenciasPersonales();
        referenciasPersonalesDistintoDomicilio = new ReferenciasPersonalesDistintoDomicilio();

        btnSincronizar = (Button) findViewById(R.id.btnSincroinizar);
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               //obtenerDatosSolicitar();
               // guardarInformacionLaboral();
                //guardarDatosConyugeEHijos();
                //guardarReferenciasPersonales();
                //guardarReferenciasPersonalesDistintoDomiciolio();
                mostraDatos();
                construirUrl();
                //sincronizar_fotos();
            }
        });
    }


    public void obtenerDatosSolicitar() {

        db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {

            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_APATERNO, ifoInformacionSolicitante.txtAp.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO, ifoInformacionSolicitante.txtAm.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE, ifoInformacionSolicitante.txtNombre.getText().toString());
            values.put(DataDB.PR_SO_DTE_NACIMIENTO, ifoInformacionSolicitante.txtLugarNacimiento.getText().toString());
            values.put(DataDB.PR_SO_LUGAR, ifoInformacionSolicitante.txtFechaNacimiento.getText().toString());
            values.put(DataDB.PR_SO_EDAD, ifoInformacionSolicitante.txtEdad.getText().toString());
            values.put(DataDB.PR_SO_SEXO, ifoInformacionSolicitante.sexo); //rg
            values.put(DataDB.PR_SO_RFC , ifoInformacionSolicitante.txtRfc.getText().toString());
            values.put(DataDB.PR_SO_CURP, ifoInformacionSolicitante.txtCurp.getText().toString());
            values.put(DataDB.PR_SO_INE, ifoInformacionSolicitante.txtFolioIfe.getText().toString());
            values.put(DataDB.PR_SO_EDO_CIVIL, ifoInformacionSolicitante.txtEstadoCivil.getText().toString());
            values.put(DataDB.PR_SO_CONYUGE_TRABAJA, ifoInformacionSolicitante.tabajaConyuge);//rg y n
            values.put(DataDB.PR_SO_INGRESO_CONYUGE, ifoInformacionSolicitante.txtInConyuge.getText().toString());
            values.put(DataDB.PR_SO_DEPENDIENTES, ifoInformacionSolicitante.dependientes);//rg y n
            values.put(DataDB.PR_SO_NUMDEPENDIENTES, ifoInformacionSolicitante.txtPersonas.getText().toString());
            values.put(DataDB.PR_SO_CALLE, ifoInformacionSolicitante.txtCalle.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT, ifoInformacionSolicitante.txtNExt.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT, ifoInformacionSolicitante.txtNInt.getText().toString());
            values.put(DataDB.PR_SO_COLONIA, ifoInformacionSolicitante.txtColonia.getText().toString());
            values.put(DataDB.PR_SO_CP, ifoInformacionSolicitante.txtCP.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO, ifoInformacionSolicitante.txtMun.getText().toString());
            values.put(DataDB.PR_SO_ESTADO, ifoInformacionSolicitante.txtEstado.getText().toString());
            values.put(DataDB.PR_SO_TIPO_RECIDENCIA, ifoInformacionSolicitante.estadoPropiedad);//rg
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_A, ifoInformacionSolicitante.tiempoResidenciaAnios);//rg
            values.put(DataDB.PR_SO_TIEMPO_RESIDENCIA_M, ifoInformacionSolicitante.tiempoResidenciaMeses);//rg
            values.put(DataDB.PR_SO_CREDITO_VI, ifoInformacionSolicitante.creditoVivienda);//rg
            values.put(DataDB.PR_SO_PAGO_VIVIENDA, ifoInformacionSolicitante.txtMontoCred.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA, ifoInformacionSolicitante.txtTelCasa.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL, ifoInformacionSolicitante.txtCelular.getText().toString());
            values.put(DataDB.PR_SO_CORREO, ifoInformacionSolicitante.txtCorreo.getText().toString());
            values.put(DataDB.PR_SO_CARGO_P_PUBLICO, ifoInformacionSolicitante.cargoPublicoSolicitante);//rg Y N
            values.put(DataDB.PR_SO_CARGO_PUBLICO, ifoInformacionSolicitante.txtCargoPublicSolicitante.getText().toString());
            values.put(DataDB.PR_SO_CONYUGE_P_PUBLICO, ifoInformacionSolicitante.cargoPublicoConyuge);//rg Y N
            values.put(DataDB.PR_SO_CONYUGE_PUBLICO, ifoInformacionSolicitante.txtEspCargoPublicoConyuge.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_SOLICITANTE, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }


    public void guardarInformacionLaboral() {

        db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {

            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NOM_EMPRESA, informacionLaboral.txtNEmp.getText().toString());
            values.put(DataDB.PR_SO_IMSS, informacionLaboral.registroImss);//rg
            values.put(DataDB.PR_SO_CALLE_EMP,informacionLaboral.txtDCalle.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_EMP,informacionLaboral.txtNExtm.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_EMP,informacionLaboral.txtNInt.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_EMP,informacionLaboral.txtCol.getText().toString());
            values.put(DataDB.PR_SO_CP_EMP,informacionLaboral.txtCp.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_EMP,informacionLaboral.txtMun.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_EMP,informacionLaboral.txtEdo.getText().toString());
            values.put(DataDB.PR_SO_DEPARTAMENTO_EMP,informacionLaboral.txtDepLab.getText().toString());
            values.put(DataDB.PR_SO_PUESTO_EMP,informacionLaboral.txtPuesto.getText().toString());
            values.put(DataDB.PR_SO_INGRESO_EMP,informacionLaboral.txtIngreso.getText().toString());
            values.put(DataDB.PR_SO_OTROS_ING_EMP,informacionLaboral.otrosIngresos);//rg
            values.put(DataDB.PR_SO_OTRO_ING_C_EMP,informacionLaboral.txtOtros.getText().toString());
            values.put(DataDB.PR_SO_ING_COM, informacionLaboral.otrosComprobable);//rg
            values.put(DataDB.PR_SO_PAGA_CRED_INS, informacionLaboral.pagaCredito);//rg
            values.put(DataDB.PR_SO_PAGA_IMPORTE_INS,informacionLaboral.txtEMontoCred.getText().toString());//
            values.put(DataDB.PR_SO_PAGA_INS, informacionLaboral.txtInst.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_JEFE,informacionLaboral.txtNJefe.getText().toString());
            values.put(DataDB.PR_SO_ANTIGUEDAD_EMP,informacionLaboral.txtAntig.getText().toString());
            values.put(DataDB.PR_SO_TEL_EMP, informacionLaboral.txtTel.getText().toString());
            values.put(DataDB.PR_SO_EXTENSION_EMP, informacionLaboral.txtExt.getText().toString());
            values.put(DataDB.PR_SO_FAX_EMP, informacionLaboral.txtFax.getText().toString());
            values.put(DataDB.PR_SO_PERIODICIDAD_COBRO, informacionLaboral.periodicidad);//rg

            db.insert(DataDB.TABLE_NAME_INFO_LABORAL, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

    public void guardarDatosConyugeEHijos() {

        db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_NOMBRE1_CONY,datosConyugeHijos.txtNombreC.getText().toString());
            values.put(DataDB.PR_SO_EDAD1_CONY,datosConyugeHijos.txtEdadC.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO1_CONY,datosConyugeHijos.txtParentescoC.getText().toString());
            values.put(DataDB.PR_SO_TEL1_CONY,datosConyugeHijos.txtTelefonoC.getText().toString());
            values.put(DataDB.PR_SO_CEL1_CONY,datosConyugeHijos.txtCelularC.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE2_CONY, datosConyugeHijos.txtNombreC2.getText().toString());
            values.put(DataDB.PR_SO_EDAD2_CONY, datosConyugeHijos.txtEdadC2.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO2_CONY,datosConyugeHijos.txtParentescoC2.getText().toString());
            values.put(DataDB.PR_SO_TEL2_CONY,datosConyugeHijos.txtTelefonoC2.getText().toString());
            values.put(DataDB.PR_SO_CEL2_CONY,datosConyugeHijos.txtCelularC2.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE3_CONY,datosConyugeHijos.txtNombreC3.getText().toString());
            values.put(DataDB.PR_SO_EDAD3_CONY,datosConyugeHijos.txtEdadC3.getText().toString());
            values.put(DataDB.PR_SO_PARENTESCO3_CONY, datosConyugeHijos.txtParentescoC3.getText().toString());
            values.put(DataDB.PR_SO_TEL3_CONY, datosConyugeHijos.txtTelefonoC3.getText().toString());
            values.put(DataDB.PR_SO_CEL3_CONY,datosConyugeHijos.txtCelularC3.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_CONYUGE, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }



    }

    public void guardarReferenciasPersonales() {

        db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_APATERNO_REF,referenciasPersonales.txtApaternoRef.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO_REF,referenciasPersonales.txtAMaternoRef.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_REF,referenciasPersonales.txtNombresRef.getText().toString());
            values.put(DataDB.PR_SO_CALLE_REF,referenciasPersonales.txtCalleRef.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_REF,referenciasPersonales.txtExtRef.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_REF,referenciasPersonales.txtIntRef.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_REF,referenciasPersonales.txtColRef.getText().toString());
            values.put(DataDB.PR_SO_CP_REF,referenciasPersonales.txtCpRef.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_REF,referenciasPersonales.txtMunRef.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_REF,referenciasPersonales.txtEdoRef.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA_REF,referenciasPersonales.txtTelRef.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL_REF,referenciasPersonales.txtCelRef.getText().toString());
            values.put(DataDB.PR_SO_CORREO_REF,referenciasPersonales.txtCorreoRef.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_REF, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

    public void guardarReferenciasPersonalesDistintoDomiciolio() {

        db = openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);

        try {
            ContentValues values = new ContentValues();

            values.put(DataDB.PR_SO_APATERNO_REF_P, referenciasPersonalesDistintoDomicilio.txtApaternoRefP.getText().toString());
            values.put(DataDB.PR_SO_AMATERNO_REF_P, referenciasPersonalesDistintoDomicilio.txtAMaternoRefP.getText().toString());
            values.put(DataDB.PR_SO_NOMBRE_REF_P, referenciasPersonalesDistintoDomicilio.txtNombresRefP.getText().toString());
            values.put(DataDB.PR_SO_CALLE_REF_P, referenciasPersonalesDistintoDomicilio.txtCalleRefP.getText().toString());
            values.put(DataDB.PR_SO_NUM_EXT_REF_P, referenciasPersonalesDistintoDomicilio.txtExtRefP.getText().toString());
            values.put(DataDB.PR_SO_NUM_INT_REF_P, referenciasPersonalesDistintoDomicilio.txtIntRefP.getText().toString());
            values.put(DataDB.PR_SO_COLONIA_REF_P, referenciasPersonalesDistintoDomicilio.txtColRefP.getText().toString());
            values.put(DataDB.PR_SO_CP_REF_P, referenciasPersonalesDistintoDomicilio.txtCpRefP.getText().toString());
            values.put(DataDB.PR_SO_MUNICIPIO_REF_P, referenciasPersonalesDistintoDomicilio.txtMunRefP.getText().toString());
            values.put(DataDB.PR_SO_ESTADO_REF_P, referenciasPersonalesDistintoDomicilio.txtEdoRefP.getText().toString());
            values.put(DataDB.PR_SO_TEL_CASA_REF_P, referenciasPersonalesDistintoDomicilio.txtTelRefP.getText().toString());
            values.put(DataDB.PR_SO_TEL_CEL_REF_P, referenciasPersonalesDistintoDomicilio.txtCelRefP.getText().toString());
            values.put(DataDB.PR_SO_CORREO_REF_P, referenciasPersonalesDistintoDomicilio.txtCorreoRefP.getText().toString());

            db.insert(DataDB.TABLE_NAME_INFO_REF_P, null, values);
            System.out.println("Insertado");
        } catch (SQLException ex) {
            System.out.println("Error al insertar solicitud: " + ex);
        } finally {
            db.close();
        }
    }

    public void mostraDatos() {

        db = openOrCreateDatabase(DataDB.DB_NAME, MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_SOLICITANTE, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    for (int n = 0; n <= 40; n++) {
                        datosSolicitanteLista.add(c.getString(n));
                        System.out.println(datosSolicitanteLista.get(n));
                    }
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen datos solicitante");
            }

            c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_LABORAL, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    for (int n = 0; n < 24; n++) {
                        datosLaborales.add(c.getString(n));
                        System.out.println(datosLaborales.get(n));
                    }
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen datos laborales");
            }

            c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_CONYUGE, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    for (int n = 0; n < 16; n++) {
                        datosConyuge.add(c.getString(n));
                        System.out.println(datosConyuge.get(n));
                    }
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen datos conyuge");
            }

            c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_REF, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    for (int n = 0; n <= 13; n++) {
                        referencias.add(c.getString(n));
                        System.out.println(referencias.get(n));
                    }
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen datos referencias");
            }

            c = db.rawQuery("SELECT *  FROM " + DataDB.TABLE_NAME_INFO_REF_P, null);
            tamDatos = c.getCount();
            if (c.moveToFirst()) {
                do {
                    for (int n = 0; n <= 13; n++) {
                        referencasP.add(c.getString(n));
                        System.out.println(referencasP.get(n));
                    }
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen datos Referencias P");
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
    }

    public void sincronizar_datos(){

       // if(connection.getConnection("No informar")) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setCancelable(true);
            builder1.setTitle("ATENCIÓN");
            builder1.setMessage("¿Esta seguro de enviar los datos?");


            builder1.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {



                       // String cadenaCompleta = strSend1+ strSend2 + strSend3+  strSend4+ strSend5+ strSend6;
                       // new GetWebServices(Catalogo.this).execute(strSend, "enviar", null, null,Service_sincronizar.items.get(i).getPr_cf_credito());

                    //Toast.makeText(Catalogo.this,"Datos enviados",Toast.LENGTH_SHORT).show();
                }
            });
            builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            Button bp = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
            Button bn = alert11.getButton(DialogInterface.BUTTON_NEGATIVE);
            bp.setTextColor(Color.GRAY);
            bn.setTextColor(Color.GRAY);
        //}
    }

    public void obtenerDatosListas() {
        if (ifoInformacionSolicitante.datosSolicitanteLista != null) {
            System.out.println("===================================================== LISTA - SOLICITANTE: ");
            System.out.println(datosSolicitanteLista.toString());

            System.out.println("===================================================== LISTA - LABORAL: ");
            System.out.println(datosLaborales.toString());

            System.out.println("===================================================== LISTA - CONYUGE: ");
            System.out.println(datosConyuge.toString());

            System.out.println("===================================================== LISTA - REFERENCIAS: ");
            System.out.println(referencias.toString());

            System.out.println("===================================================== LISTA - REFERENCIAS - P: ");
            System.out.println(referencasP.toString());

        } else {
            System.out.println("==================================== .i. ");
        }
    }

    public String construirUrl() {
            try {
                strSend1 ="" +
                        "&pr_so_numsolicitud=" + URLEncoder.encode(datosSolicitanteLista.get(1).toString(), "UTF-8") +
                        "&pr_so_mto_prestamo=" + URLEncoder.encode(datosSolicitanteLista.get(2).toString(), "UTF-8") +
                        "&pr_so_plazo=" + URLEncoder.encode(datosSolicitanteLista.get(3).toString(), "UTF-8") +
                        "&pr_so_asesor=" + URLEncoder.encode(datosSolicitanteLista.get(4).toString(), "UTF-8") +
                        "&pr_so_dte_solicitud=" + URLEncoder.encode(datosSolicitanteLista.get(5).toString(), "UTF-8") +
                        "&pr_so_destino=" + URLEncoder.encode(datosSolicitanteLista.get(6).toString(), "UTF-8") +
                        "&pr_so_apaterno=" + URLEncoder.encode(datosSolicitanteLista.get(7).toString(), "UTF-8") +
                        "&pr_so_amaterno=" + URLEncoder.encode(datosSolicitanteLista.get(8).toString(), "UTF-8") +
                        "&pr_so_nombre=" + URLEncoder.encode(datosSolicitanteLista.get(9).toString(), "UTF-8") +
                        "&pr_so_dte_nacimiento=" + URLEncoder.encode(datosSolicitanteLista.get(11).toString(), "UTF-8") +
                        "&pr_so_lugar=" + URLEncoder.encode(datosSolicitanteLista.get(10).toString(), "UTF-8") +
                        "&pr_so_edad=" + URLEncoder.encode(datosSolicitanteLista.get(12).toString(), "UTF-8") +
                        "&pr_so_sexo=" + URLEncoder.encode(datosSolicitanteLista.get(13).toString(), "UTF-8") +
                        "&pr_so_rfc=" + URLEncoder.encode(datosSolicitanteLista.get(14).toString(), "UTF-8") +
                        "&pr_so_curp=" + URLEncoder.encode(datosSolicitanteLista.get(15).toString(), "UTF-8") +
                        "&pr_so_ine=" + URLEncoder.encode(datosSolicitanteLista.get(16).toString(), "UTF-8") +
                        "&pr_so_edo_civil=" + URLEncoder.encode(datosSolicitanteLista.get(17).toString(), "UTF-8") +
                        "&pr_so_conyuge_trabaja=" + URLEncoder.encode(datosSolicitanteLista.get(18).toString(), "UTF-8") +
                        "&pr_so_ingreso_conyuge=" + URLEncoder.encode(datosSolicitanteLista.get(19).toString(), "UTF-8") +
                        "&pr_so_dependientes=" + URLEncoder.encode(datosSolicitanteLista.get(20).toString(), "UTF-8") +
                        "&pr_so_numdependientes=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(21)), "UTF-8") +
                        "&pr_so_calle=" + URLEncoder.encode(datosSolicitanteLista.get(22).toString(), "UTF-8") +
                        "&pr_so_num_ext=" + URLEncoder.encode(datosSolicitanteLista.get(23).toString(), "UTF-8") +
                        "&pr_so_num_int=" + URLEncoder.encode(datosSolicitanteLista.get(24).toString(), "UTF-8") +
                        "&pr_so_colonia=" + URLEncoder.encode(datosSolicitanteLista.get(25).toString(), "UTF-8") +
                        "&pr_so_cp=" + URLEncoder.encode(datosSolicitanteLista.get(26).toString(), "UTF-8") +
                        "&pr_so_municipio=" + URLEncoder.encode(datosSolicitanteLista.get(27).toString(), "UTF-8") +
                        "&pr_so_estado=" + URLEncoder.encode(datosSolicitanteLista.get(28).toString(), "UTF-8") +
                        "&pr_so_tipo_recidencia=" + URLEncoder.encode(datosSolicitanteLista.get(29).toString(), "UTF-8") +
                        "&pr_so_tiempo_recidencia_a=" + URLEncoder.encode(datosSolicitanteLista.get(30).toString(), "UTF-8") +
                        "&pr_so_tiempo_recidencia_m=" + URLEncoder.encode(datosSolicitanteLista.get(31).toString(), "UTF-8") +
                        "&pr_so_credito_vi=" + URLEncoder.encode(datosSolicitanteLista.get(32).toString(), "UTF-8") +
                        "&pr_so_pago_vivienda=" + URLEncoder.encode(datosSolicitanteLista.get(33).toString(), "UTF-8") +
                        "&pr_so_tel_casa=" + URLEncoder.encode(datosSolicitanteLista.get(34).toString(), "UTF-8") +
                        "&pr_so_tel_cel=" + URLEncoder.encode(datosSolicitanteLista.get(35).toString(), "UTF-8") +
                        "&pr_so_correo=" + URLEncoder.encode(datosSolicitanteLista.get(36).toString(), "UTF-8") +
                        "&pr_so_cargo_p_publico=" + URLEncoder.encode(datosSolicitanteLista.get(37).toString(), "UTF-8") +
                        "&pr_so_cargo_publico=" + URLEncoder.encode(datosSolicitanteLista.get(38).toString(), "UTF-8") +
                        "&pr_so_conyuge_p_publico=" + URLEncoder.encode(datosSolicitanteLista.get(39).toString(), "UTF-8") +
                        "&pr_so_conyuge_publico=" + URLEncoder.encode(datosSolicitanteLista.get(40).toString(), "UTF-8");
                 } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

        System.out.println("cadena solicitante " + strSend1);

                try {
                    strSend2 = "&pr_so_nom_empresa=" + URLEncoder.encode(datosLaborales.get(1).toString(), "UTF-8") +
                        "&pr_so_imss=" + URLEncoder.encode(datosLaborales.get(2).toString(), "UTF-8") +
                        "&pr_so_calle_emp=" + URLEncoder.encode(datosLaborales.get(3).toString(), "UTF-8") +
                        "&pr_so_num_ext_emp=" + URLEncoder.encode(datosLaborales.get(4).toString(), "UTF-8") +
                        "&pr_so_num_int_emp=" + URLEncoder.encode(datosLaborales.get(5).toString(), "UTF-8") +
                        "&pr_so_colonia_emp=" + URLEncoder.encode(datosLaborales.get(6).toString(), "UTF-8") +
                        "&pr_so_municipio_emp=" + URLEncoder.encode(datosLaborales.get(7).toString(), "UTF-8") +
                        "&pr_so_estado_emp=" + URLEncoder.encode(datosLaborales.get(8).toString(), "UTF-8") +
                        "&pr_so_departamento_emp=" + URLEncoder.encode(datosLaborales.get(9).toString(), "UTF-8") +
                        "&pr_so_puesto_emp=" + URLEncoder.encode(datosLaborales.get(10).toString(), "UTF-8") +
                        "&pr_so_ingreso_emp=" + URLEncoder.encode(datosLaborales.get(11).toString(), "UTF-8") +
                        "&pr_so_otros_ing_emp=" + URLEncoder.encode(datosLaborales.get(12).toString(), "UTF-8") +
                        "&pr_so_otro_ing_c_emp=" + URLEncoder.encode(datosLaborales.get(13).toString(), "UTF-8") +
                        "&pr_so_ing_com=" + URLEncoder.encode(datosLaborales.get(14).toString(), "UTF-8") +
                        "&pr_so_paga_cred_ins=" + URLEncoder.encode(datosLaborales.get(15).toString(), "UTF-8") +
                        "&pr_so_paga_importe_ins=" + URLEncoder.encode(datosLaborales.get(16).toString(), "UTF-8") +
                        "&pr_so_paga_ins=" + URLEncoder.encode(datosLaborales.get(17).toString(), "UTF-8") +
                        "&pr_so_nombre_jefe=" + URLEncoder.encode(datosLaborales.get(18).toString(), "UTF-8") +
                        "&pr_so_antiguedad_emp=" + URLEncoder.encode(datosLaborales.get(19).toString(), "UTF-8") +
                        "&pr_so_tel_emp=" + URLEncoder.encode(datosLaborales.get(20).toString(), "UTF-8") +
                        "&pr_so_extension_emp=" + URLEncoder.encode(datosLaborales.get(21).toString(), "UTF-8") +
                        "&pr_so_fax_emp=" + URLEncoder.encode(datosLaborales.get(22).toString(), "UTF-8") +
                        "&pr_so_periodicidad_cobro=" + URLEncoder.encode(datosLaborales.get(23).toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


        System.out.println("cadena laboral " + strSend2);
                    try {
                        strSend3 = "&pr_so_nombre1_cony=" + URLEncoder.encode(datosConyuge.get(1).toString(), "UTF-8") +
                                "&pr_so_edad1_cony=" + URLEncoder.encode(datosConyuge.get(2).toString(), "UTF-8") +
                                "&pr_so_parentesco1_cony=" + URLEncoder.encode(datosConyuge.get(3).toString(), "UTF-8") +
                                "&pr_so_tel1_cony=" + URLEncoder.encode(datosConyuge.get(4).toString(), "UTF-8") +
                                "&pr_so_cel1_cony=" + URLEncoder.encode(datosConyuge.get(5).toString(), "UTF-8") +
                                "&pr_so_nombre2_cony=" + URLEncoder.encode(datosConyuge.get(6).toString(), "UTF-8") +
                                "&pr_so_edad2_cony=" + URLEncoder.encode(datosConyuge.get(7).toString(), "UTF-8") +
                                "&pr_so_parentesco2_cony=" + URLEncoder.encode(datosConyuge.get(8).toString(), "UTF-8") +
                                "&pr_so_tel2_cony=" + URLEncoder.encode(datosConyuge.get(9).toString(), "UTF-8") +
                                "&pr_so_cel2_cony=" + URLEncoder.encode(datosConyuge.get(10).toString(), "UTF-8") +
                                "&pr_so_nombre3_cony=" + URLEncoder.encode(datosConyuge.get(11).toString(), "UTF-8") +
                                "&pr_so_edad3_cony=" + URLEncoder.encode(datosConyuge.get(12).toString(), "UTF-8") +
                                "&pr_so_parentesco3_cony=" + URLEncoder.encode(datosConyuge.get(13).toString(), "UTF-8") +
                                "&pr_so_tel3_cony=" + URLEncoder.encode(datosConyuge.get(14).toString(), "UTF-8") +
                                "&pr_so_cel3_cony=" + URLEncoder.encode(datosConyuge.get(15).toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

        System.out.println("cadena conyuge "+ strSend3);

                        try {
                            strSend4 =  "&pr_so_apaterno_ref=" + URLEncoder.encode(referencias.get(1).toString(), "UTF-8") +
                                    "&pr_so_amaterno_ref=" + URLEncoder.encode(referencias.get(2).toString(), "UTF-8") +
                                    "&pr_so_nombre_ref=" + URLEncoder.encode(referencias.get(3).toString(), "UTF-8") +
                                    "&pr_so_calle_ref=" + URLEncoder.encode(referencias.get(4).toString(), "UTF-8") +
                                    "&pr_so_num_ext_ref=" + URLEncoder.encode(referencias.get(5).toString(), "UTF-8") +
                                    "&pr_so_num_int_ref=" + URLEncoder.encode(referencias.get(6).toString(), "UTF-8") +
                                    "&pr_so_colonia_ref=" + URLEncoder.encode(referencias.get(7).toString(), "UTF-8") +
                                    "&pr_so_cp_ref=" + URLEncoder.encode(referencias.get(8).toString(), "UTF-8") +
                                    "&pr_so_municipio_ref=" + URLEncoder.encode(referencias.get(9).toString(), "UTF-8") +
                                    "&pr_so_estado_ref=" + URLEncoder.encode(referencias.get(10).toString(), "UTF-8") +
                                    "&pr_so_tel_casa_ref=" + URLEncoder.encode(referencias.get(11).toString(), "UTF-8") +
                                    "&pr_so_tel_cel_ref=" + URLEncoder.encode(referencias.get(12).toString(), "UTF-8") +
                                    "&pr_so_correo_ref=" + URLEncoder.encode(referencias.get(13).toString(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


        System.out.println("Cadena referencias: "+ strSend4);
                        try {
                            strSend5 =  "&pr_so_apaterno_ref_p=" + URLEncoder.encode(referencasP.get(1).toString(), "UTF-8") +
                                    "&pr_so_amaterno_ref_p=" + URLEncoder.encode(referencasP.get(2).toString(), "UTF-8") +
                                    "&pr_so_nombre_ref_p=" + URLEncoder.encode(referencasP.get(3).toString(), "UTF-8") +
                                    "&pr_so_calle_ref_p=" + URLEncoder.encode(referencasP.get(4).toString(), "UTF-8") +
                                    "&pr_so_num_ext_ref_p=" + URLEncoder.encode(referencasP.get(5).toString(), "UTF-8") +
                                    "&pr_so_num_int_ref_p=" + URLEncoder.encode(referencasP.get(6).toString(), "UTF-8") +
                                    "&pr_so_colonia_ref_p=" + URLEncoder.encode(referencasP.get(7).toString(), "UTF-8") +
                                    "&pr_so_cp_ref_p=" + URLEncoder.encode(referencasP.get(8).toString(), "UTF-8") +
                                    "&pr_so_municipio_ref_p=" + URLEncoder.encode(referencasP.get(9).toString(), "UTF-8") +
                                    "&pr_so_estado_ref_p=" + URLEncoder.encode(referencasP.get(10).toString(), "UTF-8") +
                                    "&pr_so_tel_casa_ref_p=" + URLEncoder.encode(referencasP.get(11).toString(), "UTF-8") +
                                    "&pr_so_tel_cel_ref_p=" + URLEncoder.encode(referencasP.get(12).toString(), "UTF-8") +
                                    "&pr_so_correo_ref_p=" + URLEncoder.encode(referencasP.get(13).toString(), "UTF-8");

                            //"&pr_so_folio" + URLEncoder.encode(String.valueOf(referencasP.get(14)), "UTF-8"
                        } catch (UnsupportedEncodingException e) {
                             e.printStackTrace();
                        }

                        System.out.println("cadena referencias p: " + strSend5);

                        String cadenaCompleta = Login.IPpublic + "RecibeDatos?v_cliente=2" + strSend1+ strSend2 + strSend3+  strSend4+ strSend5;
                        System.out.println(cadenaCompleta);
             new GetWebServices(ObtenerDatosCompletos.this).execute(cadenaCompleta, "enviar", null, null, null);

        return Login.IPpublic;
    }

    public void sincronizar_fotos(){

        if(connection.getConnection("No informar")) {

            db = openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
            Cursor c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_IMAGEN, null);
            try {
                String strSendFotos;
                System.out.println("Imagenes encontradas: " + c.getCount());
                if (c.moveToFirst()) {
                    do{
                        strSendFotos = Login.IPpublic + "RecibeImagen" +
                                "?v_cliente=2" +
                                "&v_pr_cf_credito=" + c.getString(0) +
                                "&v_fecha_visita=" + URLEncoder.encode(c.getString(1), "UTF-8") +
                                "&v_tipo=" + c.getString(2) +
                                "&v_id="+ URLEncoder.encode(c.getString(4), "UTF-8") +
                                "&v_imagen=" + URLEncoder.encode(c.getString(3),"UTF-8");

                        new GetWebServicesFotos(ObtenerDatosCompletos.this).execute(strSendFotos, "enviar_fotos",c.getString(0),c.getString(2));
                    }while(c.moveToNext());
                } else {
                    System.out.println("No existen imagenes en BD local");
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }finally {
                db.close();
            }
        }
    }



}
