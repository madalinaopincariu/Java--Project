package Validators;

import Domain.User;

public class ValidatorForUser implements Validator<User>
{   @Override
    public void validate(User user) throws ValidationException
    {   if (user.getUsername().length()<3)
            throw new ValidationException("Numele trebuie sa fie mai lung!");
    }
}
