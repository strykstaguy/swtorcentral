package com.stryksta.swtorcentral.util;

public enum TimelineType {
    LINE,
    START,
    MIDDLE,
    END;

    static TimelineType fromId(int type) {
        switch (type) {
            case -1:
                return TimelineType.START;
            case 0:
                return TimelineType.MIDDLE;
            case 1:
                return TimelineType.END;
            case 2:
                return TimelineType.LINE;
            default:
                return TimelineType.LINE;
        }
    }
}
