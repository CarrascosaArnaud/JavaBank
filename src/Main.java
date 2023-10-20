import java.util.*;

public class Main {
    private static Compte compte = null;
    private static List<Compte> listeComptes = new ArrayList<>();
    public static void main(String[] args) {

        displayMenu();
    }


    private static void transfert() {
        System.out.println("Combien d'argent voulez vous transférer ?");
        double argent = Long.valueOf(new Scanner(System.in).nextLine());
        System.out.println("Quel est l'ID du compte auquel vous voulez transférer ?");
        int id = getSaisie();
        Compte compteRecevant = null;
        for (int i = 0; i < listeComptes.size(); i++) {//Un compte peut se transférer à lui-même
            if (listeComptes.get(i).getId() == id) {
                compteRecevant = listeComptes.get(i);
            }
        }
        if(compteRecevant == null){
            System.out.println("Aucun compte n'existe avec cet ID");
            return;
        }else if (compteRecevant == compte){
            System.out.println("Vous ne pouvez pas vous faire un virement à vous même");
            return;
        }
        compte.transfert(argent, compteRecevant);

    }
    private static void ajoutArgent(){
        System.out.println("Combien d'argent voulez vous ajouter à votre compte ?");
        double argent = Long.valueOf(new Scanner(System.in).nextLine());
        compte.ajoutArgent(argent);
    }
    private static void retirerArgent(){
        System.out.println("Combien d'argent voulez vous retirer à votre compte ?");
        double argent = Long.valueOf(new Scanner(System.in).nextLine());
        compte.retireArgent(argent);
    }
    private static void consulterCompte(){
        if(isLogged()){
            compte.getArgent();
        }
    }

    private static boolean isLogged (){
        return compte != null;
    }

   private static void creerCompte(){
       System.out.println("Création du compte.\nVeuillez rentrer votre ID");
       int id = getSaisie();
       System.out.println("Veuillez rentrer votre mot de passe");
       int password = getSaisie();
       System.out.println("Veuillez rentrer nom");
       String owner = String.valueOf(new Scanner(System.in).nextLine());
       for(int i = 0; i < listeComptes.size(); i++){
           if(listeComptes.get(i).getId() == id){
               System.out.println("Cet ID existe déjà");
               return;
           }
       }
           Compte compte = new Compte(id, password, owner,0);
           listeComptes.add(compte);
           System.out.println("Votre compte a été créé.\nBienvenue " + owner);
    }
    private static void connecteCompte(){
        System.out.println("Connexion en cours.\nVeuillez rentrer votre ID");
        int id = getSaisie();
        System.out.println("Veuillez rentrer votre mot de passe");
        int password = getSaisie();
        for(int i = 0; i < listeComptes.size(); i++){
            if(listeComptes.get(i).connexion(id,password)){
                compte = listeComptes.get(i);
                System.out.println("Tu es connecté\nBonjour " + listeComptes.get(i).getOwner());
            }else
                System.out.println("Mauvais ID/Mot de passe");
        }
    }
    private static final String[] Menu = {
            "Créer un compte",
            "Se connecter",
            "Consulter le montant de son compte",
            "Ajouter de l'argent à son compte",
            "Retirer de l'argent de son compte",
            "Transférer de l'argent à un autre compte"
    };

    private static void displayMenu() {
        System.out.println("\nSaisissez un numéro :");
        for(int i = 0; i < Menu.length; i++){
            System.out.printf(" %d\t->\t%s\n",i,Menu[i]);
        }
        System.out.println("-1\t->\t Quitter");
        startMain(getSaisie());
    }

    private static void startMain(int index){
        if (index == -1) return;
        else if((index > 1 && index < 6) && !isLogged()){
            System.out.println("Veuillez vous connecter pour accéder à cette fonctionnalité");
        }else if(index == 0){
            creerCompte();
        }else if (index == 1){
            connecteCompte();
        }else{
            switch (index){
                case 2 -> consulterCompte();
                case 3 -> ajoutArgent();
                case 4 -> retirerArgent();
                case 5 -> transfert();
                default -> System.out.println("L'index saisi ne corresponds à aucun programme");
            }
        }
        displayMenu();
    }

    public static int getSaisie() {
        Scanner scanner = new Scanner(System.in);
        int saisie;
        try {
            saisie = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("La saisie est invalide, veuillez réessayer");
            saisie = getSaisie();
        }
        return saisie;
    }

    /*
    Objectifs de l'application : (Informations saisies via la console)
    Permettre à une personne de disposer d'un ou plusieurs comptes bancaires
    Pouvoir effectuer des retraits sur un compte dont le solde est positif
    Pouvoir déposer sur un compte
    Possibilité de virement entre plusieurs comptes

    Saisie de code pour accéder au compte
    Vérification des soldes pour éviter les découverts
    Sauvegarde des données entre les différents démarrages
     */

    //Une classe compte, qui contient un solde(retrait, dépot)
    //Pour faire des virements entre plusieurs comptes, un compte doit être identifié
    //Un code à partir de l'identifiant permet de se connecter au compte
    //Ensuite optimisation et responsabilité (dépot, retrait, virement)
    //Mettre une vérification pour empêcher le découvert
    //Sauvegarde : choix de sérialisation
    //Pouvoir naviguer entre les multiples menus
    //Ecriture dans un fichier (ou bdd)



    //Se créer compte               //Constructeur
    //Se connecter à un compte      //Compte.connexion(id,psw)
    //
    //Regarder le montant           //getArgent(argent)
    //Ajouter montant               //ajoutArgent(argent)
    //Retirer montant               //retireArgent(argent)
    //Transférer montant de x à y   //transfert(argent, compteOffrant, compteRecevant)
}