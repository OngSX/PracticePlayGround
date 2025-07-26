package org.interviewQuestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class KeywordExtractor {

    public static Map<String, Integer> getTop10Keywords(String filePath) throws IOException {
        // Validate file
        if (filePath == null || !filePath.toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("Only .txt files are allowed.");
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File not found or invalid.");
        }

        // Map to store word frequencies
        Map<String, Integer> wordCount = new HashMap<>();

        // Read file line by line (memory efficient for large files)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split by non-letter characters
                String[] words = line.toLowerCase().split("[^a-zA-Z]+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        // Sort by frequency in descending order
        List<Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Collect top 10
        Map<String, Integer> top10 = new LinkedHashMap<>();
        int count = 0;
        for (Entry<String, Integer> entry : sortedWords) {
            top10.put(entry.getKey(), entry.getValue());
            count++;
            if (count == 10) break;
        }

        return top10;
    }

    public double getIncomeScore(final String incomeStr){
        double income = 60001;
        try {
            income = Double.valueOf(incomeStr);
        } catch (Exception e) {
            System.out.println("Inserted invalid income value");
        }
        return income;
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\User\\OneDrive\\Documents\\test.txt";  // <-- Update this path

        try {
            Map<String, Integer> topKeywords = getTop10Keywords(filePath);
            topKeywords.forEach((word, freq) -> System.out.println(word + ": " + freq));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
