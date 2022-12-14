package controller;

import com.google.gson.reflect.TypeToken;
import model.Contato;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class ContatoDAO {

    static Path path = Paths.get("src/arquivo/agenda.txt");

    public static void gravarContatos(List<Contato> contatos){
        Gson gson = new Gson();

        try{
            if(!Files.exists(path)){
                Files.createFile(path);
            }
            String contato = gson.toJson(contatos);

            Files.writeString(path, contato);
        }catch(IOException e){
            System.out.println("Não foi possivel cadastrar contatos");
            throw new RuntimeException(e);

        }

    }

    public static List<Contato> pegarLista(){
        Gson gson = new Gson();
        List<Contato> contatos = new ArrayList<>();
        String linha = "";
        try{

        if(!Files.exists(path)) {
            Files.createFile(path);
        }
            linha = Files.readString(path);

        }catch (IOException e){
            System.out.println("Lista não encontrado!" + e);
            return contatos;
        }

        Type listType = new TypeToken<ArrayList<Contato>>(){}.getType();
        contatos = gson.fromJson(linha, listType);

        return contatos;

    }


}
