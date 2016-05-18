/**
 * Created by Riper on 18/05/2016.
 */
public class Player {
    private String name;
    private String role;

    public Player(){
        super();
        this.name = "";
    }
    public Player(String name, String role){
        super();
        this.name = name;
        this.role = role;
    }

    public String GetName(){return  name;}
    public String GetRole(){return  role;}
}
