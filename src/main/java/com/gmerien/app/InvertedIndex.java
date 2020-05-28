package com.gmerien.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class InvertedIndex{
    protected HashMap<String, HashMap<String, Integer>> invInd;

    protected HashMap<String, Integer> nbTokens;

    public InvertedIndex(){
        invInd = new HashMap<String, HashMap<String, Integer>>();
        nbTokens = new HashMap<String, Integer>();
    }

    public void addQueries(Queries queries){

    }


    public void openFilesList(String path, String df){
        ArrayList<String> filesPath = new ArrayList<String>();
        BufferedReader buff = null;
        String currentFilePath;

        try {
            buff = new BufferedReader(new FileReader(path));

            while ((currentFilePath = buff.readLine()) != null){
                filesPath.add(df + "/" + currentFilePath);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <  filesPath.size(); i ++){
            System.out.println("Adding file " + filesPath.get(i) + " to inverted index");
            XmlOpener opener = new XmlOpener(filesPath.get(i));

            addToInvertedIndex(new FileTokens(opener.getFileText()));
        }
    }

    public void openFilesList(String path, String df, HashMap<String, Integer> qTerms){
        ArrayList<String> filesPath = new ArrayList<String>();
        BufferedReader buff = null;
        String currentFilePath;

        try {
            buff = new BufferedReader(new FileReader(path));

            while ((currentFilePath = buff.readLine()) != null){
                filesPath.add(df + "/" + currentFilePath);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <  filesPath.size(); i ++){
            System.out.println("Adding file " + filesPath.get(i) + " to inverted index");
            XmlOpener opener = new XmlOpener(filesPath.get(i));

            addToInvertedIndex(new FileTokens(opener.getFileText()), qTerms);
        }
    }


    protected void addToInvertedIndex(FileTokens file){
        ArrayList<DocTokens> documents = file.getDocuments();

        for (int i = 0; i < documents.size(); i ++){
            addToInvertedIndex(documents.get(i));
        } 

    }

    protected void addToInvertedIndex(FileTokens file, HashMap<String, Integer> qTerms){
        ArrayList<DocTokens> documents = file.getDocuments();

        for (int i = 0; i < documents.size(); i ++){
            addToInvertedIndex(documents.get(i), qTerms);
        } 

    }

    protected void addToInvertedIndex(DocTokens doc){
        String id = doc.getDocId();
        ArrayList<String> tokens = doc.getTokens();
        String currentToken; 

        nbTokens.put(id, tokens.size());

        for (int i = 0; i < tokens.size(); i ++){
            currentToken = tokens.get(i);

            if (invInd.containsKey(currentToken)){
                if (invInd.get(currentToken).containsKey(id)){
                    int currentVal = invInd.get(currentToken).get(id);
                    invInd.get(currentToken).put(id, currentVal + 1); 
                }
                else {
                    invInd.get(currentToken).put(id, 1);
                }
            }
            else {
                invInd.put(currentToken, new HashMap<String, Integer>());
                invInd.get(currentToken).put(id, 1);
            }
        }
    }

    protected void addToInvertedIndex(DocTokens doc, HashMap<String, Integer> qTerms){
        String id = doc.getDocId();
        ArrayList<String> tokens = doc.getTokens();

        addToInvertedIndex(id, tokens, qTerms);
    }

    public void addToInvertedIndex(Queries queries, HashMap<String, Integer> qTerms){
        for (Map.Entry<String, Query> c : queries.getMap().entrySet()){

            addToInvertedIndex(c.getKey(), c.getValue(), qTerms);
        }
    }

    protected void addToInvertedIndex(String id, Query q, HashMap<String, Integer> qTerms){
        ArrayList<String> tokens = q.getQuery();
        
        addToInvertedIndex(id, tokens, qTerms);
    }

    protected void addToInvertedIndex(String id, ArrayList<String> tokens, HashMap<String, Integer> qTerms){
        String currentToken; 

        nbTokens.put(id, tokens.size());

        for (int i = 0; i < tokens.size(); i ++){
            currentToken = tokens.get(i);

            // if the current token is contained in the term list made with the queries
            if (qTerms.containsKey(currentToken)){
                if (invInd.containsKey(currentToken)){
                    if (invInd.get(currentToken).containsKey(id)){
                        int currentVal = invInd.get(currentToken).get(id);
                        invInd.get(currentToken).put(id, currentVal + 1); 
                    }
                    else {
                        invInd.get(currentToken).put(id, 1);
                    }
                }
                else {
                    invInd.put(currentToken, new HashMap<String, Integer>());
                    invInd.get(currentToken).put(id, 1);
                }
            }
        }
    }
    
    public Integer getCount(String token, String doc){
        if (invInd.containsKey(token)){
            if(invInd.get(token).containsKey(doc)){
                return invInd.get(token).get(doc);
            }
            return 0;
        }
        else{
            return 0;
        }
    }

    public Integer getCount(String token){
        if (invInd.containsKey(token)){
            return invInd.get(token).size();
        }
        else{
            return 0;
        }
    }

    public HashMap<String, Integer> getNbTokens(){

        return nbTokens;
    }

    public void printTokenIndex(String token){
        HashMap<String, Integer> current = new HashMap<String, Integer>(invInd.get(token));

        System.out.println(current);
    }

    public void printTokens(){
        for (Map.Entry<String, HashMap<String, Integer>> current : invInd.entrySet()){
            System.out.println(current.getKey());
        }
    }

    public int size(){

        return invInd.size();
    }
}