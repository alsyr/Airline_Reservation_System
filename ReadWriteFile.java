/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Read and write into a file text.
 */
public class ReadWriteFile {

  private String _fileName;

  /**
   * Constructor of ReadWriteFile.
   *
   * @param fileName a String corresponding to the file directory
   */
  public ReadWriteFile(String fileName) {
    this._fileName = fileName;
  }

  /**
   * Read a file if it exists or create one. And then store the content into a FlightClasses object
   *
   * @param theFlight a FlightClasses object
   */
  public FlightClasses readFile(FlightClasses theFlight) throws IOException {
    File theFile = new File(_fileName);
    if (!theFile.exists()) {
      try {
        theFile.createNewFile();
      } catch (IOException ex) {
        Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      FileInputStream fis = new FileInputStream(theFile);

      //Construct BufferedReader from InputStreamReader
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));

      theFlight.retrieveData(br);

      br.close();
    }
    //FileOutPutStream oFile = new FileOutputStream(theFile, false);

    return theFlight;
  }

  /**
   * Store the actual reservations information into the file.
   *
   * @param theFlight a FlightClasses object
   */
  public void writeFile(FlightClasses theFlight) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(_fileName);

    theFlight.storeData(writer);

    writer.close();
  }
}
