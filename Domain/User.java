package Domain;

import java.util.Objects;

public class User extends Entity<Long>{
	private String userName;

	public User() {
		this.userName = null;
	}

	public User(String username) {
		this.userName = username;
	}

	public User(User u) {
		this.userName = u.userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getUsername() {
		return userName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User that)) return false;
		return getId().equals(that.getId());
	}
	@Override
	public int hashCode() {
		return Objects.hash(getUsername());
	}
}