package uts.wsd;

import java.util.*;
import java.io.*;
import javax.xml.bind.*;

public class TestJAXB implements Serializable {

    public static void main(String[] args) throws Exception {
        String s = "t";
        switch (s) {
            case "m":
                System.out.println("m");
                break;
            case "t":
                System.out.println("t");
                break;
        }
    }

}
