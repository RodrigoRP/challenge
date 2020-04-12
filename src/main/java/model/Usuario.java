package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Usuario {

    private Integer andar;
    private Character elevador;
    private Character turno;

    public Usuario() {
    }

    public Usuario(Integer andar, Character elevador, Character turno) {
        this.andar = andar;
        this.elevador = elevador;
        this.turno = turno;
    }

}
