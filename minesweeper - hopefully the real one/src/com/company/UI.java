package com.company;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by user on 05.12.2016.
 */
public class UI {
    private static Scanner sc = new Scanner(System.in);

    public static void printField(List<List<FieldValue>> arr) {
        //print
        System.out.print("___");
        IntStream.range(0,arr.get(0).size()).forEach(num->System.out.print(" "+num+(((""+num).length()==1)?" ":"")+"|"));
        System.out.println();

        IntStream.range(0, arr.size()).forEach(i -> {
            System.out.print(i+(((""+i).length()==1)?" ":"") + "| ");
            arr.get(i).forEach(v -> {
                if (v.status() == Status.DEFAULT) System.out.print("~");
                else if (v.status() == Status.FLAGGED) System.out.print("F");
                else if (v.status() == Status.QUESTIONED) System.out.print("?");
                else if (v.status() == Status.REVEALED) System.out.print(v.getVal());
                System.out.print(" | ");
            });
            System.out.println();
        });
    }

    public static void printRevealedField(List<List<FieldValue>> arr){
        System.out.print("___");
        IntStream.range(0,arr.get(0).size()).forEach(num->System.out.print(" "+num+(((""+num).length()==1)?" ":"")+"|"));
        System.out.println();

        IntStream.range(0, arr.size()).forEach(i -> {
            System.out.print(i+(((""+i).length()==1)?" ":"") + "| ");
            arr.get(i).forEach(v -> {
                if(v.getVal()==-1) System.out.print("B");
                else if(v.status()==Status.REVEALED) System.out.print(v.getVal());
                else System.out.print("~");
                System.out.print(" | ");
            });
            System.out.println();
        });
    }

    public static String askForLoc() {
        System.out.println("Please insert command (You can always type 'help' or '?' to display help)");
        return sc.nextLine();
    }

    public static void printHelp() {
        System.out.println("HELP: The proper format is <loc1> <loc2> <cmd> \n List of commands: \n 'r' or 'reveal' for checking the index \n 'q' or 'question' for putting a question mark \n 'f' or 'flag' for flagging the index as probable mine");
    }

    public static void reportFlaggedIndex() {
        System.out.println("Location inserted is flagged, unflag the location to reveal it.");
    }

    public static void reportMineExplosion() {
        System.out.println("A mine has exploded, you lost :(");
    }

    public static void reportAlreadyRevealed() {
        System.out.println("This location is already revealed :)");
    }

    public static void reportWrongFormat() {
        System.out.println("Wrong format of command! :(");
        printHelp();
    }

    public static void reportWin() {
        System.out.println("You won !! congratz");
    }
}