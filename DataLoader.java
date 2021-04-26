import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        s= s.replace('\u00A0',' '); // remove non-breaking whitespace characters
        s= s.replace('\u2007',' ');
        s= s.replace('\u202F',' ');
        s= s.replace('\uFEFF',' ');

        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static String readFileAsString(String filepath) {
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        // Read File Content
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND: " + filepath);
            e.printStackTrace();
        }

        return content;
    }

    public static List<DataPoint> loadHeartDiseaseData(String filepath) {
        /* NOTE:  THIS IS ONLY FOR LOADING HEART DISEASE DATA; NOT FOR HAND-WRITTEN DIGIT RECOGNITION */
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
            // TODO: create DataPoint objects here!
        }

        return dataset;
    }

    public static void splitDataIntoTrainingAndTest(List<DataPoint> allData, List<DataPoint> emptyTrainingList, List<DataPoint> emptyTestList, double percentTraining) {
        Collections.shuffle(allData);   // This randomizes the order of allData

        // add percentTraining of the elements in allData to emptyTrainingList.
        // add all the rest of the items to emptyTestList

        // NOTE:  percentTraining is between 0 and 1, NOT 0 to 100%.
        // so, e.g., 0.6 represents 60%.

        int breakPoint = (int)(percentTraining * allData.size());
        for (int i = 0; i < breakPoint; i++) {
            emptyTrainingList.add(allData.get(i));
        }

        for (int i = breakPoint; i < allData.size(); i++) {
            emptyTestList.add(allData.get(i));
        }
    }

    public static List<DataPoint> loadMNistData(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
             /* TODO:
                For each line in the lines array:
                    split line by , to get all coordinates

                    get the correct label for this data point (the first number in the array).
                    create a double array for all of the other pixel values (starting at index 1)

                    Run the DataPoint constructor, giving it the correct label and the double[]

                    Add the DataPoint to your dataset list.
         */

             String[] currentLine = lines[a].split(",");
             String label = currentLine[0];
             double[] vals = new double[currentLine.length - 1];
             for (int i = 1; i < currentLine.length; i++) {
                 vals[i - 1] = Double.parseDouble(currentLine[i]);
             }

             dataset.add(new DataPoint(label, vals));


        }

        return dataset;
    }
}