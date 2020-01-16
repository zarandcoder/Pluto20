package de.hawlandshut.pluto20.test;

import java.util.ArrayList;
import java.util.List;

import de.hawlandshut.pluto20.model.Post;

public class TestData {

    public static List<Post> createTestdata() {
        List<Post> testList = new ArrayList<Post>();

        String body = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.";
        long time = new java.util.Date().getTime();

        testList.add( new Post("1", "Author 1", "Title 1", body, time++, "fbk"));
        testList.add( new Post("2", "Author 2", "Title 2", body, time++, "fbk"));
        testList.add( new Post("3", "Author 3", "Title 3", body, time++, "fbk"));
        testList.add( new Post("4", "Author 4", "Title 4", body, time++, "fbk"));


        return testList;
    }
}