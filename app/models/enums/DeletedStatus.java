package models.enums;

/**
 * Created by Administrator on 2015/4/4 0004.
 */
public enum DeletedStatus {
    UN_DELETED(0), DELETED(1);

    private int value;

    DeletedStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
