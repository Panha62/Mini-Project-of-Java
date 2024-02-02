import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String[] ID = new String[0];
    private static int[] Session = new int[0];
    private static String[][] SeatBooked = new String[0][0];
    private static int bookingCount = 0;

    public static void Booking(String[][] mornings, String[][] afternoons, String[][] evenings) {
        Scanner input = new Scanner(System.in);
        System.out.println("*********************************************************");
        System.out.println(">>> Start booking Process...");
        System.out.println(">>> Select the Session Time : ");
        System.out.println("1. Morning");
        System.out.println("2. Afternoon");
        System.out.println("3. Evening");
        int session = 0;
        boolean validInput;
        do {
            System.out.print("Please Choose option ( 1-3 ) : ");
            String sessionStr = input.nextLine();
            System.out.println();
            System.out.println("******************************************************************************************************************************");
            if (sessionStr.matches("^[1-3]$")) {
                session = Integer.parseInt(sessionStr);
                validInput = true;
            } else {
                System.out.println(">> Error Format...! Please choose option ( 1-3 ) : ");
                validInput = false;
            }
        } while (!validInput);

        String[][] selectedSession;
        switch (session) {
            case 1:
                selectedSession = mornings;
                break;
            case 2:
                selectedSession = afternoons;
                break;
            case 3:
                selectedSession = evenings;
                break;
            default:
                System.out.println(">> Error Format...! Please choose option ( 1-3 ) : ");
                return;
        }

        System.out.println(">>> Available seats in the " + (session == 1 ? "morning" : session == 2 ? "afternoon" : "evening") + " session : ");
        for (int i = 0; i < selectedSession.length; i++) {
            for (int j = 0; j < selectedSession[i].length; j++) {
                if (session == 1) {
                    System.out.print("[ A - " + ((i * selectedSession[i].length) + j + 1) + " \t::\t " + selectedSession[i][j] + " ]\t ");
                } else if (session == 2) {
                    System.out.print("[ B - " + ((i * selectedSession[i].length) + j + 1) + " \t::\t " + selectedSession[i][j] + " ]\t ");
                } else {
                    System.out.print("[ C - " + ((i * selectedSession[i].length) + j + 1) + " \t::\t " + selectedSession[i][j] + " ]\t ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("******************************************************************************************************************************");
        System.out.println("# INSTRUCTION");
        System.out.println("# For Single Seat : A-1.");
        System.out.println("# For Multi-Seat Booking : C-1, C-2");
        System.out.println();
        System.out.println("******************************************************************");
        do {
            System.out.print("Select Seat Number : ");
            String seatInput = input.next();
            if (seatInput.matches("^[a-zA-Z]-\\d+(,\\s?[a-zA-Z]-\\d+)*$")) {
                String[] seatNumbers = seatInput.split(",\\s*");
                System.out.print("Enter the booking ID: ");
                String id = input.next();

                // Process each seat individually
                for (String seatNumber : seatNumbers) {
                    int seat = Integer.parseInt(seatNumber.trim().substring(2)); // Extract the seat number from the input
                    int row = (seat - 1) / selectedSession[0].length;
                    int col = (seat - 1) % selectedSession[0].length;
                    if (row >= 0 && row < selectedSession.length && col >= 0 && col < selectedSession[row].length) {
                        if (selectedSession[row][col].equals("AV")) {
                            selectedSession[row][col] = "BO"; // Assuming "B" represents a booked seat
                            System.out.println("Seat was successfully booked with ID " + id + " in the " + (session == 1 ? "morning" : session == 2 ? "afternoon" : "evening") + " session!");
                            ID = Arrays.copyOf(ID, bookingCount + 1);
                            Session = Arrays.copyOf(Session, bookingCount + 1);
                            SeatBooked = Arrays.copyOf(SeatBooked, bookingCount + 1);
                            ID[bookingCount] = Arrays.toString(ID);
                            Session[bookingCount] = session;
                            // Concatenate the seat numbers into a single string
                            SeatBooked[bookingCount] = new String[]{seatNumber};
                            bookingCount++;
                        } else {
                            System.out.println("This seat was already booked in the " + (session == 1 ? "morning" : session == 2 ? "afternoon" : "evening") + " session.");
                        }
                    } else {
                        System.out.println("Invalid seat number for the " + (session == 1 ? "morning" : session == 2 ? "afternoon" : "evening") + " session.");
                    }
                }
                validInput = true;
            } else {
                System.out.println(">> Error Format...!");
                validInput = false;
            }
        } while (!validInput);

    }

    public static void showHall(String[][] mornings, String[][] afternoons, String[][] evenings) {
        System.out.println("******************************************************************************************************************************");
        System.out.println("# Hall A");
        for (int i = 0; i < mornings.length; i++) {
            for (int j = 0; j < mornings[i].length; j++) {
                System.out.print("[ A - " + ((i * mornings[i].length) + j + 1) + " \t::\t " + mornings[i][j] + " ]\t ");
            }
            System.out.println();
        }
        System.out.println("******************************************************************************************************************************");
        System.out.println("# Hall B");
        for (int i = 0; i < afternoons.length; i++) {
            for (int j = 0; j < afternoons[i].length; j++) {
                System.out.print("[ B - " + ((i * afternoons[i].length) + j + 1) + " \t::\t " + afternoons[i][j] + " ]\t ");
            }
            System.out.println();
        }
        System.out.println("******************************************************************************************************************************");
        System.out.println("# Hall C");
        {
            for (int i = 0; i < evenings.length; i++) {
                for (int j = 0; j < evenings[i].length; j++) {
                    System.out.print("[ C - " + ((i * evenings[i].length) + j + 1) + " \t::\t " + evenings[i][j] + " ]\t ");
                }
                System.out.println();
            }
        }
        System.out.println("******************************************************************************************************************************");
    }

    public static void showTime() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("   #Daily Showtime of CSTAD Hall ");
        System.out.println("# 1. Morning    (10:00AM - 12:30PM)");
        System.out.println("# 2. Afternoon  (03:00PM - 05:30PM)");
        System.out.println("# 3. Evening    (07:00PM - 09:30PM)");
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }

    public static void bookingHistory() {
        if (bookingCount != 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = LocalDate.now().format(formatter);
            System.out.println("Session\t\tID\t\tSeat\t\t\tDate");

            // Display all bookings in history
            for (int i = 0; i < bookingCount; i++) {
                String sessionStr = Session[i] == 1 ? "morning" : Session[i] == 2 ? "afternoon" : "evening";
                String seats = Arrays.toString(SeatBooked[i]).replaceAll("\\[|\\]", "").replace(", ", ",");
                System.out.println(String.format("%-10s%-10s%-15s%s", sessionStr, ID[i], seats, formattedDate));
            }
        } else {
            System.out.println("You haven't made any booking yet!!");
        }
    }

    public static void rebootHall(String[][] mornings, String[][] afternoons, String[][] evenings) {
        Scanner input = new Scanner(System.in);
        System.out.print("This will clear all booking records!! Are you sure to reboot the hall? (y/n) : ");
        String confirmation = input.next();
        if ("y".equalsIgnoreCase(confirmation)) {
            for (int i = 0; i < mornings.length; i++) {
                for (int j = 0; j < mornings[i].length; j++) {
                    mornings[i][j] = "AV";
                }
            }
            for (int i = 0; i < afternoons.length; i++) {
                for (int j = 0; j < afternoons[i].length; j++) {
                    afternoons[i][j] = "AV";
                }
            }
            for (int i = 0; i < evenings.length; i++) {
                for (int j = 0; j < evenings[i].length; j++) {
                    evenings[i][j] = "AV";
                }
            }
            //reset booking history
            ID = new String[0];
            Session = new int[0];
            SeatBooked = new String[0][0];
            bookingCount = 0;
            System.out.println("Hall has been rebooted! All current bookings have been cleared!");
        } else {
            System.out.println("Reboot was cancelled.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows, cols = 0;
        String[][] mornings = new String[0][], afternoons = new String[0][], evenings = new String[0][];
        int options = 0;
        boolean isRowValid = false, isColsValid = false, isInputValid;

        System.out.println();
        System.out.println("===================================================");
        System.out.println("*                                                 *");
        System.out.println("*            CSTAD HALL BOOKING SYSTEM            *");
        System.out.println("*                                                 *");
        System.out.println("===================================================");
        System.out.println();

        do {
            System.out.println("========================>>> [ Set up Hall ] <<<========================");
            System.out.print(">> Enter total number rows of Hall : ");
            String rowStr = input.nextLine();
            if (rowStr.matches("^[0-9]*$") && Integer.parseInt(rowStr) > 0) {
                isRowValid = true;
                System.out.print(">> Enter total seat per row of Hall : ");
                String colStr = input.nextLine();
                if (colStr.matches("^[0-9]*$") && Integer.parseInt(colStr) > 0) {
                    isColsValid = true;
                    rows = Integer.parseInt(rowStr);
                    cols = Integer.parseInt(colStr);
                    mornings = new String[rows][cols];
                    afternoons = new String[rows][cols];
                    evenings = new String[rows][cols];
                    // Initializing the seats array with empty strings
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            mornings[i][j] = "AV";
                            afternoons[i][j] = "AV";
                            evenings[i][j] = "AV";
                        }
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!isRowValid || !isColsValid);
        System.out.println();

        do {
            System.out.println("===============|Theater Menu Booking|===============");
            System.out.println("1. Hall Booking");
            System.out.println("2. Hall Checking");
            System.out.println("3. Show Time Checking");
            System.out.println("4. Booking History");
            System.out.println("5. Rebooting Hall");
            System.out.println("6. Exit");
            do {
                System.out.print("Please Choose option from 1-6 : ");
                String optionsStr = input.nextLine();
                System.out.println();

                if (optionsStr.matches("^[1-6]$")) {
                    options = Integer.parseInt(optionsStr);
                    isInputValid = true;
                } else {
                    System.out.println("Please Input the valid input 1-6!!");
                    isInputValid = false;
                }
            } while (!isInputValid);
            switch (options) {
                case 1:
                    Booking(mornings, afternoons, evenings);
                    input.nextLine();
                    break;
                case 2:
                    showHall(mornings, afternoons, evenings);
                    input.nextLine();
                    break;
                case 3:
                    showTime();
                    input.nextLine();
                    break;
                case 4:
                    bookingHistory();
                    input.nextLine();
                    break;
                case 5:
                    rebootHall(mornings, afternoons, evenings);
                    input.nextLine();
                    break;
                case 6:
                    System.exit(0);
                    input.nextLine();
                    break;
            }
        } while (options != 6);
    }

}