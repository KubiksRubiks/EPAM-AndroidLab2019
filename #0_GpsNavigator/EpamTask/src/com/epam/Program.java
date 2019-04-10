package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.imp.Data;
import com.epam.imp.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static Graph.Edge GRAPH[];
    private static ArrayList<Data> data = new ArrayList<Data>();

    public static void main(String[] args) {
        final GpsNavigator navigator = new StubGpsNavigator();
        navigator.readData("D:\\Gps\\road_map.txt");

        dataToGraph(data);

        final Path path = navigator.findPath("A", "Metro");
        System.out.println(path);
    }

    private static void dataToGraph(ArrayList<Data> data) {
        GRAPH = new Graph.Edge[data.size()];
        for (int i = 0; i < data.size(); i++) {
            GRAPH[i] = new Graph.Edge(data.get(i).getStartPoint(), data.get(i).getEndPoint(), data.get(i).getLength());
        }
    }

    private static class StubGpsNavigator implements GpsNavigator {

        @Override
        public void readData(String filePath) {
            File file = new File(filePath);
            Scanner sc = null;

            try {
                sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    String[] items = line.split(" ");
                    data.add(new Data(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3])));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                sc.close();
            }
        }

        @Override
        public Path findPath(String pointA, String pointB) {

            Graph g = new Graph(GRAPH);
            g.dijkstra(pointA);
            g.getPath(pointB);

            ArrayList<String> path = g.getVertexList();
            int cost = 0;

            for (int i = 0; i < path.size() - 1; i++) {
                for (int j = 0; j < GRAPH.length; j++) {
                    if(path.get(i).equals(data.get(j).getStartPoint()) && path.get(i+1).equals(data.get(j).getEndPoint())){
                        cost += data.get(j).getLength() * data.get(j).getPrice();
                    }
                }
            }

            return new Path(path, cost);
        }
    }
}
