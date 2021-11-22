package spring.tp4_front.Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class People {

    private Integer _id;
    private String lastname;
    private String firstname;

    private String address;


    public People(){
        lastname = "default";
        _id = -1;
        firstname = "default";
        address = "default";
    }

    public People(int _id, String lastname, String firstname, String address) {
        this._id = _id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String people_to_JSON(People people){
        StringBuilder json = new StringBuilder();
        json.append('{')
                .append("\"_id\": \"").append(people.get_id()).append("\",")
                .append("\"lastname\": \"").append(people.getLastname()).append("\",")
                .append("\"firstname\":\"").append(people.getFirstname()).append("\",")
                .append("\"address\":\"").append(people.getAddress()).append("\"")
                .append('}');
        return json.toString();
    }

    public static People json_to_People(String json){

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        People people = new People();
        try {
            jsonObject = (JSONObject) parser.parse(json);
            people.set_id(Integer.parseInt(jsonObject.get("_id").toString()));
            people.setLastname(jsonObject.get("lastname").toString());
            people.setFirstname(jsonObject.get("firstname").toString());
            people.setAddress(jsonObject.get("address").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return people;
    }
}
