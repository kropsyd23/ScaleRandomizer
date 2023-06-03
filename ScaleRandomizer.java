/*
   Sydney Kropf
   12/21/2022
   5/23/2023
   
   ScaleRandomizer will use nested arrays to allow you to select which types of scales
   you want to practice, then generate a randomized list of them.
*/

import java.util.Scanner;
import java.util.ArrayList;

public class ScaleRandomizer {
   // main() method
   public static void main(String[] args) {
      // 4D array containing information about scales
      /*
         info[] = scale type
         info[][] = type of information
         info[][][] = formal or shorthand
      */
      String[][][][] info = { { { { "Major" },
                                  { "maj" } },
                                { { null } },
                                { { "C", "G", "D", "A", "E", "B/Cb", "F#/Gb",
                                    "F", "Bb", "Eb", "Ab", "C#/Db" } } },
                              { { { "minor" },
                                  { "min" } },
                                { { "natural", "harmonic", "melodic" },
                                  { "nat", "har", "mel" } },
                                { { "A", "E", "B", "F", "C#", "G#/Ab", "D",
                                    "G", "C", "F", "A#/Bb", "D#/Eb" } } } };
      
      // get which scales user would like to practice
      Scanner scan = new Scanner(System.in);
      System.out.println("Would you like to practice: Major, Natural Minor, Harmonic Minor, and/or Melodic Minor?");
      String input = scan.nextLine().toLowerCase();
      
      // initialize ArrayLists that will hold unrandomized and randomized scales
      ArrayList<String> scalesTemp = new ArrayList<String>();
      ArrayList<String> scales = new ArrayList<String>();
      
      // add the complete scale family of each scale type specified to scalesTemp
      for (int c = 0; c < info.length; c++) {
         ArrayList<String> specifiedTypes = new ArrayList<String>();
         
         // check if category is present
         if (input.indexOf(info[c][0][1][0]) != -1) {
            // if so, check if there are types for that category
            if (info[c][1][0][0] != null) {
               // if so, add each type present to specifiedTypes
               for (int t = 0; t < info[c][1][1].length; t++) {
                  if (input.indexOf(info[c][1][1][t]) != -1) {
                     specifiedTypes.add(info[c][1][0][t]);
                  }
               }
            }
            
            // add each scale + type + key to unrandomized scalesTemp
            for (int k = 0; k < info[c][2][0].length; k++) {
               for (int t = 0; t < specifiedTypes.size() || t == 0; t++) {
                  String type = "";
                  if (specifiedTypes.size() > 0) {
                     type = " " + specifiedTypes.get(t);
                  }
                  scalesTemp.add(info[c][2][0][k] + type + " " + info[c][0][0][0]);
               }
            }
         }
      }
      
      // fill scales from randomly selecting from scalesTemp
      int numScales = scalesTemp.size();
      for (int i = 0; i < numScales; i++) {
         int rand = (int) (Math.random() * scalesTemp.size());
         scales.add(scalesTemp.get(rand));
         scalesTemp.remove(rand);
      }
      
      // print array
      int index = 0;
      while (index < scales.size() - 1) {
         System.out.println();
         int numChars = scales.get(index).length() + 2;
         while (numChars < 75 && index < scales.size() - 1) {
            System.out.print(scales.get(index) + ", ");
            numChars += scales.get(index + 1).length() + 2;
            index++;
         }
      }
      System.out.println(scales.get(index));
      scan.close();
   }
}