package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
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

               // System.out.println("=====================================================" + ifoInformacionSolicitante.txtAp.getText().toString());

                mostraDatos();
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

                obtenerDatosSolicitar();
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

    public String construirUrl() {
            try {
                strSend1 = "&pr_so_apaterno=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(7)), "UTF-8") +
                        "&pr_so_amaterno=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(8)), "UTF-8") +
                        "&pr_so_nombre=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(9)), "UTF-8") +
                        "&pr_so_dte_nacimiento=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(11)), "UTF-8") +
                        "&pr_so_lugar=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(10)), "UTF-8") +
                        "&pr_so_edad=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(12)), "UTF-8") +
                        "&pr_so_sexo=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(13)), "UTF-8") +
                        "&pr_so_rfc=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(14)), "UTF-8") +
                        "&pr_so_curp=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(15)), "UTF-8") +
                        "&pr_so_ine=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(16)), "UTF-8") +
                        "&pr_so_edo_civil=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(17)), "UTF-8") +
                        "&pr_so_conyuge_trabaja=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(18)), "UTF-8") +
                        "&pr_so_ingreso_conyuge=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(19)), "UTF-8") +
                        "&pr_so_dependientes=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(20)), "UTF-8") +
                        "&pr_so_numdependientes=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(21)), "UTF-8") +
                        "&pr_so_calle=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(22)), "UTF-8") +
                        "&pr_so_num_ext=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(23)), "UTF-8") +
                        "&pr_so_num_int=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(24)), "UTF-8") +
                        "&pr_so_colonia=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(25)), "UTF-8") +
                        "&pr_so_cp=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(26)), "UTF-8") +
                        "&pr_so_municipio=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(27)), "UTF-8") +
                        "&pr_so_estado=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(28)), "UTF-8") +
                        "&pr_so_tipo_recidencia=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(29)), "UTF-8") +
                        "&pr_so_tiempo_recidencia_a=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(30)), "UTF-8") +
                        "&pr_so_tiempo_recidencia_m=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(31)), "UTF-8") +
                        "&pr_so_credito_vi=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(32)), "UTF-8") +
                        "&pr_so_pago_vivienda=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(33)), "UTF-8") +
                        "&pr_so_tel_casa=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(34)), "UTF-8") +
                        "&pr_so_tel_cel=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(35)), "UTF-8") +
                        "&pr_so_correo=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(36)), "UTF-8") +
                        "&pr_so_cargo_p_publico=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(37)), "UTF-8") +
                        "&pr_so_cargo_publico=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(38)), "UTF-8") +
                        "&pr_so_conyuge_p_publico=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(39)), "UTF-8") +
                        "&pr_so_conyuge_publico=" + URLEncoder.encode(String.valueOf(datosSolicitanteLista.get(40)), "UTF-8");
                 } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

        System.out.println("cadena solicitante " + strSend1);

                try {
                    strSend2 = "&pr_so_nom_empresa=" + URLEncoder.encode(String.valueOf(datosLaborales.get(1)), "UTF-8") +
                        "&pr_so_imss=" + URLEncoder.encode(String.valueOf(datosLaborales.get(2)), "UTF-8") +
                        "&pr_so_calle_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(3)), "UTF-8") +
                        "&pr_so_num_ext_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(4)), "UTF-8") +
                        "&pr_so_num_int_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(5)), "UTF-8") +
                        "&pr_so_colonia_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(6)), "UTF-8") +
                        "&pr_so_municipio_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(7)), "UTF-8") +
                        "&pr_so_estado_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(8)), "UTF-8") +
                        "&pr_so_departamento_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(9)), "UTF-8") +
                        "&pr_so_puesto_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(10)), "UTF-8") +
                        "&pr_so_ingreso_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(11)), "UTF-8") +
                        "&pr_so_otros_ing_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(12)), "UTF-8") +
                        "&pr_so_otro_ing_c_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(13)), "UTF-8") +
                        "&pr_so_ing_com=" + URLEncoder.encode(String.valueOf(datosLaborales.get(14)), "UTF-8") +
                        "&pr_so_paga_cred_ins=" + URLEncoder.encode(String.valueOf(datosLaborales.get(15)), "UTF-8") +
                        "&pr_so_paga_importe_ins=" + URLEncoder.encode(String.valueOf(datosLaborales.get(16)), "UTF-8") +
                        "&pr_so_paga_ins=" + URLEncoder.encode(String.valueOf(datosLaborales.get(17)), "UTF-8") +
                        "&pr_so_nombre_jefe=" + URLEncoder.encode(String.valueOf(datosLaborales.get(18)), "UTF-8") +
                        "&pr_so_antiguedad_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(19)), "UTF-8") +
                        "&pr_so_tel_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(20)), "UTF-8") +
                        "&pr_so_extension_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(21)), "UTF-8") +
                        "&pr_so_fax_emp=" + URLEncoder.encode(String.valueOf(datosLaborales.get(22)), "UTF-8") +
                        "&pr_so_periodicidad_cobro=" + URLEncoder.encode(String.valueOf(datosLaborales.get(23)), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


        System.out.println("cadena laboral " + strSend2);
                    try {
                        strSend3 = "&pr_so_nombre1_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(1)), "UTF-8") +
                                "&pr_so_edad1_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(2)), "UTF-8") +
                                "&pr_so_parentesco1_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(3)), "UTF-8") +
                                "&pr_so_tel1_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(4)), "UTF-8") +
                                "&pr_so_cel1_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(5)), "UTF-8") +
                                "&pr_so_nombre2_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(6)), "UTF-8") +
                                "&pr_so_edad2_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(7)), "UTF-8") +
                                "&pr_so_parentesco2_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(8)), "UTF-8") +
                                "&pr_so_tel2_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(9)), "UTF-8") +
                                "&pr_so_cel2_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(10)), "UTF-8") +
                                "&pr_so_nombre3_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(11)), "UTF-8") +
                                "&pr_so_edad3_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(12)), "UTF-8") +
                                "&pr_so_parentesco3_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(13)), "UTF-8") +
                                "&pr_so_tel3_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(14)), "UTF-8") +
                                "&pr_so_cel3_cony=" + URLEncoder.encode(String.valueOf(datosConyuge.get(15)), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

        System.out.println("cadena conyuge "+ strSend3);

                        try {
                            strSend4 =  "&pr_so_apaterno_ref=" + URLEncoder.encode(String.valueOf(referencias.get(1)), "UTF-8") +
                                    "&pr_so_amaterno_ref=" + URLEncoder.encode(String.valueOf(referencias.get(2)), "UTF-8") +
                                    "&pr_so_nombre_ref=" + URLEncoder.encode(String.valueOf(referencias.get(3)), "UTF-8") +
                                    "&pr_so_calle_ref=" + URLEncoder.encode(String.valueOf(referencias.get(4)), "UTF-8") +
                                    "&pr_so_num_ext_ref=" + URLEncoder.encode(String.valueOf(referencias.get(5)), "UTF-8") +
                                    "&pr_so_num_int_ref=" + URLEncoder.encode(String.valueOf(referencias.get(6)), "UTF-8") +
                                    "&pr_so_colonia_ref=" + URLEncoder.encode(String.valueOf(referencias.get(7)), "UTF-8") +
                                    "&pr_so_cp_ref=" + URLEncoder.encode(String.valueOf(referencias.get(8)), "UTF-8") +
                                    "&pr_so_municipio_ref=" + URLEncoder.encode(String.valueOf(referencias.get(9)), "UTF-8") +
                                    "&pr_so_estado_ref=" + URLEncoder.encode(String.valueOf(referencias.get(10)), "UTF-8") +
                                    "&pr_so_tel_casa_ref=" + URLEncoder.encode(String.valueOf(referencias.get(11)), "UTF-8") +
                                    "&pr_so_tel_cel_ref=" + URLEncoder.encode(String.valueOf(referencias.get(12)), "UTF-8") +
                                    "&pr_so_correo_ref=" + URLEncoder.encode(String.valueOf(referencias.get(13)), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


        System.out.println("Cadena referencias: "+ strSend4);
                        try {
                            strSend5 =  "&pr_so_apaterno_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(1)), "UTF-8") +
                                    "&pr_so_amaterno_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(2)), "UTF-8") +
                                    "&pr_so_nombre_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(3)), "UTF-8") +
                                    "&pr_so_calle_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(4)), "UTF-8") +
                                    "&pr_so_num_ext_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(5)), "UTF-8") +
                                    "&pr_so_num_int_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(6)), "UTF-8") +
                                    "&pr_so_colonia_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(7)), "UTF-8") +
                                    "&pr_so_cp_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(8)), "UTF-8") +
                                    "&pr_so_municipio_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(9)), "UTF-8") +
                                    "&pr_so_estado_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(10)), "UTF-8") +
                                    "&pr_so_tel_casa_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(11)), "UTF-8") +
                                    "&pr_so_tel_cel_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(12)), "UTF-8") +
                                    "&pr_so_correo_ref_p=" + URLEncoder.encode(String.valueOf(referencasP.get(13)), "UTF-8");

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
