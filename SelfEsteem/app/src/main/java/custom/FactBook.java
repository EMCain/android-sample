package custom;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Emily on 5/9/2015.
 */
public class FactBook {
    private List<String> facts;
    private List<String> intros;

    private int mCurrentFact;

    public FactBook(String string){
        facts = new ArrayList<String>();
        intros = new ArrayList<String>();

        intros.add("I like my ");
        intros.add("I'm really happy about my ");
        intros.add("I am so glad to have my ");
        intros.add("I appreciate my ");

        if(string.length() > 0){
            facts = loadSaveString(string);
        }
    }

    public String getFacts() {
        String sentence;
        if(facts.size() == 0){
            sentence = "Please click New Fact to add something you like about yourself.";
        } else {

            //the button was clicked--update fact label with new fact.
            String fact;
            String intro;
            // randomly select a fact
            Random randomGenerator = new Random(); // Construct a new random number generator
            int randomNumber1 = randomGenerator.nextInt(facts.size());
            mCurrentFact = randomNumber1;
            //determine fact based on random number

            fact = facts.get(randomNumber1);

            int randomNumber2 = randomGenerator.nextInt(intros.size());

            intro = intros.get(randomNumber2);

            sentence = intro + fact + "!";
        }
        return sentence;
    }

    public void addFact(String string) {

        facts.add(string);

    }

    public void removeFact() {
        if(facts.size() != 0) {
            facts.remove(mCurrentFact);
        }
    }

    public List<String> getFactsList(){
        return facts;
    }

    public void giveFactsList(List<String> theList) {
        for (String string : theList){
            facts.add(string);
        }
    }


    public String createOutputString() {
        StringBuilder outputString = new StringBuilder();

        String entry = null;

        for(int i = 0; i < facts.size(); ++i) {
            entry = facts.get(i);
            outputString.append(entry);

            if(i != facts.size()-1) {
                outputString.append("||");
            }

//	if(outputString.length() >= whatever) {	}
        }
        return outputString.toString();
    }

    private String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }



    public void loadSaveFileString(){
        try {
            String string = readFile("save.txt");
            facts = Arrays.asList(string.split("\\|\\|"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadSaveString(String string){
            List<String> theList = Arrays.asList(string.split("\\|\\|"));
            return theList;
    }


}
