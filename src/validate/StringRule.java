package validate;

public class StringRule {
    private int minLength;
    private int maxLength;

    public StringRule() {
        this.minLength = 0;
        this.maxLength = Integer.MAX_VALUE;
    }

    public StringRule(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public boolean isValid(String input) {
        if (input == null){
            return false;
        }
        return input.length() >= minLength && input.length() <= maxLength;
    }
}
