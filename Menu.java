/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A passenger reservation.
 */
public class Menu {

  private Scanner _input = new Scanner(System.in);

  /**
   * Display the menu and interact with the user input by using FlightClasses methods.
   *
   * @param theFlight a FlightClasses object
   */
  public void display(FlightClasses theFlight) throws IOException {

    System.out.println(
        "************************************\n" +
            "Add [P]assenger\n" +
            "Add [G]roup\n" +
            "[C]ancel Reservations\n" +
            "Print Seating [A]vailability Chart\n" +
            "Print [M]anifest\n" +
            "[Q]uit\n" +
            "************************************\n"
    );

    String name;
    String theClass;
    String seat;
    String group;
    String longListNames;
    String cancelChoice;

    char selection;

    do {

      do {
        selection = _input.nextLine().charAt(0);
        if (selection != 'p' && selection != 'P' && selection != 'g' && selection != 'G' &&
            selection != 'c' && selection != 'C' && selection != 'a' && selection != 'A' &&
            selection != 'm' && selection != 'M' && selection != 'q' && selection != 'Q')
          System.out.println("Please enter a valid choice (P, G, C, A, M or Q): ");
      } while (selection != 'p' && selection != 'P' && selection != 'g' && selection != 'G' &&
          selection != 'c' && selection != 'C' && selection != 'a' && selection != 'A' &&
          selection != 'm' && selection != 'M' && selection != 'q' && selection != 'Q');

      switch (selection) {
        case 'p':
        case 'P':
          System.out.print("Name: ");
          name = _input.nextLine();
          System.out.print("Service Class: ");
          theClass = _input.nextLine();
          System.out.print("Seat Preference: ");
          seat = _input.nextLine();
          theFlight.addPassenger(name, theClass, seat);
          break;
        case 'g':
        case 'G':
          ArrayList<String> theGroupList = new ArrayList<String>();
          System.out.print("Group Name: ");
          group = _input.nextLine();
          System.out.print("Names: ");
          longListNames = _input.nextLine();
          System.out.print("Service Class: ");
          theClass = _input.nextLine();

          Pattern pat = Pattern.compile("[,]");
          String strs[] = pat.split(longListNames);
          for (String str : strs) {
            theGroupList.add(str);
          }
          theFlight.addGroup(group, theClass, theGroupList);
          break;
        case 'c':
        case 'C':
          System.out.println("Is the cancellation for an [i]ndividual or a [g]roup?");
          cancelChoice = _input.nextLine();
          if (cancelChoice.equals("i") || cancelChoice.equals("I")) {
            System.out.print("Names: ");
            name = _input.nextLine();
            theFlight.removePassenger(name);
          } else if (cancelChoice.equals("g") || cancelChoice.equals("G")) {
            System.out.print("Group Name: ");
            group = _input.nextLine();
            if ((group.toUpperCase()).equals("NONE"))
              System.out.println("Request Failed");
            else
              theFlight.removeGroup(group);
          } else
            System.out.println("Request Failed");
          break;
        case 'a':
        case 'A':
          theFlight.printAvailabilityChart();
          break;
        case 'm':
        case 'M':
          theFlight.printManifest();
          break;
      }
    } while (selection != 'q' && selection != 'Q');
  }
}
