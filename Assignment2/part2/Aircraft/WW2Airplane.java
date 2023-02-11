package Assignment2.part2.Aircraft;
public class WW2Airplane extends Aircraft{
    boolean twinEngine;

    public WW2Airplane(){
        super();
        twinEngine = false;
    }

    public WW2Airplane(double _price, double _maxElevation, boolean _twinEngine){
        super(_price, _maxElevation);
        twinEngine = _twinEngine;
    }

    public WW2Airplane(Object _clone){
        WW2Airplane clone = (WW2Airplane) _clone;
        price = clone.price;
        maxElevation =clone.maxElevation;
        twinEngine = clone.twinEngine;
    }

    // Accessors
    public boolean getTwinEngine(){
        return twinEngine;
    }

    // Mutators
    public void setTwinEngine(boolean newTwinEngine){
        this.twinEngine = newTwinEngine;
    }

    // Other Methods
    public String toString(){
        if (twinEngine){
            return super.toString() + " It's a WW2 Airplane that has a twin engine.";
        } else{
            return super.toString() + " It's a WW2 Airplane that does not have a twin engine.";
        }
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        WW2Airplane otherWW2Airplane = (WW2Airplane) obj;
        return this.price == otherWW2Airplane.price && this.maxElevation == otherWW2Airplane.maxElevation && this.twinEngine == otherWW2Airplane.twinEngine;
    }
}
