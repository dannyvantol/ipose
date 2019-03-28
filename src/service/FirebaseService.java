package service;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.stream.Collectors;

public class FirebaseService {

    public void saveHighscore(String groupName,String groupToken,int score,String playerName) throws Exception{
        Date date = new Date();
        String jsonString = "{ \"playerName\" : \""+playerName+"\" , \"score\" :  "+score+" , \"date\" : \""+date.toString()+"\" }";

        URL urlObject = new URL("https://ipose2019.firebaseio.com/achievements/"+groupName+".json?auth="+groupToken);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes());

        InputStream inputStream = connection.getInputStream();
    }

    public String getHighscores(String groupName) throws Exception{
        URL urlObject = new URL("https://ipose2019.firebaseio.com/achievements/"+groupName+".json");
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type","application/json");

        InputStream inputStream = connection.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());

    }

    public void saveAchievement(String groupName,String groupToken,String achievementName) throws Exception{
        Date date = new Date();
        String jsonString = "{ \"achievementName\" : \""+achievementName+"\" , \"date\" : \""+date.toString()+"\" }";

        URL urlObject = new URL("https://ipose2019.firebaseio.com/highscore/"+groupName+".json?auth="+groupToken);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes());

        InputStream inputStream = connection.getInputStream();
    }


}
