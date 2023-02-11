package Assignment2.part2.Train;
import Assignment2.part2.Wheeled.WheeledTransportation;

public class Train extends WheeledTransportation{
    public int vehicleCount;
    public String srcStation;
    public String desStation;

    public Train(){
        super();
        vehicleCount = 1;
        srcStation = "Montreal";
        desStation = "Vancouver";
    }

    public Train(int _wheelCount, double _maxSpeed, int _vehicleCount, String _srcStation, String _desStation){
        super(_wheelCount, _maxSpeed);
        vehicleCount = _vehicleCount;
        srcStation = _srcStation;
        desStation = _desStation;
    }

    public Train(Object _clone){
        Train clone = (Train)_clone;
        wheelCount = clone.wheelCount;
        maxSpeed = clone.maxSpeed;
        vehicleCount = clone.vehicleCount;
        srcStation = clone.srcStation;
        desStation = clone.desStation;
    }

    // Accessors
    public int getVehicleCount(){
        return vehicleCount;
    }

    public String getSrcStation(){
        return srcStation;
    }

    public String getDesStation(){
        return desStation;
    }

    // Mutators
    public void setVehicleCount(int newVehicleCount){
        this.vehicleCount = newVehicleCount;
    }

    public void setSrcStation(String newSrcStation){
        this.srcStation = newSrcStation;
    }

    public void setDesStation(String newDesStation){
        this.desStation = newDesStation;
    }

    // Other Methods
    public String toString(){
        return super.toString() + " It's a train that has " + vehicleCount + " vehicle(s), and is going from " + srcStation + " to " + desStation + ".";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Train otherTrain = (Train) obj;
        return this.maxSpeed == otherTrain.maxSpeed && this.wheelCount == otherTrain.wheelCount && this.vehicleCount == otherTrain.vehicleCount && this.srcStation == otherTrain.srcStation && this.desStation == otherTrain.desStation;
    }
}