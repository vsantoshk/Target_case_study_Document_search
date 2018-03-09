package com.casestudy.documentsearch;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class StringMatchSearch implements Search {

    private Map<String, List<String>> filesMap;

    public StringMatchSearch(Map<String, List<String>> filesMap) {
        this.filesMap = filesMap;
    }

    @Override
    public SearchResult search(String fileName, String string) {
        return () -> filesMap.get(fileName).stream().mapToInt(line -> getNumberOfTimes(line, string)).sum();
    }

    private int getNumberOfTimes(String line, String textToSearch) {
        return StringUtils.countMatches(line, textToSearch);
    }

}
