package problem4_plagiarism;

public class Main {

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        detector.addDocument(
                "essay_089",
                "machine learning is transforming modern technology and data science"
        );

        detector.addDocument(
                "essay_092",
                "machine learning is transforming modern technology and data science rapidly"
        );

        detector.analyzeDocument(
                "essay_123",
                "machine learning is transforming modern technology and data science rapidly across industries"
        );
    }
}
