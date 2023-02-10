package Assignment2.Metro;
import Assignment2.Train.Train;

public class Metro extends Train{
    public int stopCount;

    public Metro(){
        super();
        stopCount = 5;
    }

    public Metro(int _stopCount, int _wheelCount, double _maxSpeed, int _vehicleCount, String _srcStation, String _desStation){
        super(_wheelCount, _maxSpeed, _vehicleCount, _srcStation, _desStation);
        stopCount = _stopCount;
    }

    public Metro(Metro clone){
        stopCount = clone.stopCount;
        wheelCount = clone.wheelCount;
        maxSpeed = clone.maxSpeed;
        vehicleCount = clone.vehicleCount;
        srcStation = clone.srcStation;
        desStation = clone.desStation;
    }

    // Accessors
    public int getStopCount(){
        return stopCount;
    }

    // Mutators
    public void setStopCount(int newStopCount){
        this.stopCount = newStopCount;
    }

    // Other Methods
    public String toString(){
        return super.toString() + ". It has " + stopCount + " stops.";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Metro otherMetro = (Metro) obj;
        return this.maxSpeed == otherMetro.maxSpeed && this.wheelCount == otherMetro.wheelCount && this.vehicleCount == otherMetro.vehicleCount && this.srcStation == otherMetro.srcStation && this.desStation == otherMetro.desStation && this.stopCount == otherMetro.stopCount;
    }
}
