import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
public class Theatre {
    //Task 1
    static int[] rowOne = new int[12];     //rowOne is the variable used for row 1
    static int[] rowTwo = new int[16];     //rowTwo is the variable used for row 2
    static int[] rowThree = new int[20]; //rowThree is the variable used for row 3
    static int[][] combineRows = {rowOne, rowTwo, rowThree}; //Combined all 3 rows into one array
    static ArrayList<Ticket>user_details = new ArrayList<>(); //All Ticket classes are taken into an ArrayList
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        Scanner input = new Scanner(System.in);

        //Task 2
        int option;
        boolean quit;
        quit = false;
        do {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Please select an option: ");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("  " + "0) Quit");
            System.out.println("------------------------------------------------------------------------------------");

            System.out.print("Enter option: ");
            option = input.nextInt();
            switch (option) {
                case 1 -> buy_ticket(combineRows, user_details);
                case 2 -> print_seat_area(rowOne, rowTwo, rowThree);
                case 3 -> cancel_ticket(combineRows, user_details);
                case 4 -> show_available(combineRows);
                case 5 -> save(combineRows);
                case 6 -> load();
                case 7 -> show_tickets(user_details);
                case 8 -> sort_ticket(user_details);
                case 0 -> quit = true;
                default -> System.out.println("Invalid Option!");
            }
        }
        while (!quit);
    }

    //Task 3 and Task 12
    public static void buy_ticket(int[][] combineRows,ArrayList<Ticket>user_details){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the Name: ");
        String name = input.nextLine();
        System.out.print("Please enter the Surname: ");
        String surname = input.nextLine();
        System.out.print("Please enter the Email: ");
        String email = input.nextLine();
        while(true){
            if(email.contains("@")&& email.contains(".")){      //checking validation for the email
                break;
            }
            else {
                System.out.println("Please enter a valid email");
                System.out.print("Please enter the Email: ");
                email= input.next();
            }
        }
        Person person = new Person(name,surname,email); //Constructor for Person class
        int rowNum;
        int seatNum;
        double price;
        do{
            System.out.println("Row 1 : 1-12"+"  "+"Row 2 : 1-16"+"  "+"Row 3 : 1-20");
            while(true) {
                try {
                    System.out.print("Please enter the row number:");
                    rowNum = input.nextInt();
                    System.out.print("Please enter the seat number:");
                    seatNum = input.nextInt();
                    break;
                } catch(InputMismatchException e) {
                    System.out.println("Invalid input");       //Error message  when a string is entered for the row and seat
                    input.next();
                }
            }

            if (rowNum > 3 || rowNum < 1) {
                //Error messages if the row number is wrong
                System.out.println("Row number does not exist!\nRow number should be between 1 - 3");
            }
            else {
                break;
            }
        }while(true);
        int seatAllocation = combineRows[rowNum-1][seatNum-1]; //Assigning the row and seat
        System.out.println("Prices for Row 1 - £30 , Row 2 - £20 , Row 3 - £10");    //Assigning a fixed value for the three rows
        System.out.print("Please enter the price £: ");
        price = input.nextDouble();
        while(true) {
            if(price != 10 && price != 20 && price != 30) {
                System.out.println("Please enter a valid price \n Prices are £10 , £20 , £30");
                System.out.print("Please enter the price: £ ");
                price = input.nextDouble();
            } else {
                break;
            }
        }
        if(seatAllocation==0){
            combineRows[rowNum-1][seatNum-1] = 1;
            System.out.println("Thank You Seat booked!");
        }
        else System.out.println("Seat not Available!");
        Ticket ticket = new Ticket(rowNum,seatNum,price,person); //Constructor for Ticket class
        user_details.add(ticket); //Adding the Ticket constructor to the ArrayList
    }
    //Task 4
    public static void print_seat_area(int[] rowOne,int[] rowTwo, int[] rowThree){
            System.out.println("    ***********\n    *  STAGE  *\n    ***********");
            System.out.print("    ");
            row_printing(rowOne);  //calling the method to row 1
            System.out.println();
            System.out.print("  ");
            row_printing(rowTwo);  //calling the method to row 2
            System.out.println();
            row_printing(rowThree);  //calling the method to row 3
            System.out.println();
    }
    //method to print all 3 rows one at a time
    public static void row_printing(int[] row_array){
        for(int i =0;i <row_array.length; i++){
            if(i==row_array.length/2){
                System.out.print(" ");
            }
            if(row_array[i] == 1){
                System.out.print("X");
            }
            if(row_array[i] == 0){
                System.out.print("O");
            }
        }
    }


    //Task 5
    public static void cancel_ticket(int[][]combineRows,ArrayList<Ticket>user_details) {
        Scanner input = new Scanner(System.in);
        int rowNum;
        int seatNum;
        do {
            System.out.println("Row 1 : 1-12"+"  "+"Row 2 : 1-16"+"  "+"Row 3 : 1-20");
            while(true) {
                try {
                    System.out.print("Please enter the row number:");
                    rowNum = input.nextInt();
                    System.out.print("Please enter the seat number:");
                    seatNum = input.nextInt();
                    break;
                } catch(InputMismatchException e) {
                    // validating when a string is entered for the row and seat numbers
                    System.out.println("Invalid input");
                    input.next();
                }
            }

            if (rowNum > 3 || rowNum < 1) {
                //Error messages if the row number is wrong
                System.out.println("Row number does not exist!\nRow number should be between 1 - 3");
            }
            else {
                break;
            }
        }while(true);
        if(combineRows[rowNum-1][seatNum-1] == 0){
            System.out.println("Seat not booked");
        }
        else{
            combineRows[rowNum-1][seatNum-1] = 0;
            System.out.println("Thank You! Your ticket is cancelled.");
        }
        //Task 12
        for(Ticket ticket_details:user_details){
            if(ticket_details.getRow() == rowNum && ticket_details.getSeat() ==seatNum) {
                user_details.remove(ticket_details);  //removing the ticket from the ArrayList
                return;
            }
        }
    }
    //Task 6
    public static void show_available(int[][]combineRows){
        int i,j;
        for(i =0;i<3 ; i++){
            int row = i+1; //Since i=0 row 1 will be row 0,we use the variable row to +1 to make it row 1
            System.out.print("Seats available in row"+row+":");
            for(j = 0; j<combineRows[i].length; j++){
                int seat = j+1; //j=0 seat 1 in each row will be displayed as 0 we use seat variable to make it 1
                if (combineRows[i][j] == 1){
                    System.out.print("");
                }
                else{
                    System.out.print(seat+",");
                }
            }
            System.out.println();
        }
    }
    //Task 7
    public static void save(int[][]combineRows){
        try{
            FileWriter myWriter = new FileWriter("TheatreSeats.txt");
            for (int[] combineRow : combineRows) {
                for (int i : combineRow) {
                    myWriter.write(i+" ");  //Converting the array into String
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("File Created!");
        }
        catch(IOException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }
    //Task 8
    public static void load(){
       try{
           File myfile = new File("TheatreSeats.txt");
           Scanner myReader = new Scanner(myfile);
           for(int i = 0;i< combineRows.length;i++){
               for(int j=0; j<combineRows[i].length;j++){
                   combineRows[i][j] = myReader.nextInt();
               }
           }
           myReader.close();
           System.out.println(Arrays.toString(rowOne)+"\n"+Arrays.toString(rowTwo)+"\n"+Arrays.toString(rowThree));
       }
       catch(IOException e){
           System.out.println("Error occurred");
           e.printStackTrace();
       }
    }
    //Task 13
    public static void show_tickets(ArrayList<Ticket>user_details){
        double sum =0;
        for(Ticket print_details:user_details) {
            System.out.println();
            print_details.print();
            System.out.println();
            //Calculating the total sum of the ticket prices
            sum += print_details.getPrice();
            System.out.println("Total price of the tickets: " + sum);
        }
    }
    //Task 14
    public static void sort_ticket(ArrayList<Ticket>user_details) {
        for(int i = 0; i< user_details.size(); i++){  //outer loop
            for(int j = i+1; j< user_details.size(); j++){  //inner loop
                if(user_details.get(i).getPrice() > user_details.get(j).getPrice()){  //sorts the tickets in ascending order
                    Ticket temp = user_details.get(i);
                    user_details.set(i, user_details.get(j));
                    user_details.set(j, temp);
                }
            }
        }
        for(Ticket final_details: user_details){
            System.out.println();
            final_details.print();
        }
    }
}

