package Repository;

import Domain.Friendship;
import Validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RepoForFriendshipDb implements Repository<Long, Friendship>{
    private String url;
    private String username;
    private String password;
    private Validator validator;

    public RepoForFriendshipDb(String url, String username, String password, Validator validator)
    {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Optional<Friendship> add(Friendship friendship) {
        String sql = "insert into friendships (idUser1, idUser2) values (?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        return Optional.empty();
    }

    @Override
    public Iterable<Friendship> getAll() {
        Set<Friendship> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long idUser1 = resultSet.getLong("idUser1");
                Long idUser2 = resultSet.getLong("idUser2");

                Friendship friendship = new Friendship(idUser1, idUser2);
                friendship.setIdUser1(idUser1);
                friendship.setIdUser2(idUser2);
                users.add(friendship);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<Friendship> delete(Friendship friendship) {
        String delFromFriendslist = "delete friendship where friendship.id=(?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(delFromFriendslist)) {
            ps.setLong(1, friendship.getIdUser1());
            ps.setLong(1, friendship.getIdUser2());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
