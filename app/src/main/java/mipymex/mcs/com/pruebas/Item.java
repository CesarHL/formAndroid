package mipymex.mcs.com.pruebas;

public class Item {

    private int guardar;
    private int esperar;
    private String nombre;
    private String rpu;
    private String medidor;
    private String fecha;
    private String hora;

    /** Datos a sincronizar */
    private String login_usuario;
    private int num_agencia;
    private String cat_pa_parentesco;
    private String cat_co_resultado;
    private String cat_co_rdescripcion;
    private String cat_np_codigo;
    private String cat_np_descripcion;
    private String pr_cf_credito;
    // RPU
    // MEDIDOR
    private String pr_cd_latitud;
    private String pr_cd_longitud;
    // Fecha
    private String comentario;
    // Datos extendidos
    private String pr_da_nombre;
    private String pr_da_cuenta;
    private String pr_da_rpu;
    private String pr_da_domicilio;
    // Datos de las preguntas
    private String pr_da_pregunta1;
    private String pr_da_pregunta2;

    private String pr_da_nom_parentesco;

    private String tmp_id;

    private String v_mtopp;
    private String v_dtepp;

    // Datos del layout de Ruteo
    private String estado_municipio_colonia;
    private int tam_est_mun_col;

    // Contructor para inflar los contactos a mostrar
    public Item(int guardar, int esperar, String nombre, String rpu, String medidor) {
        this.guardar = guardar;
        this.esperar = esperar;
        this.nombre = nombre;
        this.rpu = rpu;
        this.medidor = medidor;
    }

    // Consructor para inflar los contactos agendados
    public Item(int esperar, String nombre, String rpu, String medidor, String fecha, String hora){
        this.esperar = esperar;
        this.nombre = nombre;
        this.rpu = rpu;
        this.medidor = medidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Constructor para inflar los contactos sincronizados
    public Item(String nombre, String login_usuario, int num_agencia, String cat_pa_parentesco, String cat_co_resultado, String cat_co_rdescripcion,
                String cat_np_codigo, String cat_np_descripcion, String pr_cf_credito, String pr_cd_latitud, String pr_cd_longitud, String rpu, String medidor,
                String comentario, String fecha, String pr_da_nombre, String pr_da_cuenta, String pr_da_rpu, String pr_da_domicilio, String pr_da_pregunta1,
                String pr_da_pregunta2, String pr_da_nom_parentesco, String tmp_id, String v_mtopp, String v_dtepp, int guardar){

        this.nombre = nombre;
        this.login_usuario = login_usuario;
        this.num_agencia = num_agencia;
        this.cat_pa_parentesco = cat_pa_parentesco;
        this.cat_co_resultado = cat_co_resultado;
        this.cat_co_rdescripcion = cat_co_rdescripcion;
        this.cat_np_codigo = cat_np_codigo;
        this.cat_np_descripcion = cat_np_descripcion;
        this.pr_cf_credito = pr_cf_credito;
        this.rpu = rpu;
        this.medidor = medidor;
        this.pr_cd_latitud = pr_cd_latitud;
        this.pr_cd_longitud = pr_cd_longitud;
        this.fecha = fecha;
        this.comentario = comentario;
        this.pr_da_nombre = pr_da_nombre;
        this.pr_da_cuenta = pr_da_cuenta;
        this.pr_da_rpu = pr_da_rpu;
        this.pr_da_domicilio = pr_da_domicilio;
        this.pr_da_pregunta1 = pr_da_pregunta1;
        this.pr_da_pregunta2 = pr_da_pregunta2;
        this.pr_da_nom_parentesco = pr_da_nom_parentesco;
        this.tmp_id = tmp_id;
        this.v_mtopp = v_mtopp;
        this.v_dtepp = v_dtepp;
        this.guardar = guardar;
    }

    // Constructor parai nflar los estados de ruteo
    public Item(String estado_municipio_colonia, int tam_est_mun_col){
        this.estado_municipio_colonia = estado_municipio_colonia;
        this.tam_est_mun_col = tam_est_mun_col;
    }

    public Item(String string, String string1, String i, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14, String string15, String string16, String string17, String string18, String string19, String string20, String string21, String string22, String string23, String string24, String string25, String string26, String string27, String string28, String string29, String string30, String string31, String string32, String string33, String string34, String string35, String string36, String string37, String string38, String string39, String string40, String string41, String string42, String string43, String string44, String string45, String string46, String string47, String string48, String string49, String string50, String string51, String string52, String string53, String string54, String string55, String string56, String string57, String string58) {
    }

    public int getGuardar(){
        return guardar;
    }

    int getEsperar() {
        return esperar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRpu() {
        return rpu;
    }

    public String getMedidor() {
        return medidor;
    }

    public String getFecha(){
        return fecha;
    }

    String getHora(){
        return hora;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public int getNum_agencia() {
        return num_agencia;
    }

    public String getCat_pa_parentesco() {
        return cat_pa_parentesco;
    }

    public String getCat_co_resultado() {
        return cat_co_resultado;
    }

    public String getCat_co_rdescripcion() {
        return cat_co_rdescripcion;
    }

    public String getCat_np_codigo() {
        return cat_np_codigo;
    }

    public String getCat_np_descripcion() {
        return cat_np_descripcion;
    }

    public String getPr_cf_credito() {
        return pr_cf_credito;
    }

    public String getPr_cd_latitud() {
        return pr_cd_latitud;
    }

    public String getPr_cd_longitud() {
        return pr_cd_longitud;
    }

    public String getComentario() {
        return comentario;
    }

    public String getPr_da_nombre() {
        return pr_da_nombre;
    }

    public String getPr_da_cuenta() {
        return pr_da_cuenta;
    }

    public String getPr_da_rpu() {
        return pr_da_rpu;
    }

    public String getPr_da_domicilio() {
        return pr_da_domicilio;
    }

    public String getPr_da_pregunta1() {
        return pr_da_pregunta1;
    }

    public String getPr_da_pregunta2() {
        return pr_da_pregunta2;
    }

    public String getPr_da_nom_parentesco() {
        return pr_da_nom_parentesco;
    }

    public String getTmp_id() {
        return tmp_id;
    }

    public String getEstado_municipio_colonia() {
        return estado_municipio_colonia;
    }

    public int getTam_est_mun_col() {
        return tam_est_mun_col;
    }

    public String getV_mtopp() {
        return v_mtopp;
    }

    public String getV_dtepp() {
        return v_dtepp;
    }
}
