package UI;

import Domain.Friendship;
import Domain.User;
import Service.ServiceForFriendship;
import Service.ServiceForUser;
import Validators.ValidationException;
import Validators.ValidatorForFriendship;
import Validators.ValidatorForUser;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Console{
    public ServiceForUser serviceUser;
    public ServiceForFriendship serviceFriendship;

    public Console(ServiceForUser serviceUser, ServiceForFriendship serviceFriendship)
   {
        this.serviceUser = serviceUser;
        this.serviceFriendship = serviceFriendship;
    }

    private void showMenu(){
        System.out.println("1.Introduceti un utilizator");
        System.out.println("2.Introduceti o prietenie");
        System.out.println("3.Stergeti un utilizator");
        System.out.println("4.Stergeti o prietenie");
        System.out.println("5.Afisati utilizatorii");
        System.out.println("6.Afisati prieteniile");
        System.out.println("7.Iesire");
        System.out.println("Dati optiunea dorita: ");}

    public List<User> getAllUsersUi(){return serviceUser.getAllUsersService();}

    public List<Friendship> getAllFriendshipsUi(){
        return serviceFriendship.getAllFriendshipsService();
    }

    public void addUserUi(User user)
    {
        for (User i: serviceUser.getAllUsersService())
            if(Objects.equals(i.getId(), user.getId()))
                throw new ValidationException("Exista deja un utilizator cu acest id!");
        serviceUser.addUserService(user);
    }

    public void addFriendshipUi(Friendship friendship){

        int ok1=0,ok2=0;
        for (User i: serviceUser.getAllUsersService())
            if(Objects.equals(i.getId(), friendship.getIdUser1()))
                ok1=1;
            else if(Objects.equals(i.getId(), friendship.getIdUser2()))
                ok2=1;
        if (ok1 == 1 && ok2 == 1)
        {for(Friendship fr: serviceFriendship.getAllFriendshipsService())
            if (
                    (Objects.equals(fr.getIdUser1(), friendship.getIdUser1()) && Objects.equals(fr.getIdUser2(), friendship.getIdUser2())) ||
                            (Objects.equals(fr.getIdUser1(), friendship.getIdUser2()) && Objects.equals(fr.getIdUser2(), friendship.getIdUser1()))
            )
                throw new ValidationException("Sunt prieteni deja!");
        serviceFriendship.addFriendshipService(friendship);
        }
        else throw new ValidationException("Nu exista unul dintre id-uri!");
    }

    public void deleteUserUi(User user) {
        int ok = 0;
        for (User u: serviceUser.getAllUsersService())
            if (Objects.equals(u.getId(), user.getId()) && Objects.equals(u.getUsername(), user.getUsername())) {
                ok = 1;
                break;
            }
        if(ok == 0)throw new ValidationException("Nu exista acest utilizator!");
        else serviceUser.deleteUserService(user);
    }

    public void deleteFriendshipUi(Friendship fr){
        int ok = 0;
        for (Friendship i: serviceFriendship.getAllFriendshipsService())
            if ((Objects.equals(i.getIdUser1(), fr.getIdUser1()) && Objects.equals(i.getIdUser2(), fr.getIdUser2())) ||
                    (Objects.equals(i.getIdUser1(), fr.getIdUser2()) && Objects.equals(i.getIdUser2(), fr.getIdUser1()))) {
                ok = 1;
                break;
            }
        if(ok == 0) throw new ValidationException("Nu exista acest utilizator!");
        else serviceFriendship.deleteFriendshipService(fr);}

    public void showUI(){
        Scanner scanner= new Scanner(System.in);
        int optiune;

        while(true){
            showMenu();
            optiune=scanner.nextInt();
        if(optiune == 1){
            try{System.out.println("Introduceti id-ul:");
                String id = scanner.next();
                System.out.println("Introduceti username-ul:");
                String username = scanner.next();
                User user = new User(username);
                addUserUi(user);}
            catch (ValidationException ve) {ve.printStackTrace();}
        }
        else if(optiune == 2){
            try{System.out.println("Introduceti id-ul primului prieten:");
                Long idUser1 = Long.valueOf(scanner.next());
                System.out.println("Introduceti id-ul celui de-al doilea prieten:");
                Long idUser2 = Long.valueOf(scanner.next());
                Friendship fr = new Friendship(idUser1, idUser2);
                addFriendshipUi(fr);}
            catch (ValidationException ve) {ve.printStackTrace();}
        }
        else if(optiune == 3){
            try{System.out.println("Dati  id-ul utilizatorului pe care doriti sa-l stergeti: ");
                String id = scanner.next();
                System.out.println("Dati username-ul utilizatorului pe care doriti sa-l stergeti:");
                String username = scanner.next();
                User user = new User(username);
                deleteUserUi(user);}
            catch(ValidationException ve) {ve.printStackTrace();}
        }
        else if(optiune == 4){
            try{System.out.println("Dati primul id al prieteniei pe care doriti sa o stergeti: ");
                Long id = Long.valueOf(scanner.next());
                System.out.println("Dati al doilea id al prieteniei pe care doriti sa o stergeti: ");
                Long id2 = Long.valueOf(scanner.next());
                Friendship fr = new Friendship(id, id2);
                deleteFriendshipUi(fr);}
            catch (ValidationException ve) {ve.printStackTrace();}
        }
        else if(optiune == 5){
            for (User i : getAllUsersUi())
                System.out.println(i.getId() + " " + i.getUsername());
            System.out.println("\n");
        }
        else if(optiune == 6){
            for (Friendship i : getAllFriendshipsUi())
                System.out.println(i.getIdUser1() + " " + i.getIdUser2());
            System.out.println("\n");
        }
        else if (optiune == 7) break;
        else System.out.println("Optiune gresita! Reincercati.");
    }
}
}
