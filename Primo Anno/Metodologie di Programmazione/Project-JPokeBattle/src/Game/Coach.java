package Game;

/*
 * Coach class
*/
public class Coach {
    private String name;
    private static Team team;
    private int age;
    private String gender;

    public Coach(String name, int age, Team teamIn, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        team = teamIn;
    }
    
    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setTeam(Team teamIn) {
        team = teamIn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

}
