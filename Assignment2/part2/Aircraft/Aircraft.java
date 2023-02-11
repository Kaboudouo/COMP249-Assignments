package Assignment2.part2.Aircraft;

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
        return "This Aircraft costs $" + price + ", and has a maximum elevation of " + maxElevation + "m.";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Aircraft otherAircraft = (Aircraft) obj;
        return this.price == otherAircraft.price && this.maxElevation == otherAircraft.maxElevation;
    }
}

