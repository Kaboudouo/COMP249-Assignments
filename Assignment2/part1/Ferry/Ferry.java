package Assignment2.part1.Ferry;

public class Ferry{
    
    // Variables
    double maxSpeed;
    double maxLoad;

    // Constructors
    public Ferry(){
        maxSpeed = 10;
        maxLoad = 100;
    }

    public Ferry(double _maxSpeed, double _maxLoad){
        maxSpeed = _maxSpeed;
        maxLoad = _maxLoad;
    }

    public Ferry(Ferry clone){
        maxSpeed = clone.maxSpeed;
        maxLoad = clone.maxLoad;
    }

    // Accessors
    public double getMaxSpeed(){
        return maxSpeed;
    }

    public double getMaxLoad(){
        return maxLoad;
    }

    // Mutators
    public void setMaxSpeed(double newMaxSpeed){
        this.maxSpeed = newMaxSpeed;
    }

    public void setMaxLoad(double newMaxLoad){
        this.maxLoad= newMaxLoad;
    }

    // Other Methods
    public String toString(){
        return "This Ferry has a maximum speed of " + maxSpeed + "km/h, and has a maximum load capacity of " + maxLoad + "kg.";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Ferry otherFerry = (Ferry) obj;
        return this.maxSpeed == otherFerry.maxSpeed && this.maxLoad == otherFerry.maxLoad;
    }
}