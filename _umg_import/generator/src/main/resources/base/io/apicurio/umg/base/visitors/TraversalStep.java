package io.apicurio.umg.base.visitors;

public class TraversalStep {
    
    private String propertyName;
    private boolean isList;
    private boolean isMap;
    private int index;
    private String key;

    public TraversalStep(String propertyName) {
        setPropertyName(propertyName);
    }
    
    public TraversalStep(String propertyName, int index) {
        setPropertyName(propertyName);
        setIndex(index);
        setIsList(true);
    }
    
    public TraversalStep(String propertyName, String key) {
        setPropertyName(propertyName);
        setKey(key);
        setIsMap(true);
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public boolean isList() {
        return isList;
    }
    
    public void setIsList(boolean isList) {
        this.isList = isList;
    }
    
    public boolean isMap() {
        return isMap;
    }
    
    public void setIsMap(boolean isMap) {
        this.isMap = isMap;
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
