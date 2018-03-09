package com.casestudy.documentsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentSearch {

    public static void main(String[] args) {
        try {
            String folderPath = "/Users/a599423/Downloads/Target_case_study/DocumentSearch/src/main/java/com/casestudy/documentsearch/";
            File file1 = new File(folderPath + "/french_armed_forces.txt");
            String file1Name = file1.getName();
            File file2 = new File(folderPath + "/hitchhikers.txt");
            String file2Name = file2.getName();
            File file3 = new File(folderPath + "/warp_drive.txt");
            String file3Name = file3.getName();
 /*
            File file4 = new File(folderPath + "/2M.txt");
            String file4Name = file4.getName();
*/
            Map<String, List<String>> filesMap = new HashMap<>();
            filesMap.put(file1Name, Files.readAllLines(Paths.get(file1.getPath()), Charset.defaultCharset()));
            filesMap.put(file2Name, Files.readAllLines(Paths.get(file2.getPath()), Charset.defaultCharset()));
            filesMap.put(file3Name, Files.readAllLines(Paths.get(file3.getPath()), Charset.defaultCharset()));
  //          filesMap.put(file4Name, Files.readAllLines(Paths.get(file4.getPath()), Charset.defaultCharset()));

            String inputString = input();


            searchUsingStringMatch(filesMap, inputString);
            System.out.println();
            searchUsingRegExMatch(filesMap, inputString);
            System.out.println();
            searchUsingIndexMatch(filesMap, inputString);
            System.out.println();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void searchUsingStringMatch(Map<String, List<String>> filesMap, String searchString) {
        StringMatchSearch stringMatchSearch = new StringMatchSearch(filesMap);
        for (String file1Name : filesMap.keySet()) {
            long startTime = System.currentTimeMillis();
            long numberOfMatches = stringMatchSearch.search(file1Name, searchString).getNumberOfMatches();
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println(
                    file1Name + " - " + numberOfMatches + " matches  ( " + timeTaken + " ms using StringMatchSearch )");
        }
    }

    private static void searchUsingRegExMatch(Map<String, List<String>> filesMap, String searchString) {
        RegularExpressionSearch regularExpressionSearch = new RegularExpressionSearch(filesMap);
        for (String file1Name : filesMap.keySet()) {
            long startTime = System.currentTimeMillis();
            long numberOfMatches = regularExpressionSearch.search(file1Name, searchString).getNumberOfMatches();
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println(file1Name + " - " + numberOfMatches + " matches  ( " + timeTaken
                    + " ms using RegularExpressionSearch )");
        }
    }

    private static void searchUsingIndexMatch(Map<String, List<String>> filesMap, String searchString) {
        IndexedMatchSearch indexedMatchSearch = new IndexedMatchSearch(filesMap);
        for (String file1Name : filesMap.keySet()) {
            long startTime = System.currentTimeMillis();
            long numberOfMatches = indexedMatchSearch.search(file1Name, searchString).getNumberOfMatches();
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println(file1Name + " - " + numberOfMatches + " matches  ( " + timeTaken
                    + " ms using IndexedMatchSearch )");
        }
    }

    private static String input() throws IOException {
        System.out.println("Enter the search string");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        return inputString;
    }

}
