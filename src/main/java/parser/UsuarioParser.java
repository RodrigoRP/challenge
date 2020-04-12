package parser;

import model.Usuario;

public class UsuarioParser {

    public static Usuario parse(Usuario obj) {

        Usuario usuario = new Usuario();

        usuario.setAndar(obj.getAndar());
        usuario.setElevador(obj.getElevador());
        usuario.setTurno(obj.getTurno());

        return usuario;
    }

    private UsuarioParser() {
    }

}



