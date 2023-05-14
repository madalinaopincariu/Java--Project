package Validators;

import Domain.Friendship;

import java.util.Objects;

public class ValidatorForFriendship implements Validator<Friendship> {
    @Override
    public void validate(Friendship friendship) throws ValidationException {
        int ok = 1;
        Long i;
        if (Objects.equals(friendship.getIdUser1(), friendship.getIdUser2()))
            throw new ValidationException("Id-urile prietenilor trebuie sa fie diferite!");
        //else for (i = 0L; i < 1000 && ok == 0; i++)
        //if (!Objects.equals(friendship.getIdUser1(), i) || !Objects.equals(i, friendship.getIdUser2())) ok=1;
    }
}
