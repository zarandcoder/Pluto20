package de.hawlandshut.pluto20.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hawlandshut.pluto20.model.Post;

public class TestData {
    public static List<Post> postTestList = new ArrayList<Post>();
    public static String body = "Hello beautiful world. My name is Vadim Zaripov. I'm from MUC";

    public static List<Post> createTestData() {
        long time = new Date().getTime();

        postTestList.add(new Post("1","Author 1","Title 1", body, time++));
        postTestList.add(new Post("2","Author 2","Title 2", body, time++));
        postTestList.add(new Post("3","Author 3","Title 3", body, time++));
        postTestList.add(new Post("4","Author 4","Title 4", body, time++));
        postTestList.add(new Post("5","Author 5","Title 5", body, time++));
        postTestList.add(new Post("6","Author 6","Title 6", body, time++));

        return postTestList;
    }
}
