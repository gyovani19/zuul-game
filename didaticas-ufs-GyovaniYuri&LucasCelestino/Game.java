/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.Stack;


public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomHistory;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<>();

    }


    private void createRooms()
    {
        Room entrada, did1, did2, did3, did4, did5, did6;
      
        // create the rooms
        entrada = new Room(" entrada principal da UFS");
        did1 = new Room(" didática 1.");
        did2 = new Room(" didática 2.");
        did3 = new Room(" didática 3.");
        did4 = new Room(" didática 4.");
        did5 = new Room(" didática 5.");
        did6 = new Room(" didática 6.");
        //create the items



        // initialise room exits
        entrada.setExit("leste", did6);
        entrada.setExit("oeste", did5);
        did5.setExit("norte", did3);
        did5.setExit("leste", did6);
        did6.setExit("norte", did2);
        did6.setExit("oeste", did5);
        did3.setExit("oeste", did4);
        did3.setExit("leste", did2);
        did3.setExit("sul", did5);
        did4.setExit("leste", did3);
        did2.setExit("oeste", did3);
        did2.setExit("leste", did1);
        did2.setExit("sul", did6);
        did1.setExit("oeste", did2);


        //Initialize the items
        did5.addItem("Livro", "Um pequeno livro chamado: 'Cálculo 1 - James Stewart'", 20.0);
        did6.addItem("Guarda-chuva", "Parece que alguém esquecer um guarda-chuva aqui.", 0.5);
        did4.addItem("Caderno", "Um caderno com anotações sobre Biologia", 0.3);
        did3.addItem("Bicicleta", "Uma bicicleta presa a uma pilastra", 11.5);
        did2.addItem("Casaco", "Um casaco azul e velho.", 0.2);
        did1.addItem("Notebook", "Um dos notebook mais potentes do mercado, da marca Positivo.", 2.1);


        currentRoom = entrada;  // start game outside
    }

    public void play() 
    {            
        printWelcome();


                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar.  Até mais tarde.");
    }


    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo(a) às Didáticas da UFS");
        System.out.println();
        System.out.println("Digital 'ajuda' se precisar de ajuda ");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }


    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("Comando desconhecido...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda")) {
            printHelp();
        } else if (commandWord.equals("ir")) {
            goRoom(command);
        } else if (commandWord.equals("sair")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("voltar")) {
            if (!command.hasSecondWord()) {
                goBack();
            } else {
                System.out.println("Voltar para?");
            }
        }
        // else command not recognised.
        return wantToQuit;
    }

    private void goBack() {
        if (!roomHistory.isEmpty()) {
            currentRoom = roomHistory.pop();
            System.out.println(currentRoom.getLongDescription());
        } else {
            System.out.println("Você não pode voltar.");
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("O objetivo deste jogo é conhecer as didáticas");
        System.out.println();
        System.out.println("Os comandos disponíveis são:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir onde?");
            return;
        }

        String direction = command.getSecondWord();
        roomHistory.push(currentRoom);

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há porta!");
        }
        else {

            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.printItems();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
