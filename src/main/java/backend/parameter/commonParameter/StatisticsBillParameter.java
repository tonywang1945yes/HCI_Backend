package backend.parameter.commonParameter;

public class StatisticsBillParameter {
    String id;
    int min;
    int max;
    String name;//用户搜索时是商家名，商家搜索时是用户邮箱

    public StatisticsBillParameter(String id, int min, int max, String name) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public StatisticsBillParameter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
