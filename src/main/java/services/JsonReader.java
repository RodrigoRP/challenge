package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Usuario;
import parser.UsuarioParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static List<Usuario> carregarJson() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Usuario> usuarioList = new ArrayList<>();

        try (Reader reader = new FileReader("input.json")) {

            Usuario[] usuarios = gson.fromJson(reader, Usuario[].class);

            for (Usuario usuario : usuarios) {
                usuarioList.add(UsuarioParser.parse(usuario));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioList;
    }

    private JsonReader() {

    }
}
