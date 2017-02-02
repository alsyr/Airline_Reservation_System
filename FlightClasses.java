/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Creates the first and economy classes and keeps track of all passengers
 */
public class FlightClasses {

  private Rows[] _first;
  private Rows[] _economy;
  private int _numEmptySeatFirst;
  private int _numEmptySeatEco;
  public static final int NUM_ROW_FIRST = 2;
  public static final int NUM_ROW_ECO = 20;
  public static final int NUM_SEAT_FIRST = 4;
  public static final int NUM_SEAT_ECO = 6;
  public static final int ERROR_SEAT = 100;
  public static final int GAP_FIRST = 1;
  public static final int GAP_ECO = 10;

  /**
   * Construct both classes as arrays of Rows objects. Initialize also the number of empty seats in
   * those classes at the beginning
   */
  public FlightClasses() {
    _first = new Rows[NUM_ROW_FIRST];
    _economy = new Rows[NUM_ROW_ECO];
    _numEmptySeatFirst = NUM_ROW_FIRST * NUM_SEAT_FIRST;
    _numEmptySeatEco = NUM_ROW_ECO * NUM_SEAT_ECO;
  }

  /**
   * Initialize a class with dummy passengers.
   *
   * @param theClass an array of Rows objects representing a class
   * @param numRow   an int representing the number of rows in the class
   * @param numSeat  an int representing the number of seats in one row
   */
  private void createSubFlightClasses(Rows[] theClass, int numRow, int numSeat) {
    for (int i = 0; i < numRow; i++) {
      Passenger[] emptySeats = new Passenger[numSeat];
      for (int j = 0; j < numSeat; j++) {
        emptySeats[j] = new Passenger();
      }
      theClass[i] = new Rows(emptySeats);
    }
  }

  /**
   * Initialize both classes with dummy passengers.
   */
  public void createFlightClasses() {
    createSubFlightClasses(_first, NUM_ROW_FIRST, NUM_SEAT_FIRST);
    createSubFlightClasses(_economy, NUM_ROW_ECO, NUM_SEAT_ECO);
  }

  /**
   * Print the availability chart of a class.
   *
   * @param theClass an array of Rows objects representing a class
   * @param numRow   an int representing the number of rows in the class
   * @param numSeat  an int representing the number of seats in one row
   * @param gap      an int representing 1 or 10 for the sake of printing understandable seat
   *                 letter
   */
  private void printSubsetAvailabilityChart(Rows[] theClass, int numRow, int numSeat, int gap) {
    int i = 0;

    while (i < numRow) {
      if (theClass[i].getNumRowPassenger() != numSeat) {
        System.out.print(" " + (i + gap) + ":");
        int k = 0;
        while (k < numSeat) {
          if (!(theClass[i].getListRowPassenger())[k].exist()) {
            System.out.print("-" + (char) ('A' + k));
          }
          k++;
        }
      }
      i++;
    }
  }

  /**
   * Print the availability chart of both classes.
   */
  public void printAvailabilityChart() {
    System.out.print("Availability List:");
    if (_numEmptySeatFirst != 0) {
      System.out.print("\nFirst: ");
      printSubsetAvailabilityChart(_first, NUM_ROW_FIRST, NUM_SEAT_FIRST, GAP_FIRST);
    }
    if (_numEmptySeatEco != 0) {
      System.out.print("\nEconomy: ");
      printSubsetAvailabilityChart(_economy, NUM_ROW_ECO, NUM_SEAT_ECO, GAP_ECO);
    }
    System.out.print("\n");
  }

  /**
   * Print the manifest chart of a class.
   *
   * @param theClass an array of Rows objects representing a class
   * @param numRow   an int representing the number of rows in the class
   * @param numSeat  an int representing the number of seats in one row
   * @param gap      an int representing 1 or 10 for the sake of printing understandable seat
   *                 letter
   */
  private void printSubsetManifest(Rows[] theClass, int numRow, int numSeat, int gap) {
    int i = 0;
    while (i < numRow) {
      if (theClass[i].getNumRowPassenger() != 0) {
        int k = 0;
        while (k < numSeat) {
          if ((theClass[i].getListRowPassenger())[k].exist()) {
            System.out.print(" " + (i + gap));
            System.out.print((char) ('A' + k) + ": ");
            System.out.print((theClass[i].getListRowPassenger())[k].getPassengerName());
          }
          k++;
        }
      }
      i++;
    }
  }

  /**
   * Print the manifest chart of both classes.
   */
  public void printManifest() {
    System.out.print("Manifest List:");
    if (_numEmptySeatFirst != NUM_ROW_FIRST * NUM_SEAT_FIRST) {
      System.out.print("\nFirst: ");
      printSubsetManifest(_first, NUM_ROW_FIRST, NUM_SEAT_FIRST, GAP_FIRST);
    }
    if (_numEmptySeatEco != NUM_ROW_ECO * NUM_SEAT_ECO) {
      System.out.print("\nEconomy: ");
      printSubsetManifest(_economy, NUM_ROW_ECO, NUM_SEAT_ECO, GAP_ECO);
    }
    System.out.print("\n");
  }

  /**
   * Check an empty seat in 2 specifics seats of a row in a class.
   *
   * @param theClass  an array of Rows objects representing a class
   * @param numRow    an int representing the number of rows in the class
   * @param firstInt  an int representing the indice of a seat
   * @param secondInt an int representing the indice of a seat
   * @param &         @return trace an array of int keeping track of the corresponding seat and row
   */
  private int[] checkSubPreferredSeat(Rows[] theClass, int numRow, int firstInt, int secondInt, int[] trace) {
    int i = 0;
    while (i < numRow && trace[0] == ERROR_SEAT) {
      if (!(((theClass[i].getListRowPassenger())[firstInt]).exist())) {
        trace[0] = i;
        trace[1] = firstInt;
      }

      if (!(((theClass[i].getListRowPassenger())[secondInt]).exist()) && trace[0] == ERROR_SEAT) {
        trace[0] = i;
        trace[1] = secondInt;
      }
      i++;
    }
    return trace;
  }

  /**
   * Find an empty seat with the preferred seat choice in a class.
   *
   * @param seat     a String of the user's choice
   * @param theClass an array of Rows objects representing a class
   * @param numRow   an int representing the number of rows in the class
   * @param numSeat  an int representing the number of seats in one row
   * @return exactEmptySeat an array of int keeping track of the corresponding seat and row
   */
  private int[] checkPreferredSeat(String seat, Rows[] theClass, int numRow, int numSeat) {
    int[] exactEmptySeat = {ERROR_SEAT, ERROR_SEAT};

    if ((seat.toUpperCase()).equals("W")) {
      exactEmptySeat = checkSubPreferredSeat(theClass, numRow, 0, numSeat - 1, exactEmptySeat);
    }

    if ((seat.toUpperCase()).equals("A")) {
      exactEmptySeat = checkSubPreferredSeat(theClass, numRow, (numSeat / 2) - 1, numSeat / 2, exactEmptySeat);
    }

    if ((seat.toUpperCase()).equals("C")) {
      exactEmptySeat = checkSubPreferredSeat(theClass, numRow, 1, numSeat - 2, exactEmptySeat);
    }
    return exactEmptySeat;
  }

  /**
   * Add a passenger in a corresponding spot otherwise print an error message.
   *
   * @param name         a string representing the passenger's name
   * @param theClass     an array of Rows objects representing a class
   * @param seats        an array of int representing the exact seat where to make the reservation
   * @param numEmptySeat an int representing the number of empty seats in the row
   * @param gap          an int representing 1 or 10 for the sake of printing understandable seat
   *                     letter
   * @return numEmptySeat an int representing the number of empty seats in the row
   */
  private int addSubPassenger(String name, Rows[] theClass, int[] seats, int numEmptySeat, int gap) {
    if (seats[0] == ERROR_SEAT) {
      System.out.println("Request Failed");
    }
    if (seats[0] != ERROR_SEAT) {
      theClass[seats[0]].getListRowPassenger()[seats[1]] = new Passenger(name, "NONE");
      theClass[seats[0]].incrementNumRowPassenger();
      numEmptySeat--;
      System.out.print(name + " has been seated in the seat " + (seats[0] + gap));
      System.out.print((char) ('A' + (seats[1])) + "\n");
    }
    return numEmptySeat;
  }

  /**
   * Add a passenger in either class.
   *
   * @param name     a string representing the passenger's name
   * @param theClass a string holding the user's choice
   * @param seat     a string holding the user's choice
   */
  public void addPassenger(String name, String theClass, String seat) {
    int[] exactSeat;

    if ((theClass.toUpperCase()).equals("FIRST")) {
      exactSeat = checkPreferredSeat(seat, _first, NUM_ROW_FIRST, NUM_SEAT_FIRST);
      _numEmptySeatFirst = addSubPassenger(name, _first, exactSeat, _numEmptySeatFirst, GAP_FIRST);
    } else if (((theClass.toUpperCase())).equals("ECONOMY")) {
      exactSeat = checkPreferredSeat(seat, _economy, NUM_ROW_ECO, NUM_SEAT_ECO);
      _numEmptySeatEco = addSubPassenger(name, _economy, exactSeat, _numEmptySeatEco, GAP_ECO);
    } else {
      System.out.println("Request Failed");
    }
  }

  /**
   * Add a group of passengers in a class otherwise print an error message.
   *
   * @param group        a string representing the group's name
   * @param theClass     an array of Rows objects representing a class
   * @param names        an ArrayList of string representing the passengers name
   * @param numRow       an int representing the number of rows in the class
   * @param numSeat      an int representing the number of seats in one row
   * @param gap          an int representing 1 or 10 for the sake of printing understandable seat
   *                     letter
   * @param numEmptySeat an int representing the number of empty seats in the row
   * @return numEmptySeat an int representing the number of empty seats in the row
   */
  private int addSubGroup(String group, Rows[] theClass, ArrayList<String> names, int numRow,
                          int numSeat, int gap, int numEmptySeat) {
    int groupSize = names.size();
    ArrayList<Integer> theSeats = new ArrayList<Integer>();

    if (numEmptySeat < groupSize) {
      System.out.println("Insufficient space to seat all members of the group");
    } else {
      int numEmpty = 0;
      while (!names.isEmpty()) {
        int i = 0;
        while (i < numRow && !names.isEmpty()) {
          if (theClass[i].getNumRowPassenger() == numEmpty || theClass[i].getNumRowPassenger() == names.size()) {
            int j = 0;
            while (j < numSeat && !names.isEmpty()) {
              if (!(theClass[i].getListRowPassenger()[j].exist())) {
                theClass[i].getListRowPassenger()[j] = new Passenger(names.remove(0), group);
                theClass[i].incrementNumRowPassenger();
                numEmptySeat--;
                theSeats.add(i);
                theSeats.add(j);
              }
              j++;
            }
          }
          i++;
        }
        numEmpty++;
      }
      int k = 0;
      System.out.print("The passengers have been seated in the seats: ");
      while (k < theSeats.size()) {
        System.out.print(" " + (theSeats.get(k) + gap));
        System.out.print((char) ('A' + theSeats.get(k + 1)));
        k = k + 2;
      }
      System.out.print("\n");
    }
    return numEmptySeat;
  }

  /**
   * Add a group of passengers in either class.
   *
   * @param group    a string representing the group's name
   * @param theClass a string holding the user's choice
   * @param names    an ArrayList of string representing the passengers name
   */
  public void addGroup(String group, String theClass, ArrayList<String> names) {

    if ((theClass.toUpperCase()).equals("FIRST")) {
      _numEmptySeatFirst = addSubGroup(group, _first, names, NUM_ROW_FIRST, NUM_SEAT_FIRST, GAP_FIRST, _numEmptySeatFirst);
    } else if ((theClass.toUpperCase()).equals("ECONOMY")) {
      _numEmptySeatEco = addSubGroup(group, _economy, names, NUM_ROW_ECO, NUM_SEAT_ECO, GAP_ECO, _numEmptySeatEco);
    } else {
      System.out.println("Request Failed");
    }
  }

  /**
   * Remove a passenger in a corresponding class.
   *
   * @param name         a string representing the passenger's name
   * @param theClass     an array of Rows objects representing a class
   * @param numRow       an int representing the number of rows in the class
   * @param numSeat      an int representing the number of seats in one row
   * @param numEmptySeat an int representing the number of empty seats in the row
   * @return numEmptySeat an int representing the number of empty seats in the row
   */
  private int removeSubPassenger(String name, Rows[] theClass, int numRow, int numSeat, int numEmptySeat) {
    int i = 0;
    boolean found = false;

    while (i < numRow && !found) {
      int k = 0;
      while (k < numSeat && !found) {
        if (theClass[i].getListRowPassenger()[k].exist()) {
          if ((theClass[i].getListRowPassenger()[k].getPassengerName().toUpperCase().equals(name.toUpperCase()))) {
            theClass[i].getListRowPassenger()[k] = new Passenger();
            theClass[i].decrementNumRowPassenger();
            numEmptySeat++;
            found = true;
          }
        }
        k++;
      }
      i++;
    }
    return numEmptySeat;
  }

  /**
   * Remove a passenger from the flight by looking in both classes until found.
   *
   * @param name a string representing the passenger's name
   */
  public void removePassenger(String name) {
    int witness = _numEmptySeatFirst;

    _numEmptySeatFirst = removeSubPassenger(name, _first, NUM_ROW_FIRST, NUM_SEAT_FIRST, _numEmptySeatFirst);

    if (witness == _numEmptySeatFirst) {
      _numEmptySeatEco = removeSubPassenger(name, _economy, NUM_ROW_ECO, NUM_SEAT_ECO, _numEmptySeatEco);
    }
  }

  /**
   * Remove a group of passengers in a class.
   *
   * @param group        a string representing the group's name
   * @param theClass     an array of Rows objects representing a class
   * @param numRow       an int representing the number of rows in the class
   * @param numSeat      an int representing the number of seats in one row
   * @param numEmptySeat an int representing the number of empty seats in the row
   * @return numEmptySeat an int representing the number of empty seats in the row
   */
  private int removeSubGroup(String group, Rows[] theClass, int numRow, int numSeat, int numEmptySeat) {
    int i = 0;

    while (i < numRow) {
      int k = 0;
      while (k < numSeat) {
        if (theClass[i].getListRowPassenger()[k].exist()) {
          if ((theClass[i].getListRowPassenger()[k].getPassengerGroup().toUpperCase().equals(group.toUpperCase()))) {
            theClass[i].getListRowPassenger()[k] = new Passenger();
            theClass[i].decrementNumRowPassenger();
            numEmptySeat++;
          }
        }
        k++;
      }
      i++;
    }
    return numEmptySeat;
  }

  /**
   * Remove a group of passengers from the flight by looking in both classes until found.
   *
   * @param group a string representing the group's name
   */
  public void removeGroup(String group) {
    int witness = _numEmptySeatFirst;

    _numEmptySeatFirst = removeSubGroup(group, _first, NUM_ROW_FIRST, NUM_SEAT_FIRST, _numEmptySeatFirst);

    if (witness == _numEmptySeatFirst) {
      _numEmptySeatEco = removeSubGroup(group, _economy, NUM_ROW_ECO, NUM_SEAT_ECO, _numEmptySeatEco);
    }
  }

  /**
   * Store the actual reservations information from one class into a text file.
   *
   * @param theClass  an array of Rows objects representing a class
   * @param numRow    an int representing the number of rows in the class
   * @param numSeat   an int representing the number of seats in one row
   * @param aClass    a String representing a class
   * @param theWriter a PrintWriter object
   */
  private void storeSubData(Rows[] theClass, int numRow, int numSeat, String aClass, PrintWriter theWriter) {
    int i = 0;
    while (i < numRow) {
      if (theClass[i].getNumRowPassenger() != 0) {
        int k = 0;
        while (k < numSeat) {
          if ((theClass[i].getListRowPassenger())[k].exist()) {
            theWriter.println(aClass);
            theWriter.println(i);
            theWriter.println(k);
            theWriter.println((theClass[i].getListRowPassenger())[k].getPassengerName());
            theWriter.println((theClass[i].getListRowPassenger())[k].getPassengerGroup());
          }
          k++;
        }
      }
      i++;
    }
  }

  /**
   * Store the actual flight reservations information into a text file.
   *
   * @param theWriter a PrintWriter object
   */
  public void storeData(PrintWriter theWriter) {
    storeSubData(_first, NUM_ROW_FIRST, NUM_SEAT_FIRST, "first", theWriter);
    storeSubData(_economy, NUM_ROW_ECO, NUM_SEAT_ECO, "economy", theWriter);
  }

  /**
   * Retrieve the previous reservations information from one class.
   *
   * @param br       a BufferedReader object
   * @param theClass an array of Rows objects representing a class
   */
  private void retrieveSubData(BufferedReader br, Rows[] theClass) throws IOException {
    int row;
    int seat;
    String name;
    String group;

    row = Integer.parseInt(br.readLine());
    seat = Integer.parseInt(br.readLine());
    name = br.readLine();
    group = br.readLine();

    theClass[row].getListRowPassenger()[seat] = new Passenger(name, group);
    theClass[row].incrementNumRowPassenger();
  }

  /**
   * Retrieve the previous flight reservations information.
   *
   * @param br a BufferedReader object
   */
  public void retrieveData(BufferedReader br) throws IOException {
    String line = br.readLine();
    if (line != null) {
      do {
        if ((line.toUpperCase()).equals("FIRST")) {
          retrieveSubData(br, _first);
          _numEmptySeatFirst--;
        } else if ((line.toUpperCase()).equals("ECONOMY")) {
          retrieveSubData(br, _economy);
          _numEmptySeatEco--;
        }
      } while ((line = br.readLine()) != null);
    }
  }
}