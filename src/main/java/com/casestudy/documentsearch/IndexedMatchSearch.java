package com.casestudy.documentsearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IndexedMatchSearch implements Search {

    private Map<String, Map<String, Long>> countMap;

    public IndexedMatchSearch(Map<String, List<String>> filesMap) {
        preProcessData(filesMap);
    }

    private void preProcessData(Map<String, List<String>> filesMap) {
        countMap = new HashMap<>();
        StringMatchSearch stringMatchSearch = new StringMatchSearch(filesMap);
        for (String fileName : filesMap.keySet()) {
            HashSet<HashSet<String>> uniqueStringsSet = filesMap.get(fileName).stream()
                    .map(line -> getStreamOfSplitStrings(line)).collect(Collectors.toCollection(HashSet::new));

            HashSet<String> uniqueStrings = new HashSet<>();
            for (HashSet<String> set : uniqueStringsSet)
                uniqueStrings.addAll(set);

            Map<String, Long> fileMap = new HashMap<>();
            for (String string : uniqueStrings) {
                fileMap.put(string, new Long(stringMatchSearch.search(fileName, string).getNumberOfMatches()));
            }
            countMap.put(fileName, fileMap);
        }
    }

    private HashSet<String> getStreamOfSplitStrings(String line) {
        return new HashSet<String>(Arrays.asList(line.split(" ")));
    }

    @Override
    public SearchResult search(String fileName, String string) {
        Long count = countMap.get(fileName).get(string);
        return () -> count == null ? 0 : count.longValue();
    }

}
