package mecanismoDeBusca;

public class TermFreq implements Comparable<TermFreq> {
    private String term;
    private int count;

    public TermFreq(String term, int count) {
        this.term = term;
        this.count = count;
    }

    @Override
    public int compareTo(TermFreq o) {
        int a = getCount().intValue();
        int b = o.getCount().intValue();
        if(a == b)
            return 0;
        if(a > b)
            return -1;
        return 1;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

