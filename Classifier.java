import java.lang.reflect.Array;
import java.util.*;

public class Classifier {
    private ArrayList<DataPoint> trainingData;
    private int k;

    public Classifier(int k) {
        this.k = k;
        trainingData = new ArrayList<DataPoint>();
    }

    public void addTrainingData(List<DataPoint> points) {
        // TODO: add all points from input to the training data
        trainingData.addAll(points);
    }

    public void addTrainingData(DataPoint point) {
        // TODO: add all points from input to the training data
        trainingData.add(point);
    }

    public String classify(double[] featureVector) {
        if (trainingData.size() == 0) return "no training data";

        ArrayList<Double> distances = new ArrayList<>();
        DataPoint[] dataPoints = new DataPoint[this.k];

        for (DataPoint d : trainingData) {
            distances.add(similarity(featureVector, d.getData()));
        }

        Collections.sort(distances);


        for (int i = 0; i < this.k; i++) {
            for (int j = 0; j < trainingData.size(); j++) {
                if (distance(featureVector, trainingData.get(j).getData()) == distances.get(i)) {
                    dataPoints[i] = trainingData.get(j);
                }
            }

        }

        String label = "";
        int count = 0;
        for (int i = 0; i < dataPoints.length; i++) {
            String temp = dataPoints[i].getLabel();
            int tempCount = 0;

            for (int j = 0; j < dataPoints.length; j++) {
                if (dataPoints[j].getLabel().equals(temp)) {
                    tempCount++;

                    if (tempCount > count) {
                        label = temp;
                        count = tempCount;
                    }
                }
            }
        }

        return label;
    }




    public double distance(double[] d1, double[] d2) {
        // TODO:  Use the n-dimensional Euclidean distance formula to find the distance between d1 and d2
        long total = 0;
        for (int i = 0; i < d1.length; i++) {
            total += (d1[i] - d2[i]) * (d1[i] - d2[i]);
        }

        return total;
    }

    public double similarity(double[] d1, double[] d2) {
        long n = 0;
        for (int i = 0; i < d1.length; i++) {
            n += d1[i] * d2[i];
        }

        long d1D = 0;
        for (int i = 0; i < d1.length; i++) {
            d1D += d1[i] * d1[i];
        }

        long d2D = 0;
        for (int i = 0; i < d2.length; i++) {
            d2D += d2[i] * d2[i];
        }

        return n / (Math.pow(d1D, 0.5) * Math.pow(d2D, 0.5));
    }


    public void test(List<DataPoint> test) {
        ArrayList<DataPoint> correct = new ArrayList<>();
        ArrayList<DataPoint> wrong = new ArrayList<>();

        int i = 0;
        for (DataPoint p : test) {
            String predict = classify(p.getData());
            System.out.print("#" + i + " REAL:\t" + p.getLabel() + " predicted:\t" + predict);
            if (predict.equals(p.getLabel())) {
                correct.add(p);
                System.out.print(" Correct ");
            } else {
                wrong.add(p);
                System.out.print(" WRONG ");
            }

            i++;
            System.out.println(" % correct: " + ((double) correct.size() / i));
        }

        System.out.println(correct.size() + " correct out of " + test.size());
        System.out.println(wrong.size() + " wrong out of " + test.size());
        System.out.println("% Error: " + (double) wrong.size() / test.size());
    }
}


