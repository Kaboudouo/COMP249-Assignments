package Assignment2.Wheeled;

public class WheeledTransportation {
    public int wheelCount;
    public double maxSpeed;

    public WheeledTransportation(){
        wheelCount = 16;
        maxSpeed = 80;
    }

    public WheeledTransportation(int _wheelCount, double _maxSpeed){
        wheelCount = _wheelCount;
        maxSpeed = _maxSpeed;
    }

    public WheeledTransportation(WheeledTransportation clone){
        wheelCount = clone.wheelCount;
        maxSpeed = clone.maxSpeed;
    }

    // Accessors
    public int getWheelCount(){
        return wheelCount;
    }

    public double getMaxSpeed(){
        return maxSpeed;
    }

    // Mutators
    public void setWheelCount(int newWheelCount){
        this.wheelCount = newWheelCount;
    }

    public void setMaxSpeed(double newMaxSpeed){
        this.maxSpeed= newMaxSpeed;
    }

    // Other Methods
    public String toString(){
        return "This Wheeled Transportation has a maximum speed of " + maxSpeed + "km/h, and has " + wheelCount + " wheel(s).";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        WheeledTransportation otherWheeledTransportation = (WheeledTransportation) obj;
        return this.maxSpeed == otherWheeledTransportation.maxSpeed && this.wheelCount == otherWheeledTransportation.wheelCount;
    }
}
