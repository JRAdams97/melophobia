from django.contrib.auth.models import AbstractUser
from django.db import models

class User(AbstractUser):
    # Custom user model
    pass

class Profile(models.Model):
    # Profile model associated with an individual User
    user = models.OneToOneField(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.user.username
