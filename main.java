package cinema;
import java.util.Scanner;
public class Cinema {

    static double purchasedTickets = 0;
    static double totalTickets = 0;
    static double currentIncome = 0;
    static double totalIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter the number of rows: ");
        int rows = sc.nextInt();
        rows++;
        System.out.println("Enter the number of seats in each row: ");
        int seats = sc.nextInt();
        seats++;
        totalTickets = (rows-1)*(seats-1);
        char[][] cinemaRoom = new char[rows][seats];
        createCinema(cinemaRoom,rows,seats);

        boolean run = true;
        while(run){
            printMenu(sc, cinemaRoom, rows, seats);
            int action = sc.nextInt();
            switch(action){
                case 1:
                    printCinema(cinemaRoom,rows,seats);
                    break;
                case 2:
                    updateCinema(sc,cinemaRoom,rows,seats);
                    break;
                case 3:
                    showStats(rows, seats);
                    break;
                case 0:
                    run = false;
                    break;
            }
        }
        sc.close();
    }

    //Create empty cinema room grid
    private static void createCinema(char[][] cinemaRoom,int rows,int seats){
        cinemaRoom[0][0] = ' ';
        int j = 49;
        for(int i = 1; i < seats; i++){
            cinemaRoom[0][i] = (char) j;
            j++;
        }
        int g = 49;
        for(int i = 1; i < rows; i++){
            cinemaRoom[i][0] = (char) g;
            g++;
        }
        for(int i = 1; i < rows; i++){
            for(int h = 1; h < seats; h++){
                cinemaRoom[i][h] = 'S';
            }
        }
    }

    //Print cinema grid
    private static void printCinema(char[][] cinemaRoom, int rows, int seats){
        System.out.println();
        System.out.println("Cinema:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < seats; j++){
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Buying and updating seat
    private static void updateCinema(Scanner sc, char[][] cinemaRoom, int rows, int seats){
        while(true){

            int rowNumber;
            while(true){
                System.out.println("\nEnter a row number: ");
                rowNumber = sc.nextInt();
                if(rowNumber<rows){
                    break;
                }else{
                    System.out.println("Wrong input!");
                }
            }

            int seatNumber;
            while(true){
                System.out.println("Enter a seat number in that row: ");
                seatNumber = sc.nextInt();
                if(seatNumber<seats){
                    break;
                }else{
                    System.out.println("Wrong input!");
                }
            }

            if(cinemaRoom[rowNumber][seatNumber] == 'B'){
                System.out.println("That ticket has already been purchased!");
            }else{
                cinemaRoom[rowNumber][seatNumber] = 'B';
                int totalSeats = (rows-1)*(seats-1);
                if(totalSeats<60){
                    System.out.println("Ticket price: $10");
                    purchasedTickets++;
                    currentIncome += 10;
                    break;
                }else{
                    if(rowNumber<=((rows-1)/2)){
                        System.out.println("Ticket price: $10");
                        purchasedTickets++;
                        currentIncome += 10;
                        break;
                    }else{
                        System.out.println("Ticket price: $8");
                        purchasedTickets++;
                        currentIncome += 8;
                        break;
                    }
                }
            }
        }

    }

    //Print menu
    private static void printMenu(Scanner sc, char[][] cinemaRoom, int rows, int seats){
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    //Show statistics
    private static void showStats(int rows, int seats){
        System.out.printf("Number of purchased tickets: %.0f\n", purchasedTickets);
        double percentage = (purchasedTickets*100)/totalTickets;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%.0f\n", currentIncome);

        if(totalTickets <= 60){
            totalIncome = totalTickets*10;
        }else{
            int fronts = (rows-1)/2;
            int backs = (rows-1)-fronts;
            double frontsTotal = (fronts*(seats-1)*10);
            double backsTotal = (backs*(seats-1)*8);
            totalIncome = frontsTotal+backsTotal;
        }
        System.out.printf("Total income: $%.0f\n\n", totalIncome);

    }

}
