package Assignment2.Aircraft;

public class Aircraft {
    double price;
    double maxElevation;

    public Aircraft(){
        price = 9.99;
        maxElevation = 1250;
    }

    public Aircraft(double _price, double _maxElevation){
        price = _price;
        maxElevation = _maxElevation;
    }

    public Aircraft(Aircraft clone){
        price = clone.price;
        maxElevation = clone.maxElevation;
    }

    // Accessors
    public double getPrice(){
        return price;
    }

    public double getMaxElevation(){
        return maxElevation;
    }

    // Mutators
    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setMaxElevation(double newMaxElevation){
        this.maxElevation= newMaxElevation;
    }

    // Other Methods
    public String toString(){
        return "This Aircraft cost $" + price + ", and has a maximum elevation of " + maxElevation + "m.";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Aircraft otherAircraft = (Aircraft) obj;
        return this.price == otherAircraft.price && this.maxElevation == otherAircraft.maxElevation;
    }
}

class WW2Airplane extends Aircraft{
    boolean twinEngine;

    public WW2Airplane(){
        super();
        twinEngine = false;
    }

    public WW2Airplane(double _price, double _maxElevation, boolean _twinEngine){
        super(_price, _maxElevation);
        twinEngine = _twinEngine;
    }

    public WW2Airplane(WW2Airplane clone){
        price = clone.price;
        maxElevation = clone.maxElevation;
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
            return super.toString() + " This WW2 Airplane has a twin engine.";
        } else{
            return super.toString() + " This WW2 Airplane does not have a twin engine.";
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
