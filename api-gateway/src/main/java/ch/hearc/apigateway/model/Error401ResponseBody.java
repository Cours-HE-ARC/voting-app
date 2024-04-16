package ch.hearc.apigateway.model;

public class Error401ResponseBody {

    private String message = "Erreur d'authentification";
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Error401ResponseBody(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
