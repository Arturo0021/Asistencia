package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Versiones implements Serializable {

    public Integer Id;
    public Integer version;
    public String nombreapk;
    public String url;
    public Integer IdApp;

    public Entities_Versiones() {    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNombreapk() {
        return nombreapk;
    }

    public void setNombreapk(String nombreapk) {
        this.nombreapk = nombreapk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdApp() {
        return IdApp;
    }

    public void setIdApp(Integer idApp) {
        IdApp = idApp;
    }
}
