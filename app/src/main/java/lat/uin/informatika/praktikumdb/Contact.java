package lat.uin.informatika.praktikumdb;

/**
 * Created by aldyrialdy on 07/11/16.
 */
public class Contact {
    private int _id;
    private String _name;
    private String _phonenumber;
    public Contact(){
    }
    public Contact(String name, String phonenumber){
        this._name = name;
        this._phonenumber = phonenumber;
    }
    public Contact(int id,String name, String phonenumber){
        this._id = id;
        this._name = name;
        this._phonenumber = phonenumber;
    }
    public int getId() {
        return _id;
    }
    public void setId(int id) {
        this._id = id;
    }
    public String getName() {
        return _name;
    }
    public void setName(String name) {
        this._name = name;
    }
    public String getPhonenumber() {
        return _phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this._phonenumber = phonenumber;
    }
}
