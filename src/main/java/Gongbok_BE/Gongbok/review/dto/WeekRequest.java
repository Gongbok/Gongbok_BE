package Gongbok_BE.Gongbok.review.dto;

public class WeekRequest {

    private int weekNum;
    private long weekId;

    public int getWeekNum() {
        return weekNum;
    }

    public long getWeekId() {
        return weekId;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setWeekId(long weekId) {
        this.weekId = weekId;
    }

}