package Service;

import Domain.User;
import Repository.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceForUser
{ 	private final Repository<Long, User> repoUser;

    public ServiceForUser(Repository<Long, User> rf)
    {this.repoUser = rf;}

    public Optional<User> addUserService(User user)
    {return repoUser.add(user);}

    public Optional<User> deleteUserService(User user)
    {return repoUser.delete(user);}

    public List<User> getAllUsersService()
    { Iterable<User> users = repoUser.getAll();
        return StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
    }
}
