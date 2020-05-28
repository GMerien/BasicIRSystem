package com.gmerien.app;

import java.util.ArrayList;

public class DocText {
    protected static Integer num = 0;

    protected Integer id;
    protected String docId;
    protected ArrayList<String> paragraphs;

    public DocText(){
        paragraphs = new ArrayList<String>();
        id = num;
        docId = "";

        num ++;
    }

    public void add(String str){
        paragraphs.add(str);
    }

    public void setId(String id){
        docId = id;
    }

    public int size() {

        return paragraphs.size();
    }

    public ArrayList<String> getParagraphs(){

        return paragraphs;
    }

    public Integer getId(){

        return id;
    }

    public String getDocId(){

        return docId;
    }

    public void print(){
        System.out.println("\t|-------------------------------------|");
        System.out.println("\t| Document with id : " + id);
        System.out.println("\t|        and DocId : " + docId);
        System.out.println("\t|-------------------------------------|");

        for (int i = 0; i < paragraphs.size(); i++){
            System.out.println(paragraphs.get(i));
        }
    }
}