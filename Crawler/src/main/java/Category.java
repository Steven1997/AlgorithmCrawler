/**
 * 分类实体类
 */
public class Category {
    private int index;
    private int occurCount;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOccurCount() {
        return occurCount;
    }

    public void setOccurCount(int occurCount) {
        this.occurCount = occurCount;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name=" + Main.getCandidate().get(index) +
                ", occurCount=" + occurCount +
                '}';
    }
}
