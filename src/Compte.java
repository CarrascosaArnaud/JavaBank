public class Compte {
    //Attributs
    private int id, password;
    private String owner;
    //Private  Personne owner;
    private double argent;

    //Constructeurs
    public Compte(){

    }
    public Compte (int id, int password, String owner, double argent){
        this.id = id;
        this.password = password;
        this.owner = owner;
        this.argent = argent;
    }

    //Getters & Setters
    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  int getPassword() {
        return password;
    }

    public  void setPassword(int password) {
        this.password = password;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public  double getArgent() {
        System.out.println("Solde : " + argent + "€");
        return argent;
    }

    public  void setArgent(double argent) {
        this.argent = argent;
    }

    //Méthodes
    public  boolean connexion(int id, int password){
        return(id == this.id && password == this.password);
    }

    public void ajoutArgent(double argent){
        this.argent += argent;
        System.out.println(argent + "€ ont été ajoutés à votre compte.\n");
        getArgent();
    }

    public boolean retireArgent(double argent){
        if (argent <= this.argent) {
            this.argent -= argent;
            System.out.println(argent + "€ ont été retirés de votre compte.\n");
            getArgent();
            return true;
        }else{
            System.out.println("Vous ne pouvez pas retirer " + argent + " à votre compte au risque de passer à découvert.");
            return false;
        }
    }

    public void transfert(double argent, Compte compteRecevant){
        if (retireArgent(argent)){
            compteRecevant.ajoutArgent(argent);
            System.out.println("Vous avez viré " + argent + "€ au compte N°" + compteRecevant.getId());
            getArgent();
        }
        else System.out.println("Le transfert n'est pas possible.");
    }
}
