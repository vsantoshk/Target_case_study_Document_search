package com.casestudy.documentsearch;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionSearch implements Search {

    private Map<String, List<String>> filesMap;

    public RegularExpressionSearch(Map<String, List<String>> filesMap) {
        this.filesMap = filesMap;
    }

    @Override
    public SearchResult search(String fileName, String string) {
        return () -> filesMap.get(fileName).stream().mapToInt(line -> getNumberOfTimes(line, string)).sum();
    }

    private int getNumberOfTimes(String line, String textToSearch) {
        int count = 0;
        if (!line.isEmpty()) {
            Pattern pattern = Pattern.compile(textToSearch);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find())
                count++;
        }
        return count;
    }

}
