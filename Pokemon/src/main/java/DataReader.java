import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {
    private BufferedReader reader;
    private HashMap<Integer, Pokemon> pokemonMap = new HashMap();
    private HashMap<Integer, Attack> attackMap = new HashMap();
    private final String POKEMON_CSV_FILE_PATH = "C:\\Users\\Codersbay\\Desktop\\CodersBay_AbdurakhmanVaschaev\\Java\\JavaAusbildung\\Pokemon\\src\\data\\Pokemon.csv";
    private final String ATTACK_CSV_FILE_PATH = "C:\\Users\\Codersbay\\Desktop\\CodersBay_AbdurakhmanVaschaev\\Java\\JavaAusbildung\\Pokemon\\src\\data\\Attacks.csv";

    public HashMap<Integer, Pokemon> getPokemonMap() {
        return this.pokemonMap;
    }

    public void setPokemonMap(HashMap<Integer, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
    }

    public HashMap<Integer, Attack> getAttackMap() {
        return this.attackMap;
    }

    public void setAttackMap(HashMap<Integer, Attack> attackMap) {
        this.attackMap = attackMap;
    }

    public DataReader() {
        String currentLine;
        int id;
        String name;
        String effect;
        String type;
        int power;
        int accuracy;
        int pp;
        try {
            this.reader = new BufferedReader(new FileReader("C:\\Users\\Codersbay\\Desktop\\CodersBay_AbdurakhmanVaschaev\\Java\\JavaAusbildung\\Pokemon\\src\\data\\Pokemon.csv"));
            this.reader.readLine();

            while((currentLine = this.reader.readLine()) != null) {
                String[] components = currentLine.split(";");
                id = Integer.parseInt(components[0]);
                name = components[1];
                effect = components[2];
                type = components[3];
                int total = Integer.parseInt(components[4]);
                power = Integer.parseInt(components[5]);
                accuracy = Integer.parseInt(components[6]);
                pp = Integer.parseInt(components[7]);
                int spAtk = Integer.parseInt(components[8]);
                int spDef = Integer.parseInt(components[9]);
                int speed = Integer.parseInt(components[10]);
                Pokemon pokemon = new Pokemon(id, name, effect, type, total, power, accuracy, pp, spAtk, spDef, speed);
                this.pokemonMap.put(pokemon.getId(), pokemon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.reader = new BufferedReader(new FileReader("C:\\Users\\Codersbay\\Desktop\\CodersBay_AbdurakhmanVaschaev\\Java\\JavaAusbildung\\Pokemon\\src\\data\\Attacks.csv"));
            this.reader.readLine();

            while((currentLine = this.reader.readLine()) != null) {
                String[] components = currentLine.split(";");
                id = Integer.parseInt(components[0]);
                name = components[1];
                effect = components[2];
                type = components[3];
                String kind = components[4];
                power = Integer.parseInt(components[5]);
                accuracy = Integer.parseInt(components[6].replaceAll("%", ""));
                pp = Integer.parseInt(components[7]);
                Attack attack = new Attack(id, name, effect, type, kind, power, accuracy, pp);
                this.attackMap.put(attack.getId(), attack);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//