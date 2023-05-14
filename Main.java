import Domain.Friendship;
import Domain.User;
import Repository.RepoForFriendshipDb;
import Repository.RepoForUserDb;
import Repository.Repository;
import Service.ServiceForFriendship;
import Service.ServiceForUser;
import UI.Console;
import Validators.ValidatorForFriendship;
import Validators.ValidatorForUser;

public class Main
{
    public static void main(String[] args)
    {   String username="postgres";
        String password="admin";
        String url="jdbc:postgresql://localhost:5432/lab4";
        Repository<Long, User> repoUser = new RepoForUserDb(url, username, password,  new ValidatorForUser());
        Repository<Long, Friendship> repoFr = new RepoForFriendshipDb(url, username, password,  new ValidatorForFriendship());
        ServiceForFriendship serviceFr = new ServiceForFriendship(repoFr);
        ServiceForUser serviceUser= new ServiceForUser(repoUser);
        Console ui = new Console(serviceUser, serviceFr);
        ui.showUI();
    }
}