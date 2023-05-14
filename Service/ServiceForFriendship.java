package Service;

import Domain.Friendship;
import Repository.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceForFriendship
{ 	private final Repository<Long, Friendship> repoFriendship;

	public ServiceForFriendship(Repository<Long, Friendship> rf)
	{this.repoFriendship = rf;}

	public Optional<Friendship> addFriendshipService(Friendship friendship)
	{return repoFriendship.add(friendship);}

	public Optional<Friendship> deleteFriendshipService(Friendship friendship)
	{return repoFriendship.delete(friendship);}

	public List<Friendship> getAllFriendshipsService()
	{ 	Iterable<Friendship> friendships = repoFriendship.getAll();
		return StreamSupport.stream(friendships.spliterator(), false).collect(Collectors.toList());}
}
