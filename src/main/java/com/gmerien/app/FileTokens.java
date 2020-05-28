package com.gmerien.app;

import java.util.ArrayList;

public class FileTokens {
    protected Integer id;
    protected ArrayList<DocTokens> documents;

    public FileTokens(FileText file){
        ArrayList<DocText> tmp = file.getDocuments();
        documents = new ArrayList<DocTokens>();

        id = file.getId();

        for(int i = 0; i < tmp.size(); i ++) {
            documents.add(new DocTokens(tmp.get(i)));
        }
    }
    
    public ArrayList<DocTokens> getDocuments(){
        
        return documents;
    }

    public void print(){
        System.out.println("\nFROM FILE ID : " + id);

        for(int i = 0; i < documents.size(); i ++){
            documents.get(i).print();
        }
    }
}