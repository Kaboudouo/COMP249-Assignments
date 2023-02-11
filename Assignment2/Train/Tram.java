package Assignment2.Train;
import Assignment2.Metro.*;

public class Tram extends Metro{
    int yrCreation;

    public Tram(){
        super();
        yrCreation = 1984;
    }

    public Tram(int _yrCreation, int _stopCount, int _wheelCount, double _maxSpeed, int _vehicleCount, String _srcStation, String _desStation){
        super(_stopCount, _wheelCount, _maxSpeed, _vehicleCount, _srcStation, _desStation);
        yrCreation = _yrCreation;
    }

    public Tram(Tram clone){
        yrCreation = clone.yrCreation;
        stopCount = clone.stopCount;
        wheelCount = clone.wheelCount;
        maxSpeed = clone.maxSpeed;
        vehicleCount = clone.vehicleCount;
        srcStation = clone.srcStation;
        desStation = clone.desStation;
    }

    // Accessors
    public int getYrCreation(){
        return yrCreation;
    }

    // Mutators
    public void setYrCreation(int newYrCreation){
        this.yrCreation = newYrCreation;
    }

    // Other Methods
    public String toString(){
        return super.toString() + " However, it is technically a tram and was created in " + yrCreation + ".";
    }

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Tram otherTram = (Tram) obj;
        return this.maxSpeed == otherTram.maxSpeed && this.wheelCount == otherTram.wheelCount && this.vehicleCount == otherTram.vehicleCount && this.srcStation == otherTram.srcStation && this.desStation == otherTram.desStation && this.stopCount == otherTram.stopCount && this.yrCreation == otherTram.yrCreation;
    }
}
