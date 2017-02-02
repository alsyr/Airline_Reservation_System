/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

/**
 * Rows containing passengers.
 */
public class Rows {
  private Passenger[] _listRowPassenger;
  private int _numRowPassenger;

  /**
   * Constructor of row of passenger.
   *
   * @param listRowPassenger an array of Passenger
   */
  public Rows(Passenger[] listRowPassenger) {
    this._listRowPassenger = listRowPassenger;
    _numRowPassenger = 0;
  }

  /**
   * Get the passengers in a row.
   *
   * @return _listRowPassenger an array of Passenger
   */
  public Passenger[] getListRowPassenger() {
    return _listRowPassenger;
  }

  /**
   * Get the number of passengers in a row.
   *
   * @return _numRowPassenger an int
   */
  public int getNumRowPassenger() {
    return _numRowPassenger;
  }

  /**
   * Increment the number of passengers in a row.
   */
  public void incrementNumRowPassenger() {
    _numRowPassenger++;
  }

  /**
   * Decrement the number of passengers in a row.
   */
  public void decrementNumRowPassenger() {
    _numRowPassenger--;
  }
}
