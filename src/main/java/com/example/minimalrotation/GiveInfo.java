package com.example.minimalrotation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.*;


public class GiveInfo extends Dijkstra{


    ArrayList<Position> positions = new ArrayList<>();
    java.util.List<List> routeFinder = new ArrayList<>();
    java.util.List<Position> Best = new ArrayList<>();
    java.util.List<Position> Option = new ArrayList<>();

    Position source;
    Position dest;

    public static String[] startStop = new String[2];
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField route1;
    @FXML
    private TextField route2;
    @FXML
    private TextField route3;
    @FXML
    private TextField bestTime;
    @FXML
    private TextField worstTime;
    @FXML
    private TextField extraTime;
    @FXML
    private TextField bestNum;
    @FXML
    private TextField worstNum;
    @FXML
    private TextField extraNum;


    @FXML
    protected void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("map-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getRootInfo(String[] ss) {
        startStop = ss;

        java.util.List<Integer> xs = new ArrayList<>();
        java.util.List<Integer> ys = new ArrayList<>();
        java.util.List<String> names = new ArrayList<>();

        String url = "jdbc:postgresql://localhost:5432/Map";
        String username = "postgres";
        String password = "Password1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM positions";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int x = resultSet.getInt("X");
                    int y = resultSet.getInt("Y");
                    String name = resultSet.getString("Name");
                    xs.add(x);
                    ys.add(y);
                    names.add(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(String name: names){
            System.out.println(name);
        }

        Position M3 = new Position(xs.get(0), ys.get(0), names.get(0));
        Position M2 = new Position(xs.get(1), ys.get(1), names.get(1));
        Position M1 = new Position(xs.get(2), ys.get(2), names.get(2));
        Position Security_Hut1 = new Position(xs.get(3), ys.get(3), names.get(3));
        Position Security_Hut2 = new Position(xs.get(4), ys.get(4), names.get(4));
        Position Military = new Position(xs.get(5), ys.get(5), names.get(5));
        Position Student_Life_Center = new Position(xs.get(6), ys.get(6), names.get(6));
        Position Stop1 = new Position(xs.get(7), ys.get(7), names.get(7));
        Position Hangar2 = new Position(xs.get(8), ys.get(8), names.get(8));
        Position T3 = new Position(xs.get(9), ys.get(9), names.get(9));
        Position T4 = new Position(xs.get(10), ys.get(10), names.get(10));
        Position BinBin = new Position(xs.get(11), ys.get(11), names.get(11));
        Position Security_Hut4 = new Position(xs.get(12), ys.get(12), names.get(12));
        Position Pool = new Position(xs.get(13), ys.get(13), names.get(13));
        Position T2 = new Position(xs.get(14), ys.get(14), names.get(14));
        Position Stop2 = new Position(xs.get(15), ys.get(15), names.get(15));
        Position T1 = new Position(xs.get(16), ys.get(16), names.get(16));
        Position Dining_Hall = new Position(xs.get(17), ys.get(17), names.get(17));
        Position Hangar1 = new Position(xs.get(18), ys.get(18), names.get(18));
        Position Security_Hut3 = new Position(xs.get(19), ys.get(19), names.get(19));


        positions.add(M1);
        positions.add(M2);
        positions.add(M3);
        positions.add(T1);
        positions.add(T2);
        positions.add(T3);
        positions.add(T4);
        positions.add(Hangar1);
        positions.add(Hangar2);
        positions.add(Security_Hut1);
        positions.add(Security_Hut2);
        positions.add(Security_Hut3);
        positions.add(Security_Hut4);
        positions.add(Stop1);
        positions.add(Stop2);
        positions.add(Military);
        positions.add(Student_Life_Center);
        positions.add(BinBin);
        positions.add(Pool);
        positions.add(Dining_Hall);

        for(Position p : positions){
            if(Objects.equals(p.name, ss[0]))
            {
                source = p;
            }
            else if(Objects.equals(p.name, ss[1]))
            {
                dest = p;
            }
        }
        // Source ve dest noktalari basari ile olustururldu!!

        String route = "";
        int counter = 0;
        double distance1 = 0;
        routeFinder = get_nodes(positions, source, dest);
        Best = routeFinder.get(0);
        Option = routeFinder.get(1);

        QueueQ q = new QueueQ();
        for (Position value : Best) {
            q.enqueue(value);
        }
        Position p;
        while ((p = q.dequeue()) != null){
            counter++;
            if(p.next == null){
                route = route.concat(p.getName());
            }
            else route = route.concat(p.getName() + " -> ");
        }
        int copy_counter = counter-1;
        for(int i = 0; i < copy_counter; i++){
            distance1 += Best.get(i).calculateDistance(Best.get(i+1));
        }
        route1.setText(route);
        bestNum.setText(String.valueOf(counter));
        bestTime.setText(String.valueOf(distance1));

        //Option
        int counter1 = 0;
        String route1 = "";
        double distance2 = 0;
        QueueQ q1 = new QueueQ();
        for (Position value : Option) {
            q1.enqueue(value);
        }
        Position p1;
        while ((p1 = q1.dequeue()) != null){
            counter1++;
            if(p1.next == null){
                route1 = route1.concat(p1.getName());
            }
            else route1 = route1.concat(p1.getName() + " -> ");
        }
        int copy_counter1 = counter1-1;
        for(int i = 0; i < copy_counter1; i++){
            distance2 += Option.get(i).calculateDistance(Option.get(i+1));
        }
        route2.setText(route1);
        worstNum.setText(String.valueOf(counter1));
        worstTime.setText(String.valueOf(distance2));



        double distance3 = source.calculateDistance(dest);
        route3.setText(source.getName() + " -> " + dest.getName());
        extraNum.setText(String.valueOf(2));
        extraTime.setText(String.valueOf(distance3));

    }
}
