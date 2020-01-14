package io.apicurio.datamodels.openapi.visitors.dereference;

public class Reference {


    private final String ref;
    private String abs;
    private String rel;

    public Reference(String ref) {
        if(ref == null)
            throw new IllegalArgumentException();
        this.ref = ref;
        // split relative/absolute part
        String[] parts = ref.split("#");
        if(parts.length == 0) {
            abs = ref;
            rel = null;
        } else if (parts.length == 2) {
            abs = "".equals(parts[0]) ? null : parts[0];
            rel = "#" + parts[1];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getRef() {
        return ref;
    }

    public String getAbsPart() {
        return abs;
    }

    public String getRelPart() {
        return rel;
    }

    public boolean isRelative() {
        return abs == null;
    }

    public String getName() {
        String[] parts = ref.split("/"); // TODO checks
        //parts = parts[parts.length - 1].split(".");
        return parts[parts.length - 1];
    }

    public Reference withAbsoluteFrom(Reference that) {
        if(that.getAbsPart() == null)
            throw new IllegalArgumentException();
        return new Reference(that.abs + rel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reference reference = (Reference) o;

        return ref.equals(reference.ref);
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }
}
