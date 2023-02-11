package Assignment2.part2.Mono;
import Assignment2.part2.Wheeled.WheeledTransportation;

public class Monowheel extends WheeledTransportation{
    double maxWeight;

    public Monowheel(){
        super();
        maxWeight = 350;
    }

    public Monowheel(double _maxWeight, int _wheelCount, double _maxSpeed){
        super(_wheelCount, _maxSpeed);
        maxWeight = _maxWeight;
    }

    public Monowheel(Object _clone){
        Monowheel clone = (Monowheel)_clone;
        maxWeight = clone.maxWeight;
        wheelCount = clone.wheelCount;
        maxSpeed = clone.maxSpeed;
    }

    // Accessors
    public double getMaxWeight(){
        return maxWeight;
    }

    // Mutators
    public void setWheelCount(double newMaxWeight){
        this.maxWeight = newMaxWeight;
    }

    // Other Methods
    public String toString(){
        return super.toString() + " Being a monowheel, it has a maximum weight load of " + maxWeight + "kg.";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Monowheel otherMonowheel = (Monowheel) obj;
        return this.maxSpeed == otherMonowheel.maxSpeed && this.wheelCount == otherMonowheel.wheelCount && this.maxWeight == otherMonowheel.maxWeight;
    }
}
