package com.gmerien.app;

import java.util.ArrayList;

public class FileText {
    protected static Integer num = 0;

    protected Integer id;
    protected ArrayList<DocText> documents;

    public FileText(){
        documents = new ArrayList<DocText>();
        id = num;
        num ++;
    }

    public void add(DocText doc){
        documents.add(doc);
    }

    public ArrayList<DocText> getDocuments(){

        return documents;
    }

    public int size(){

        return documents.size();
    }

    public int getId(){

        return id;
    }

    public void print(){
        System.out.println("FILE WITH ID : " + id);
        
        for (int i = 0; i < documents.size(); i++){
            documents.get(i).print();
        }

    }

}