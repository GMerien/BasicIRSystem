package com.gmerien.app;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App 
{
    public static void main( String[] args )
    {
        String qPath = "";
        String dPath = "";
        String df = "";
        String rPath = "";
        String run = "";

        int i = 0;

        while(i < args.length){
            if(args[i].compareTo("-q") == 0){
                qPath = new String(args[i+1]);
            }
            else if(args[i].compareTo("-d") == 0){
                dPath = new String(args[i+1]);
            }
            else if(args[i].compareTo("-df") == 0){
                df = new String(args[i+1]);
            }
            else if(args[i].compareTo("-r") == 0){
                run = new String(args[i+1]);
            }
            else if(args[i].compareTo("-o") == 0){
                rPath = new String(args[i+1]);
            }

            i ++;
        }

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        Run.exec(qPath, dPath, df, rPath, run);
    }
}
