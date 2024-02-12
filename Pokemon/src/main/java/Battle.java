import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final Scanner scan;
    private Random random;
    private Pokemon pokemon;
    private Pokemon pokemonPC;
    private final List<Integer> possibleAttackIDList;
    private final DataReader dataReader;

    public Battle() {
        this.scan = new Scanner(System.in);
        this.random = new Random();
        this.possibleAttackIDList = new ArrayList();
        this.dataReader = new DataReader();
    }

    public void startBattle() {
        this.showAllPokemon();
        this.pokemon = this.choosePokemon();
        int controller = 1;
        this.showAllPossibleAttacks(this.pokemon, this.possibleAttackIDList, controller);
        this.chooseAttack(this.pokemon, this.possibleAttackIDList);
        this.pokemonPC = this.choosePokemonPC(this.pokemonPC);
        this.possibleAttackIDList.clear();
        controller = 0;
        this.showAllPossibleAttacks(this.pokemonPC, this.possibleAttackIDList, controller);
        this.pokemonPC = this.addTwoRandomAttacksForPokemonPC(this.pokemonPC, this.possibleAttackIDList);
        if (this.pokemon.getSpeed() > this.pokemonPC.getSpeed()) {
            while(this.pokemon.getHp() > 0.0 && this.pokemonPC.getHp() > 0.0) {
                this.playerAttack(this.pokemon, this.pokemonPC);
                if (this.pokemonPC.getHp() > 0.0) {
                    this.pcAttack(this.pokemonPC, this.pokemon);
                }
            }
        } else {
            while(this.pokemon.getHp() > 0.0 && this.pokemonPC.getHp() > 0.0) {
                this.pcAttack(this.pokemonPC, this.pokemon);
                if (this.pokemon.getHp() > 0.0) {
                    this.playerAttack(this.pokemon, this.pokemonPC);
                }
            }
        }

    }

    public void showAllPokemon() {
        System.out.println("Liste aller Pokemons");

        for(int i = 1; i <= this.dataReader.getPokemonMap().size(); ++i) {
            System.out.println(this.dataReader.getPokemonMap().get(i));
            System.out.println("---------------------------------------------------------");
        }

    }

    public Pokemon choosePokemon() {
        System.out.println("Waehle ein Pokemon aus, indem du die ID oder den Namen des Pokemons eingibst.");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        PrintStream var10000;
        Pokemon var10001;
        int index;
        Pokemon pokemon;
        if (input.matches(".*\\d.*")) {
            index = Integer.parseInt(input);
            if (index <= 0 || index > this.dataReader.getPokemonMap().size()) {
                System.out.println("Dieser Index existiert nicht.");
                this.choosePokemon();
            }

            for(int i = 1; i <= this.dataReader.getPokemonMap().size(); ++i) {
                if (((Pokemon)this.dataReader.getPokemonMap().get(i)).getId() == index) {
                    var10000 = System.out;
                    var10001 = (Pokemon)this.dataReader.getPokemonMap().get(i);
                    var10000.println("Du hast " + var10001.getName() + " ausgewaehlt.");
                    pokemon = (Pokemon)this.dataReader.getPokemonMap().get(i);
                    Pokemon pokemonCopy = pokemon.clone();
                    return pokemonCopy;
                }
            }
        } else {
            for(index = 1; index <= this.dataReader.getPokemonMap().size(); ++index) {
                if (input.equals(((Pokemon)this.dataReader.getPokemonMap().get(index)).getName())) {
                    var10000 = System.out;
                    var10001 = (Pokemon)this.dataReader.getPokemonMap().get(index);
                    var10000.println("Du hast " + var10001.getName() + " ausgewaehlt.");
                    pokemon = (Pokemon)this.dataReader.getPokemonMap().get(index);
                    pokemon = pokemon.clone();
                    return pokemon;
                }

                if (index == this.dataReader.getPokemonMap().size() - 1 && ((Pokemon)this.dataReader.getPokemonMap().get(index)).getName().equals(input)) {
                    System.out.println("Dieser Name fuer ein Pokemon existiert im Programm nicht.");
                    this.choosePokemon();
                }
            }
        }

        return null;
    }

    public void showAllPossibleAttacks(Pokemon pokemon, List<Integer> possibleAttackIDList, int controller) {
        if (controller == 1) {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println(String.valueOf(pokemon) + "\n");
            System.out.println(pokemon.getName() + " kann folgende Attacken nutzen.\n");
        }

        PrintStream var10000;
        String var10001;
        int i;
        for(i = 1; i < this.dataReader.getAttackMap().size(); ++i) {
            if (((Attack)this.dataReader.getAttackMap().get(i)).getType().equals(pokemon.getType1())) {
                if (controller == 1) {
                    var10000 = System.out;
                    var10001 = String.valueOf(this.dataReader.getAttackMap().get(i));
                    var10000.println(var10001 + "\n");
                    System.out.println("---------------------------------------------------------");
                }

                possibleAttackIDList.add(((Attack)this.dataReader.getAttackMap().get(i)).getId());
            }
        }

        if (pokemon.getType2() != null) {
            for(i = 1; i < this.dataReader.getAttackMap().size(); ++i) {
                if (((Attack)this.dataReader.getAttackMap().get(i)).getType().equals(pokemon.getType2())) {
                    if (controller == 1) {
                        var10000 = System.out;
                        var10001 = String.valueOf(this.dataReader.getAttackMap().get(i));
                        var10000.println(var10001 + "\n");
                        System.out.println("---------------------------------------------------------");
                    }

                    possibleAttackIDList.add(((Attack)this.dataReader.getAttackMap().get(i)).getId());
                }
            }
        }

    }

    public void chooseAttack(Pokemon pokemon, List<Integer> possibleAttackIDList) {
        System.out.println("\nWaehle zwei Attacken aus dieser Liste aus, indem du deren ID reinschreibst.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        int id01;
        int id02;
        do {
            Collections.sort(possibleAttackIDList);
            System.out.println(possibleAttackIDList);
            id01 = this.controlNumber();
            id02 = this.controlNumber();
            System.out.println(id01);
            if (!possibleAttackIDList.contains(id01) || !possibleAttackIDList.contains(id02)) {
                System.out.println("Waehle nur eine ID aus der vorherigen Liste der Attacken aus.");
            }
        } while(!possibleAttackIDList.contains(id01) || !possibleAttackIDList.contains(id02));

        pokemon.setFirstAttack((Attack)this.dataReader.getAttackMap().get(id01));
        pokemon.setSecondAttack((Attack)this.dataReader.getAttackMap().get(id02));
        PrintStream var10000 = System.out;
        String var10001 = pokemon.getFirstAttack().getName();
        var10000.println("Du hast " + var10001 + " und " + pokemon.getSecondAttack().getName() + " ausgewaehlt.\n");
    }

    public Pokemon choosePokemonPC(Pokemon pokemonPC) {
        pokemonPC = ((Pokemon)this.dataReader.getPokemonMap().get(this.random.nextInt(this.dataReader.getPokemonMap().size()) + 1)).clone();
        System.out.println("Gegner");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(pokemonPC);
        return pokemonPC;
    }

    public Pokemon addTwoRandomAttacksForPokemonPC(Pokemon pokemon, List<Integer> possibleAttackIDList) {
        this.random = new Random();
        pokemon.setFirstAttack((Attack)this.dataReader.getAttackMap().get(possibleAttackIDList.get(this.random.nextInt(possibleAttackIDList.size()))));

        do {
            pokemon.setSecondAttack((Attack)this.dataReader.getAttackMap().get(possibleAttackIDList.get(this.random.nextInt(possibleAttackIDList.size()))));
        } while(pokemon.getFirstAttack() == pokemon.getSecondAttack());

        PrintStream var10000 = System.out;
        String var10001 = pokemon.getFirstAttack().getName();
        var10000.println("Die Attacken " + var10001 + " und " + pokemon.getSecondAttack().getName() + " wurden von dem Gegner ausgewaehlt.\n");
        System.out.println(String.valueOf(pokemon.getFirstAttack()) + "\n");
        System.out.println(pokemon.getSecondAttack());
        return pokemon;
    }

    public void playerAttack(Pokemon pokemon, Pokemon pokemonEnemy) {
        System.out.println("Waehle eine Attacke aus.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int control = 1;

        int chooseProgram;
        do {
            do {
                PrintStream var10000 = System.out;
                String var10001 = pokemon.getFirstAttack().getName();
                var10000.println("1. " + var10001 + "\n2. " + pokemon.getSecondAttack().getName());
                chooseProgram = this.scan.nextInt();
            } while(chooseProgram < 1);
        } while(chooseProgram > 2);

        double damage;
        if (chooseProgram == 1) {
            damage = this.damageCalculation(pokemon.getFirstAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
        } else {
            damage = this.damageCalculation(pokemon.getSecondAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
        }

        this.healthEffect(damage, pokemonEnemy, control);
    }

    public void pcAttack(Pokemon pokemon, Pokemon pokemonEnemy) {
        System.out.println("Dein Gegner greift an.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int control = 0;
        int chooseProgram = this.random.nextInt(2) + 1;
        PrintStream var10000;
        String var10001;
        double damage;
        if (chooseProgram == 1) {
            var10000 = System.out;
            var10001 = pokemon.getName();
            var10000.println(var10001 + " nutzt " + pokemon.getFirstAttack().getName() + ".");
            damage = this.damageCalculation(pokemon.getFirstAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
            this.healthEffect(damage, pokemonEnemy, control);
        } else {
            var10000 = System.out;
            var10001 = pokemon.getName();
            var10000.println(var10001 + " nutzt " + pokemon.getSecondAttack().getName() + ".");
            damage = this.damageCalculation(pokemon.getSecondAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
            this.healthEffect(damage, pokemonEnemy, control);
        }

    }

    public double damageCalculation(int power, int baseAttack, int enemyDefense) {
        double damage = (double)power * (double)baseAttack / (double)enemyDefense * 0.16666666666666666;
        System.out.printf("Schaden: %.2f\n\n", damage);
        return damage;
    }

    public void healthEffect(double damage, Pokemon pokemon, int control) {
        pokemon.setHp(pokemon.getHp() - damage);
        String text;
        if (pokemon.getHp() <= 0.0) {
            text = control > 0 ? pokemon.getName() + " ist gestorben\n." : " Du bist gestorben.\n";
            System.out.println(text);
        } else {
            text = control > 0 ? String.format("%s hat noch %.2f Lebenspunkte\n", pokemon.getName(), pokemon.getHp()) : String.format("Du hast noch %.2f Lebenspunkte.\n", pokemon.getHp());
            System.out.print(text);
        }

    }

    public int controlNumber() {
        Scanner scan = new Scanner(System.in);

        while(true) {
            while(scan.hasNextInt()) {
                int number = scan.nextInt();
                if (number >= 0) {
                    return number;
                }

                System.out.println("Du darfst nur eine positive Zahl reinschreiben");
            }

            System.out.println("Du kannst nur eine Zahl reinschreiben.");
            scan.nextInt();
        }
    }
}
//