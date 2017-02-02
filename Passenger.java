/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

/**
 * A passenger reservation.
 */
public class Passenger {

  private String _pName;
  private String _pGroup;
  private boolean _seat;

  /**
   * Constructor of a dummy passenger by default.
   */
  public Passenger() {
    this._seat = false;
  }

  /**
   * Construct a passenger with its name and group.
   *
   * @param pName  a String
   * @param pGroup a String
   */
  public Passenger(String pName, String pGroup) {
    this._pName = pName;
    this._pGroup = pGroup;
    this._seat = true;
  }

  /**
   * Get the passenger's name.
   *
   * @return passenger's name
   */
  public String getPassengerName() {
    return _pName;
  }

  /**
   * Get the name of passenger's group.
   *
   * @return the name of passenger's group
   */
  public String getPassengerGroup() {
    return _pGroup;
  }

  /**
   * Verifying if the actual passenger is dummy or not.
   *
   * @return _seat a boolean in order to know if the seat is occupied or not
   */
  public boolean exist() {
    return _seat;
  }
}
