package mipymex.mcs.com.pruebas;

public class DataDB {

    public static int VERSION = 1;

    // Datos de la base de datos
    public static final String DB_NAME = "database";
    public static final String TABLE_NAME_USUARIOS = "usuarios";
    public static final String TABLE_NAME_SOLICITUD = "solicitud";
    public static final String TABLE_NAME_INFORMACION_SOLICITANTE = "informacionSolicitante";
    public static final String TABLE_NAME_INFROMACION_LABORAL = "informacionLaboral";
    public static final String TABLE_NAME_INFORMACION_CONYUGE = "informacionLaboral";

    // Datos de la tabla Usuarios
    public static final String NAME = "nombre";
    static final String PASSWORD = "contrasena";
    public static final String NUM_AGENCIA = "num_agencia";

    // Datos de la tabla de configuración de imagenes
    static String CAT_CM_VALOR_MIN = "cat_cm_valor_min";
    static String CAT_CM_VALOR_MAX = "cat_cm_valor_max";
    static String CAT_CM_VALOR_WIFI = "cat_cm_valor_wifi";
    public static String CAT_CM_VALOR_VERSION = "cat_cm_valor_version";

    // Datos de la tabla de tipos de fotos
    public static String CAT_CM_ID = "cat_cm_id";
    public static String CAT_CM_DESCRIPCION = "cat_cm_descripcion";

    // Datos de la tabla de Imagenes
    public static final String IMAGEN = "imagen";

    public static final String TABLE_NAME_CONFIG_IMAGENES = "config_imagenes";
    public static final String TABLE_NAME_TIPO_FOTO = "tipo_foto";

     public static final String PR_SO_NUMSOLICITUD = "pr_so_numsolicitud";
     public static final String PR_SO_MTO_PRESTAMO = "pr_so_mto_prestamo";
     public static final String PR_SO_PLAZO = "pr_so_plazo";
     public static final String PR_SO_ASESOR = "pr_so_asesor";
     public static final String PR_SO_DTE_SOLICITUD ="pr_so_dte_solicitud";
     public static final String PR_SO_DESTINO = "pr_so_destino";

     public static final String PR_SO_APATERNO = "pr_so_apaterno";
     public static final String PR_SO_AMATERNO = "pr_so_amaterno";
     public static final String PR_SO_NOMBRE = "pr_so_nombre";
     public static final String PR_SO_DTE_NACIMIENTO = "pr_so_nacimiento";
     public static final String PR_SO_LUGAR = "pr_so_lugar";
     public static final String PR_SO_EDAD = "pr_so_edad";
     public static final String PR_SO_SEXO = "pr_so_sexo";
     public static final String PR_SO_RFC = "pr_so_rfc";
     public static final String PR_SO_CURP = "pr_so_curp";
     public static final String PR_SO_INE = "pr_so_ine";
     public static final String PR_SO_EDO_CIVIL = "pr_so_edo_civil";
     public static final String PR_SO_CONYUGE_TRABAJA = "pr_so_conyuge_trabaja";
     public static final String PR_SO_INGRESO_CONYUGE = "pr_so_ingreso_conyuge";
     public static final String PR_SO_DEPENDIENTES = "pr_so_dependientes";
     public static final String PR_SO_NUMDEPENDIENTES = "pr_so_numdependientes";
     public static final String PR_SO_CALLE = "pr_so_calle";
     public static final String PR_SO_NUM_EXT = "pr_so_num_ext";
     public static final String PR_SO_NUM_INT = "pr_so_num_int";
     public static final String PR_SO_COLONIA = "pr_so_colonia";
     public static final String PR_SO_CP = "pr_so_cp";
     public static final String PR_SO_MUNICIPIO = "pr_so_municipio";
     public static final String PR_SO_ESTADO = "pr_so_estado";
     public static final String PR_SO_TIPO_RECIDENCIA = "pr_so_tipo_residencia";
     public static final String PR_SO_TIEMPO_RESIDENCIA_A = "pr_so_tiempo_residencia_a";
     public static final String PR_SO_TIEMPO_RESIDENCIA_M = "pr_so_tiempo_residencia_m";
     public static final String PR_SO_CREDITO_VI = "pr_so_credito_vi";
     public static final String PR_SO_PAGO_VIVIENDA = "pr_so_pago_vivienda";
     public static final String PR_SO_TEL_CASA = "pr_so_tel_casa";
     public static final String PR_SO_TEL_CEL = "pr_so_tel_cel";
     public static final String PR_SO_CORREO = "pr_so_correo";
     public static final String PR_SO_CARGO_P_PUBLICO = "pr_so_cargo_p_publico";
     public static final String PR_SO_CARGO_PUBLICO = "pr_so_publico";
     public static final String PR_SO_CONYUGE_P_PUBLICO = "pr_so_conyuge_p_publico";
     public static final String PR_SO_CONYUGE_PUBLICO = "pr_so_conyuge_publico";

     public static final String PR_SO_NOM_EMPRESA = "pr_so_nom_empresa";
     public static final String PR_SO_IMSS = "pr_so_imss";
     public static final String PR_SO_CALLE_EMP = "pr_so_calle_emp";
     public static final String PR_SO_NUM_EXT_EMP = "pr_so_num_ext_emp";
     public static final String PR_SO_NUM_INT_EMP = "pr_so_num_int_emp";
     public static final String PR_SO_COLONIA_EMP = "pr_so_colonia_emp";
     public static final String PR_SO_CP_EMP = "pr_so_cp_emp";
     public static final String PR_SO_MUNICIPIO_EMP = "pr_so_municipio_emp";
     public static final String PR_SO_ESTADO_EMP = "pr_so_estado_emp";
     public static final String PR_SO_DEPARTAMENTO_EMP = "pr_so_departamento_emp";
     public static final String PR_SO_PUESTO_EMP = "pr_so_puesto_emp";
     public static final String PR_SO_INGRESO_EMP = "pr_so_ingreso_emp";
     public static final String PR_SO_OTROS_ING_EMP = "pr_so_ing_emp";
     public static final String PR_SO_OTRO_ING_C_EMP = "pr_so_c_emp";
     public static final String PR_SO_ING_COM = "pr_so_ing_com";
     public static final String PR_SO_PAGA_CRED_INS = "pr_so_cred_ins";
     public static final String PR_SO_PAGA_IMPORTE_INS = "pr_so_paga_importe_ins";
     public static final String PR_SO_PAGA_INS = "pr_so_paga_ins";
     public static final String PR_SO_NOMBRE_JEFE = "pr_so_nombre_jefe";
     public static final String PR_SO_ANTIGUEDAD_EMP = "pr_so_antiguedad_emp";
     public static final String PR_SO_TEL_EMP = "pr_so_tel_emp";
     public static final String PR_SO_EXTENSION_EMP = "pr_so_extension_emp";
     public static final String PR_SO_FAX_EMP = "pr_so_fax_emp";
     public static final String PR_SO_PERIODICIDAD_COBRO = "pr_so_periodicidad";

     public static final String PR_SO_NOMBRE1_CONY = "pr_so_nombre1_cony";
     public static final String PR_SO_EDAD1_CONY = "pr_so_edad1_cony";
     public static final String PR_SO_PARENTESCO1_CONY = "pr_so_parentesco1_cony";
     public static final String PR_SO_TEL1_CONY = "pr_so_tel1_cony";
     public static final String PR_SO_CEL1_CONY = "pr_so_cel1_cony";
     public static final String PR_SO_NOMBRE2_CONY = "pr_so_nombre2_cony";
     public static final String PR_SO_EDAD2_CONY = "pr_so_edad2_cony";
     public static final String PR_SO_PARENTESCO2_CONY = "pr_so_parentesco2_cony";
     public static final String PR_SO_TEL2_CONY = "pr_so_tek2_cony";
     public static final String PR_SO_CEL2_CONY = "pr_so_cel2_cony";
     public static final String PR_SO_NOMBRE3_CONY = "pr_so_nombre3_cony";
     public static final String PR_SO_EDAD3_CONY = "pr_so_edad3_cony";
     public static final String PR_SO_PARENTESCO3_CONY = "pr_so_parentesco3_cony";
     public static final String PR_SO_TEL3_CONY = "pr_so_tel3_cony";
     public static final String PR_SO_CEL3_CONY = "pr_so_cel3_cony";

     public static final String PR_SO_APATERNO_REF = "pr_so_apaterno_ref";
     public static final String PR_SO_AMATERNO_REF = "pr_so_amaterno_ref";
     public static final String PR_SO_NOMBRE_REF = "pr_so_nombre_ref";
     public static final String PR_SO_CALLE_REF = "pr_so_calle_ref";
     public static final String PR_SO_NUM_EXT_REF = "pr_so_num_ext_ref";
     public static final String PR_SO_NUM_INT_REF = "pr_so_num_int_ref";
     public static final String PR_SO_COLONIA_REF = "pr_so_colonia_ref";
     public static final String PR_SO_CP_REF = "pr_so_cp_ref";
     public static final String PR_SO_MUNICIPIO_REF = "pr_so_municipio_ref";
     public static final String PR_SO_ESTADO_REF = "pr_so_estado_ref";
     public static final String PR_SO_TEL_CASA_REF = "pr_so_tel_casa_ref";
     public static final String PR_SO_TEL_CEL_REF = "pr_so_tel_cel_ref";
     public static final String PR_SO_CORREO_REF = "pr_so_correo_ref";

     public static final String PR_SO_APATERNO_REF_P = "pr_so_apaterno_ref_p";
     public static final String PR_SO_AMATERNO_REF_P = "pr_so_amaterno_ref_p";
     public static final String PR_SO_NOMBRE_REF_P = "pr_so_nombre_ref_p";
     public static final String PR_SO_CALLE_REF_P = "pr_so_calle_ref_p";
     public static final String PR_SO_NUM_EXT_REF_P = "pr_so_num_ext_ref_p";
     public static final String PR_SO_NUM_INT_REF_P = "pr_so_num_int_ref_p";
     public static final String PR_SO_COLONIA_REF_P = "pr_so_colonia_ref_p";
     public static final String PR_SO_CP_REF_P = "pr_so_cp_ref_p";
     public static final String PR_SO_MUNICIPIO_REF_P = "pr_so_municipio_ref_p";
     public static final String PR_SO_ESTADO_REF_P = "pr_so_estado_ref_p";
     public static final String PR_SO_TEL_CASA_REF_P = "pr_so_tel_cada_ref_p";
     public static final String PR_SO_TEL_CEL_REF_P = "pr_so_tel_cel_ref_p";
     public static final String PR_SO_CORREO_REF_P = "pr_so_correo_ref_p";

     public static final String PR_SO_FOLIO = "pr_so_folio";

}