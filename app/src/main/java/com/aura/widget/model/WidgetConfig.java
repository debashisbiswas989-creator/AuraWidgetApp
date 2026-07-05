package com.aura.widget.model;

import java.util.ArrayList;
import java.util.List;

public class WidgetConfig {
    public String widgetName;
    public List<WidgetElement> elements = new ArrayList<>();

    public static class WidgetElement {
        public String type; // "text", "container", etc.
        public String content;
        public String color;
        public int size;
    }
}
