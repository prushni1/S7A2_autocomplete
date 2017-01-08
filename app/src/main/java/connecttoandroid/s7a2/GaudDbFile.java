package connecttoandroid.s7a2;

public class GaudDbFile {

    //private variables
    int _id;
    String _name;
    String _phone_number;
    String _datentime;

    // Empty constructor
    public GaudDbFile(){

    }
    // constructor
    public GaudDbFile(int id, String name, String _phone_number, String _datentime){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;

        this._datentime=_datentime;
    }

    // constructor
    public GaudDbFile(String name, String _phone_number, String _datentime){

        this._name = name;
        this._phone_number = _phone_number;

        this._datentime=_datentime;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    // getting _datentime
    public String getDatenTime(){
        return this._datentime;
    }


    // setting _datentime
    public void setDatenTime(String _datentime){
        this._datentime = _datentime;
    }
}
