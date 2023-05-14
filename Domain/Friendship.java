package Domain;

public class Friendship extends Entity<Long> {
	private Long idUser1;
	private Long idUser2;

	public Friendship(Long idUser1, Long idUser2) {
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
	}

	public Long getIdUser1() {
		return idUser1;
	}

	public void setIdUser1(Long idUser1) {
		this.idUser1 = idUser1;
	}

	public Long getIdUser2() {
		return idUser2;
	}

	public void setIdUser2(Long idUser2) {
		this.idUser2 = idUser2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Friendship that)) return false;
		return getIdUser1().equals(that.getIdUser1()) &&
				getIdUser2().equals(that.getIdUser2());
	}
}