package problem.car.visitor.api;

/**
 * Created by wrightjt on 1/4/2016.
 */
class LookupKey {
    VisitType visitType;
    Class<?> clazz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LookupKey lookupKey = (LookupKey) o;

        if (visitType != lookupKey.visitType) return false;

        if(!lookupKey.clazz.isAssignableFrom(this.clazz))
            return false;
        return !(clazz != null ? !clazz.equals(lookupKey.clazz) : lookupKey.clazz != null);

    }

    @Override
    public int hashCode() {
        int result = visitType != null ? visitType.hashCode() : 0;
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        return result;
    }

    public LookupKey(VisitType visitType, Class<?> clazz) {

        this.visitType = visitType;
        this.clazz = clazz;
    }
}
