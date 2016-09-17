package com.stryksta.swtorcentral.ui.views.timeline;

public enum TimelineType {
    LINE,
    START,
    MIDDLE,
    END,
    SINGLE;

    static TimelineType fromId(int type) {
        switch (type) {
            case -1:
                return TimelineType.START;
            case 0:
                return TimelineType.MIDDLE;
            case 1:
                return TimelineType.LINE;
            case 2:
                return TimelineType.END;
            case 3:
                return TimelineType.SINGLE;
            default:
                return TimelineType.LINE;
        }
    }
}
