package PracticoDao.patrondao.dto;


public class DepartamentoDto {
    private String id;
    private String nombre;
    private String idpadre;
    private String color;

    public DepartamentoDto(String id, String nombre, String idpadre, String color) {
        this.id = id;
        this.nombre = nombre;
        this.idpadre = idpadre;
        this.color = color;
    }


    public DepartamentoDto(String nombre, String idpadre, String color) {
        this("1", nombre, idpadre, color);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(String idpadre) {
        this.idpadre = idpadre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "DepartamentoDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idpadre='" + idpadre + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
