package com.gmerien.app;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.Math;

public class WeightMatrix{
    protected HashMap<String, Integer> terms;
    protected HashMap<String, Integer> documents;

    protected InvertedIndex invertedIndex;

    protected ArrayList<ArrayList<Double>> weights;

    protected Integer nTerms;
    protected Integer nDocuments;

    String documentsFolder;

    public WeightMatrix(String df){
        invertedIndex = new InvertedIndex();

        terms = new HashMap<String, Integer>();
        documents = new HashMap<String, Integer>();

        weights = null;

        nTerms = 0;
        nDocuments = 0;

        documentsFolder = df;
    }

    public void setTerms(Queries queries){
        ArrayList<String> currentQueryList;
        int index = 0;

        for (Map.Entry<String, Query> elem : queries.getMap().entrySet()){
            currentQueryList = ((Query)elem.getValue()).getQuery();

            for(int i = 0; i < currentQueryList.size(); i ++){
                if(!terms.containsKey(currentQueryList.get(i)))
                {
                    terms.put(currentQueryList.get(i), index);
                    index ++;
                }
            }
        }
    }

    public void setDocuments(String path, String df, Queries queries){
        HashMap<String, Integer> tmp;
        Integer index = 0;

        invertedIndex.openFilesList(path, df, terms);
        invertedIndex.addToInvertedIndex(queries, terms);

        tmp = new HashMap<String, Integer>(invertedIndex.getNbTokens());

        for(Map.Entry<String, Integer> c : tmp.entrySet()){
            documents.put(c.getKey(), index);
            index ++;
        }
    }

    public void initWeightMatrix(){
        ArrayList<Double> tmp;

        nTerms = terms.size();
        nDocuments = documents.size();

        weights = new ArrayList<ArrayList<Double>>(nTerms);

        for (int i = 0; i < nTerms; i ++) {
            tmp = new ArrayList<Double>(nDocuments);
            weights.add(tmp);
        }

        System.out.println("Weight Matrix, size terms = " + nTerms + ", size documents = " + nDocuments);
    }

    public void computeWeightMatrix(){
        double currentTF;
        double currentIDF;
        double currentTFIDF;

        double currentCount;
        double currentNb;

        Integer nb = 0;

        System.out.println("Computing weight matrix");

        for (Map.Entry<String, Integer> currentTerm : terms.entrySet()){
            
            for (Map.Entry<String, Integer> currentDoc : documents.entrySet()){
                currentCount = (double)invertedIndex.getCount(currentTerm.getKey(), currentDoc.getKey()); 
                
                if (currentCount > 0.1) {
                currentNb = (double)invertedIndex.getNbTokens().get(currentDoc.getKey());

                currentTF = currentCount/currentNb;
                currentIDF = Math.log10( (double)nDocuments / ( (double)invertedIndex.getCount(currentTerm.getKey()) + 1.0) );
                currentTFIDF = currentTF*currentIDF;
                
                weights.get(currentTerm.getValue()).add(currentDoc.getValue(), currentTFIDF);

                }
                else{
                    weights.get(currentTerm.getValue()).add(currentDoc.getValue(), 0.0);
                }
                
                nb ++;
            }
        }
    }

    public double computeSimilarity(String d1, String d2){
        Integer d1ID;
        Integer d2ID;

        if (documents.containsKey(d1)){
            if (documents.containsKey(d2)){
                d1ID = documents.get(d1);
                d2ID = documents.get(d2);

                return computeSimilarity(d1ID, d2ID);
            }
            else {
                System.out.println("The id " + d2 + " does not exist");
                return 0.0;
            }
        }
        else {
            System.out.println("The id " + d1 + " does not exist");
            return 0.0;
        }
    }

    public double computeSimilarity(Integer d1, Integer d2){
        double wiD1;
        double wiD2;
        double lenD1 = 0;
        double lenD2 = 0;
        double res = 0;

        for (int t = 0; t < weights.size(); t ++){
            wiD1 = weights.get(t).get(d1);
            wiD2 = weights.get(t).get(d2);

            lenD1 += wiD1 * wiD1;
            lenD2 += wiD2 * wiD2;

            res += wiD1 * wiD2;
        }

        res = res / (Math.sqrt(lenD1) * Math.sqrt(lenD2));

        return res;
    }

    public HashMap<String, Integer> getTerms(){
        return terms;
    }

    public InvertedIndex getInvertedIndex(){
        return invertedIndex;
    }

    public Integer getTermIndex(String term){

        return terms.get(term);
    }

    public String getTermString(int index){

        for (Map.Entry<String, Integer> c : terms.entrySet()){
            if (c.getValue() == index){
                return c.getKey();
            }
        }

        return "----";
    }

    public Integer getDocumentIndex(String document){

        return documents.get(document);
    }

    public HashMap<String, Integer> getDocuments(){

        return documents;
    }

    public ArrayList<ArrayList<Double>> getMatrix(){

        return weights;
    }

    public void printTerms(){
        System.out.println("Terms from queries :");

        for (Map.Entry<String, Integer> c : terms.entrySet()){
            System.out.println(c.getValue() + ": " + c.getKey());
        }
    }
}