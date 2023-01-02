package scrapper.model;

public class Review {
    private final String mark;
    private final String positiveComment;
    private final String negativeComment;

    public Review(String mark, String positiveComment, String negativeComment) {
        this.mark = mark;
        this.positiveComment = positiveComment;
        this.negativeComment = negativeComment;
    }

    public String getMark() {
        return mark;
    }

    public String getPositiveComment() {
        return positiveComment;
    }

    public String getNegativeComment() {
        return negativeComment;
    }
}
