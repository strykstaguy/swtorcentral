package com.stryksta.swtorcentral.data;

import com.stryksta.swtorcentral.util.TimelineType;

/**
 * Created by Bernat on 06/04/2014.
 */
public class TestItem {

    private String name;
    private TimelineType tipo;

    public TestItem(String name) {
        this.name = name;
        this.tipo = TimelineType.LINE;
    }

    public TestItem(String name, TimelineType tipo) {
        this.name = name;
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimelineType getTipo() {
        return tipo;
    }

    public void setTipo(TimelineType tipo) {
        this.tipo = tipo;
    }
}
