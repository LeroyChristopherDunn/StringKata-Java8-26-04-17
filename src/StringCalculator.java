import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class StringCalculator {

    static int add(String numbers) throws Exception {

        if (numbers.isEmpty())
            return 0;
        return sum(numbers);

    }

    private static int[] extractNumbers(String numbers) {

        String delimiterRegex = ",|\n";

        if (numbers.startsWith("//")) {
            Pattern pattern = getPattern(numbers);
            Matcher matcher = pattern.matcher(numbers);
            matcher.find();
            String[] splitDelimiter = matcher.group(1).split("\\]\\[");
            delimiterRegex = Arrays.stream(splitDelimiter).map(Pattern::quote).collect(Collectors.joining("|"));
            numbers = matcher.group(2);
        }

        return Arrays.stream(numbers.split(delimiterRegex)).mapToInt(Integer::parseInt).toArray();

    }

    private static Pattern getPattern(String numbers) {
        Pattern pattern;
        if (numbers.startsWith("//[")) {
            pattern = Pattern.compile("//\\[(.*)\\]\n(.*)");
        } else {
            pattern = Pattern.compile("//(.*)\n(.*)");
        }
        return pattern;
    }

    private static int sum(String numbers) throws Exception {

        int[] extractedNumbers = extractNumbers(numbers);
        int[] negativeNumbers = Arrays.stream(extractedNumbers).filter(i -> i < 0).toArray();
        if (negativeNumbers.length > 0)
            throw new Exception("Negatives not allowed: " + Arrays.toString(negativeNumbers));

        return Arrays.stream(extractedNumbers).filter(i -> i < 1000).sum();

    }
}
