package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Context;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCallback;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class Data {

    public static List<Confrence> confrences = new ArrayList<Confrence>();
    public static List<String> userSelectedTags = new ArrayList<String>();
    public static List<Tags> tags = new ArrayList<Tags>();
    public static List<Integer> userSelectedTalks = new ArrayList<>();

    public Data(){

}

    public static void initData(Context context){
/*
        InputStream inputStream;
        String data[];
        inputStream = context.getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String line;
            while((line = reader.readLine()) != null){
                data = line.split(",");
                try{
                    //Log.e("Data ", data[0] + data[1] + data[2] + data[3] + data[4] + data[5] + data[6]);
                    Confrence confrence = new Confrence(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
                    confrences.add(confrence);
                }catch (Exception e){
                    Log.e("Problem", e.toString());
                }
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Error!! cant read");
        }
*/


        try {

            InputStream ins = context.getResources().openRawResource(R.raw.data);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));


            String line = " ";
            String[] vals;
            while ((line = br.readLine()) != null) {
                vals = line.split(",");
                if (vals.length > 6) {
                    confrences.add(new Confrence(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5], vals[6]));
                }else{
                    System.out.println(line);
                }

            }
        }catch(Exception e){
            System.out.println(e);
        };


        IamAuthenticator authenticator = new IamAuthenticator(context.getString(R.string.natural_language_apikey));
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2020-01-10", authenticator);
        naturalLanguageUnderstanding.setServiceUrl(context.getString(R.string.natural_language_url));


        ConceptsOptions conceptsOptions = new ConceptsOptions.Builder()
                .build();

        CategoriesOptions categories= new CategoriesOptions.Builder()
                .build();

        Features features = new Features.Builder()
                .categories(categories)
                .concepts(conceptsOptions)
                .build();

        for(int i = 0; i < confrences.size(); i++){
            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(confrences.get(i).description)
                    .features(features)
                    .build();

            final ArrayList<String> tag = new ArrayList<String>();
            final ArrayList<Double> score = new ArrayList<Double>();
            final ArrayList<String> url = new ArrayList<String>();
            final ArrayList<String> relavantTitle = new ArrayList<String>();

            naturalLanguageUnderstanding.analyze(parameters).enqueue(new ServiceCallback<AnalysisResults>() {
                @Override
                public void onResponse(Response<AnalysisResults> response) {

                    for(int j = 0; j < response.getResult().getCategories().size(); j++){
                        //System.out.println("Label: " + response.getResult().getCategories().get(j).getLabel() + " Score: " + response.getResult().getCategories().get(j).getScore());
                        String[] temp = response.getResult().getCategories().get(j).getLabel().split("/");
                        for(int a = 1; a < temp.length; a++){
                            double s = response.getResult().getCategories().get(j).getScore();
                            if(s <= 90) {
                                tag.add(temp[a]);
                                score.add(response.getResult().getCategories().get(j).getScore());
                            }
                        }
                        //tag.add(response.getResult().getCategories().get(j).getLabel());
                        //score.add(response.getResult().getCategories().get(j).getScore());
                    }

                    for(int j = 0; j < response.getResult().getConcepts().size(); j++){
                        //System.out.println("Topic " + response.getResult().getConcepts().get(j).getText() + " Relevant url " + response.getResult().getConcepts().get(j).getDbpediaResource() + " Relevance: " + response.getResult().getConcepts().get(j).getRelevance());
                        url.add(response.getResult().getConcepts().get(j).getDbpediaResource());
                        relavantTitle.add(response.getResult().getConcepts().get(j).getText());
                    }
                }
                @Override
                public void onFailure(Exception e){
                    System.out.println(e);
                }
            });

            Tags t = new Tags(tag,score,url,relavantTitle);
            tags.add(t);
        }

        System.out.println("done !!!!!");
    }

    public static ArrayList<String> getUniqueTags() {
        ArrayList<String> uniqueTagNames = new ArrayList<>();
        ArrayList<String> allTagNames = new ArrayList<>();
        while(tags.size()<confrences.size()){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
        for(int i = 0; i < tags.size(); i++){
            for(int j = 0; j < tags.get(i).tags.size(); j++) {
                allTagNames.add(tags.get(i).tags.get(j));
            }
        }

        for(int i = 0; i < allTagNames.size(); i++){
            if(!(uniqueTagNames.contains(allTagNames.get(i)))){
                uniqueTagNames.add(allTagNames.get(i));
            }
        }

        Collections.sort(uniqueTagNames);
        return uniqueTagNames;
    }


    public static ArrayList<Integer> getTalksIndexUsingTags(ArrayList<String> options){
        ArrayList<Integer> talks = new ArrayList<>();
        for (int i = 0; i < options.size(); i++){
            for(int j = 0; j < tags.size(); j++){
                for(int k = 0; k < tags.get(j).tags.size(); k++){
                    if(options.get(i).equalsIgnoreCase(tags.get(j).tags.get(k)))
                    talks.add(j);
                }
            }
        }
        return talks;
    }

    public static boolean isConfrenceOverLapping(int index1, int index2){
        SimpleDateFormat sfmt = new SimpleDateFormat("E  MMM dd  yyyy - HH:mm:ss"); //format for start date
        SimpleDateFormat efmt = new SimpleDateFormat("E  MMM dd  yyyy HH:mm"); //format for end date
        Date sDate = null; // the date for the start of conf 2
        Date eDate = null; // the date for the end of conf 1
        try{
            sDate = sfmt.parse(confrences.get(index2).startTime);
            eDate = efmt.parse(confrences.get(index1).endTime);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(eDate.after(sDate)){
            return true;
        }
        return false;
    }
    

    public static String relavantReading(int index){
        String str = confrences.get(index).title + "\n";
        for(int i = 0; i < tags.get(index).url.size(); i++){
            str = str + "\n" + tags.get(index).relavantTitle.get(i) + " - " + tags.get(index).url.get(i) ;
        }
        return str;
    }

}
