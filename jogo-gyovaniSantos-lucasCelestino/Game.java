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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("" +
                "fora da entrada do campus, vendo o portão\n                                                                                \n" +
                "                                                                                \n" +

                "                   @@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@                  \n" +
                "       @@@@@@ @@@. @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@ .@@@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       @@@@@@ @@   @@   @@  @@*  @@   @@ @@   @@  %@*  @@   @@   @@ @@@@@%      \n" +
                "       #@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@,      \n" +
                "                                                                                \n" +
                "                                                                                \n");
        theater = new Room("in a lecture theater\n                                                                                \n" +
                "    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   \n" +
                " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@@&*************************************************************************@@@\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                " @@@%(((((@@@#((((#@@@((((@@@(((((@@@,,,@,,,@@@((((#@@@((((@@@(((((@@@%(((((@@@(\n" +
                " @@@%(((((@@@(((((((((((((@@@(((((@@@@@@@@@@@@@(((((@@@((((@@@#((((&@@@(((((@@@(\n" +
                " @@@%(((((@@@(((((@@@((((@@@@((((@@@#       @@@#((((@@@(((((@@@(((((@@@(((((@@@(\n" +
                " @@@%((((#@@@(((((@@(((((@@@(((((@@@         @@@(((((@@@((((@@@(((((@@@(((((@@@(\n" +
                " @@@%(((@@@((((%@@@(((&@@@((((@@@              #@@@((((@@@((((@@@((((#@@@(((@@@(\n" +
                " @@@%((@@@&(((@@@@(((@@@@(((@@@@                 @@@@(((@@@@(((@@@%(((@@@@((@@@(\n" +
                " @@@@@@@(((@@@@((@@@@((@@@@@                         @@@@@(#@@@@((@@@@(((@@@@@@(\n" +
                " @@@@@@@@@@@@@@@@@@@@                                       @@@@@@@@@@@@@@@@@@@(\n" +
                " @@@@@@@@@@@@@@&                                                 @@@@@@@@@@@@@@(\n" +
                " @@@%@@@(@@@@(@@@%                                             @@@@(@@@@(@@@@@@(\n" +
                " @@@%(@@@(@@@@(%@@@                                          .@@@((@@@%(@@@(@@@(\n" +
                " @@@%(@@@@(@@@@((@@@%                                       @@@@((@@@#(@@@%(@@@(\n" +
                " @@@%((@@@((@@@&((@@@&                                     @@@@((&@@@((@@@((@@@(\n" +
                " @@@%((@@@(((@@@(((@@@,                                   &@@@(((##(((@@@@((@@@(\n" +
                " @@@%((%@@@((&@@@(((@@@                                   @@@((((@@(((@@@(((@@@(\n" +
                " @@@%(((@@@(((@@@(((@@@#                                 @@@%(((((((((@@@(((@@@(\n" +
                " @@@%(((@@@(((@@@((((@@@                                 @@@((((@@@(((@@@(((@@@(\n" +
                " @@@%(((@@@(((@@@#(((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(((@@@&(((@@@(((@@@(\n" +
                "  @@@@@@@@@@@@@@@@@@@@@&                                 @@@@@@@@@@@@@@@@@@@@@@ \n" +
                "                                                                                \n");
        pub = new Room("no pub do campus");
        lab = new Room("no laboratorio de computacao");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Bem-vindo á UFS!");
        System.out.println("Esse é um jogo pra te ajudar a conhecer o campus São Cristóvao da UFS.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();
        printLocationInfo(currentRoom); // Call the new method here
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Não entendi o que você quis dizer...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda")) {
            printHelp();
        }
        else if (commandWord.equals("ir")) {
            goRoom(command);
        }
        else if (commandWord.equals("sair")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Ir onde?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = null;
        if (direction.equals("norte")) {
            nextRoom = currentRoom.northExit;
        }
        if (direction.equals("leste")) {
            nextRoom = currentRoom.eastExit;
        }
        if (direction.equals("sul")) {
            nextRoom = currentRoom.southExit;
        }
        if (direction.equals("oeste")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("Não há porta!");
        } else {
            currentRoom = nextRoom;
            printLocationInfo(currentRoom);
        }
    }
    private void printLocationInfo(Room room) {
        System.out.println("Você está " + room.getDescription());
        System.out.print("Saídas: ");
        if (room.northExit != null) {
            System.out.print("norte ");
        }
        if (room.eastExit != null) {
            System.out.print("leste ");
        }
        if (room.southExit != null) {
            System.out.print("sul ");
        }
        if (room.westExit != null) {
            System.out.print("oeste ");
        }
        System.out.println();
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
