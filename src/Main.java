import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("=========          ALCRYPT          ===========");
        System.out.println("=========          V 1.1            ===========");
        System.out.println("=========            By             ===========");
        System.out.println("=========     Lyse AND Alhdo        ===========");
        System.out.println("===============================================");

        System.out.println("Please choose what you want");
        System.out.println("\n 1) Encode");
        System.out.println("\n 2) Decode");
        System.out.println("\n 3) Exit");

        Scanner sc = new Scanner(System.in);
        int reponse = sc.nextInt();

        switch(reponse){
            case 1:
                sc.nextLine();
                System.out.println("\nVeuillez entrer le texte que vous voulez encoder.\n");
                String texte = sc.nextLine();
                Alcrypt mots = new Alcrypt(texte);
                System.out.println("Votre texte crypte et proteger va s'afficher: ");
                System.out.println(mots.encode());
                break;
            case 2:
                sc.nextLine();
                System.out.println("Veuillez entrer le texte que vous voulez decoder\n");
                String txt = sc.nextLine();

                Alcrypt alcrypt=new Alcrypt(txt);
                System.out.println(alcrypt.decode());
                System.out.println(Alcrypt.decode(txt));
                break;
            case 3:
                sc.nextLine();
                System.out.println("Thanks");
                System.exit(0);
            default:
                System.out.println("Veuillez entrer correctement votre choix.");
                break;
        }


    }
}
