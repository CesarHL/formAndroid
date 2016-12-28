package mipymex.mcs.com.pruebas;

public class DataDB {

    // Datos de la base de datos
    public static final String DB_NAME = "database";                    // Nombre de base de datos
    public static final String TABLE_NAME_USERS = "usuarios";           // Tabla para sesión

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

}