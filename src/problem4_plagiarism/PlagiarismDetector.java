package problem4_plagiarism;

import java.util.*;

public class PlagiarismDetector {

    // ngram -> documents containing it
    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    // document -> its ngrams
    private HashMap<String, List<String>> documentStore = new HashMap<>();

    private int N = 5; // 5-gram

    // Add document to database
    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        documentStore.put(docId, ngrams);

        for (String gram : ngrams) {
            ngramIndex
                    .computeIfAbsent(gram, k -> new HashSet<>())
                    .add(docId);
        }
    }

    // Analyze new document
    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {

            if (ngramIndex.containsKey(gram)) {

                for (String existingDoc : ngramIndex.get(gram)) {
                    matchCount.put(
                            existingDoc,
                            matchCount.getOrDefault(existingDoc, 0) + 1
                    );
                }
            }
        }

        System.out.println("Extracted " + ngrams.size() + " n-grams");

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);

            double similarity =
                    (matches * 100.0) / ngrams.size();

            System.out.println(
                    "Found " + matches + " matching n-grams with " + doc
            );

            System.out.println(
                    "Similarity: " + similarity + "%"
            );

            if (similarity > 50) {
                System.out.println("PLAGIARISM DETECTED");
            }
        }
    }

    // Generate ngrams
    private List<String> generateNGrams(String text) {

        String[] words = text.toLowerCase().split("\\s+");

        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }

            ngrams.add(gram.toString().trim());
        }

        return ngrams;
    }
}