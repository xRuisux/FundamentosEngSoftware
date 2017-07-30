package usuario.dominio;

public class Pessoa {
    private int id;
    private Usuario usuario;
    private String nome;
    private String enderecoCasa;
    private String modeloDrone;


    public Pessoa(){
        this.nome = null;
        this.usuario = null;
        this.enderecoCasa = null;
        this.modeloDrone = null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnderecoCasa() {
        return enderecoCasa;
    }

    public void setEnderecoCasa(String enderecoCasa) {
        this.enderecoCasa = enderecoCasa;
    }

    public String getModeloDrone() {
        return modeloDrone;
    }

    public void setModeloDrone(String modeloDrone){
        this.modeloDrone = modeloDrone;
    }
}
